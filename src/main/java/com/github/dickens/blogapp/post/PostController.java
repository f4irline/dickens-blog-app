package com.github.dickens.blogapp.post;

import com.github.dickens.blogapp.Category;
import com.github.dickens.blogapp.comment.Comment;
import com.github.dickens.blogapp.comment.CommentController;
import com.github.dickens.blogapp.comment.CommentRepository;
import com.github.dickens.blogapp.search.HibernateSearch;
import com.github.dickens.blogapp.security.auth.ApiResponse;
import com.github.dickens.blogapp.user.UserController;
import com.github.dickens.blogapp.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Iterator;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api")
public class PostController {

    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    HibernateSearch hibernateSearch;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "posts", produces = {"application/hal+json"})
    public Resources<Post> getPosts() {
        Iterable<Post> allPosts = postRepository.findAll();
        for(Post post : allPosts) {
            Long postId = post.getPostId();
            Link selfLink = linkTo(PostController.class).slash("/posts/"+postId).withSelfRel();
            post.add(selfLink);

            Iterable<Comment> allComments = commentRepository.findCommentsByPost(postRepository.findById(postId).get());
            Iterator it = allComments.iterator();
            if(it.hasNext()) {
                Link commentsLink = linkTo(methodOn(CommentController.class).getCommentsByPost(postId))
                        .slash("comments/"+postId).withRel("allComments");
                post.add(commentsLink);
            }
        }
        Link link = linkTo(PostController.class).withSelfRel();
        Resources<Post> result = new Resources<>(allPosts,link);
        return result;
    }

    @GetMapping(value = "posts/all/{userId}")
    public Iterable<Post> getPostsByAuthor(@PathVariable Long userId) {
        try {
            return postRepository.findByAuthor(userRepository.findById(userId).get());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No posts for author with id "+userId+" was found.", ex);
        }
    }


    @GetMapping(value = "posts/search/{text}")
    public Iterable<Post> searchPosts(@PathVariable String text) {
        try {
            return hibernateSearch.search(text);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No posts for with text "+text+" was found.", ex);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value ="posts/add/{userId}")
    public ResponseEntity<?> addPost(@RequestBody Post post, @PathVariable Long userId, UriComponentsBuilder b) {
        post.setAuthor(userRepository.findById(userId).get());
        postRepository.save(post);

        UriComponents components = b.path("/posts/{postId}").buildAndExpand(post.getPostId());

        return ResponseEntity.status(HttpStatus.CREATED).location(components.toUri()).body(new ApiResponse(true, "Created new post succesfully."));
    }

    @GetMapping(value = "/posts/{postId}")
    public Post getPost(@PathVariable Long postId) {
        try {
            return postRepository.findById(postId).get();
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No posts with id "+postId+" was found.", ex);
        }
    }

    @GetMapping(value = "/posts/category/{category}")
    public Iterable<Post> getPostByCategory(@PathVariable("category") Category category) {
        try {
            return postRepository.findByCategory(category);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No posts with category "+category+" was found.", ex);
        }
    }

    @GetMapping(value = "/posts/all")
    public Iterable<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        try {
            postRepository.deleteById(postId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No posts with id "+postId+" was found.", ex);
        }
    }

}
