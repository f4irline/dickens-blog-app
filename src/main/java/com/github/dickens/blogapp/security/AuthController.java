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

@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    JwtTokenizer tokenizer;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUserName(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenizer.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthResponse(jwt));
    }

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

        UriComponents components = b.path("/api/users/{userName}").buildAndExpand(user.getUserName());

        return ResponseEntity.status(HttpStatus.OK).location(components.toUri()).body(new ApiResponse(true, "User registered successfully"));
    }
}
