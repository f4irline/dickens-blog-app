package com.github.dickens.blogapp.security;

import com.github.dickens.blogapp.security.auth.ApiResponse;
import com.github.dickens.blogapp.security.auth.jwt.JwtAuthResponse;
import com.github.dickens.blogapp.security.auth.jwt.JwtTokenizer;
import com.github.dickens.blogapp.security.auth.requests.LoginRequest;
import com.github.dickens.blogapp.security.auth.requests.RegisterRequest;
import com.github.dickens.blogapp.user.User;
import com.github.dickens.blogapp.user.UserRepository;
import com.github.dickens.blogapp.user.role.Role;
import com.github.dickens.blogapp.user.role.RoleDefinition;
import com.github.dickens.blogapp.user.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Collections;

/**
 * RestController implementation that's related to authentication requests.
 *
 * @author Tommi Lepola
 * @version 2.0
 * @since 2019.0323
 */
@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {
    /**
     * AuthenticationManager instance that's used for the authentication.
     */
    @Autowired
    AuthenticationManager authenticationManager;

    /**
     * CrudRepository for users.
     */
    @Autowired
    UserRepository userRepository;

    /**
     * CrudRepository for roles.
     */
    @Autowired
    RoleRepository roleRepository;

    /**
     * Implementation for generating JWT tokens and validating the JWT tokens send in requests.
     */
    @Autowired
    JwtTokenizer tokenizer;

    /**
     * Returns HTTP OK response if user authenticates succesfully.
     *
     * @param loginRequest the login request which includes userName and password to be checked.
     * @return HTTP OK response with JwtToken in the body of the response.
     */
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        /**
         * Try to authenticate user.
         */
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUserName(),
                        loginRequest.getPassword()
                )
        );

        /**
         * Changes the currently authenticated principal if authentication was succesful.
         */
        SecurityContextHolder.getContext().setAuthentication(authentication);

        /**
         * Generate JWT token from the tokenizer and respond with it.
         */
        String jwt = tokenizer.generateToken(authentication);
        return ResponseEntity.status(HttpStatus.OK).body(new JwtAuthResponse(jwt));
    }

    /**
     * Handles registering the user.
     *
     * <p>
     * Method checks first if a user with the given name already exists. If user does exist, we respond
     * with HTTP bad request and body to tell the user that username was already taken.
     * </p>
     *
     * <p>
     * If user does not exist, we create a new User entity, set it's role initially to be just a regular
     * user and then save it to repository. Then we return HTTP OK to user with the endpoint location of
     * the new user.
     * </p>
     *
     * @param signUpRequest the register request object with required fields for users.
     * @param b builder related to this action to build a location URI for response headers.
     * @return HTTP status 200 if succesful, 400 if user with given name already exists.
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest signUpRequest, UriComponentsBuilder b) {
        if(userRepository.existsByUserName(signUpRequest.getUserName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, "Username already taken."));
        }

        User user = new User(signUpRequest.getUserName(), signUpRequest.getUserFirst(),
                signUpRequest.getUserLast(), signUpRequest.getPassword());

        Role userRole = roleRepository.findByDefinition(RoleDefinition.ROLE_USER)
                .orElseThrow(() -> new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR));

        user.setRoles(Collections.singleton(userRole));

        userRepository.save(user);

        UriComponents components = b.path("/api/users/{userName}").buildAndExpand(user.getUserName());

        return ResponseEntity.status(HttpStatus.OK).location(components.toUri()).body(new ApiResponse(true, "User registered successfully"));
    }
}
