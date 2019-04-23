package com.github.dickens.blogapp.user;

import com.github.dickens.blogapp.comment.Comment;
import com.github.dickens.blogapp.comment.CommentRepository;
import com.github.dickens.blogapp.post.Post;
import com.github.dickens.blogapp.post.PostController;
import com.github.dickens.blogapp.post.PostRepository;
import com.github.dickens.blogapp.security.auth.ApiResponse;
import com.github.dickens.blogapp.user.role.Role;
import com.github.dickens.blogapp.user.role.RoleDefinition;
import com.github.dickens.blogapp.user.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    RoleRepository roleRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value="users", produces = { "application/hal+json" })
    public Resources<User> getUsers() {
        Iterable<User> allUsers = userRepository.findAll();

        for (User user : allUsers) {
            Long userId = user.getUserId();
            Link selfLink = linkTo(UserController.class).slash("/users/"+userId).withSelfRel();
            user.add(selfLink);

            Iterable userPosts = postRepository.findByAuthor(userRepository.findById(userId).get());
            Iterator it = userPosts.iterator();
            if (it.hasNext()) {
                Link postsLink = linkTo(methodOn(PostController.class)
                        .getPostsByAuthor(userId)).slash("posts/all/"+userId).withRel("allPosts");
                user.add(postsLink);
            }
        }

        Link link = linkTo(UserController.class).withSelfRel();
        Resources<User> result = new Resources<User>(allUsers, link);
        return result;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        try {
            User userToDelete = userRepository.findById(userId).get();

            Iterable<Post> posts = postRepository.findByAuthor(userToDelete);
            for (Post post : posts) {
                postRepository.delete(post);
            }

            Iterable<Comment> comments = commentRepository.findByAuthor(userToDelete);
            for (Comment comment : comments) {
                commentRepository.delete(comment);
            }

            userRepository.delete(userToDelete);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No user with id "+userId+" was found.", ex);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "users/all")
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "users/role/{userId}/{isAdmin}")
    public ResponseEntity<?> editUserRole(@PathVariable Long userId, @PathVariable boolean isAdmin) {
        try {
            User user = userRepository.findById(userId).get();

            Role userRole = roleRepository.findByDefinition(RoleDefinition.ROLE_USER)
                    .orElseThrow(() -> new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR));
            Role adminRole = roleRepository.findByDefinition(RoleDefinition.ROLE_ADMIN)
                    .orElseThrow(() -> new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR));

            if (isAdmin) {
                user.setRoles(new ArrayList<>(Arrays.asList(userRole, adminRole)));
            } else {
                user.setRoles(new ArrayList<>(Collections.singleton(userRole)));
            }

            userRepository.save(user);

            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "Edited user's role with id "+userId+" succesfully."));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No user with id "+userId+" was found.", ex);
        }
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(value = "/users/details")
    @Transactional
    public UserDetails getDetails(Authentication authentication) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } else {
            return null;
        }
    }
}
