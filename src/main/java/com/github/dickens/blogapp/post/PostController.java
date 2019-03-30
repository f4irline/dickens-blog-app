package com.github.dickens.blogapp.post;

import com.github.dickens.blogapp.Category;
import com.github.dickens.blogapp.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class PostController {

    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value ="posts/add/{userId}")
    public void addPost(@RequestBody Post post, @PathVariable Long userId) {
        post.setAuthor(userRepository.findById(userId).get());
        postRepository.save(post);
    }

    @GetMapping(value = "/posts/{postId}")
    public Optional<Post> getPost(@PathVariable Long postId) {
        return postRepository.findById(postId);
    }

    @GetMapping(value = "/posts/category/{category}")
    public Iterable<Post> getPostByCategory(@PathVariable("category") Category category) {
        return postRepository.findByCategory(category);
    }

    @GetMapping(value = "/posts/all")
    public Iterable<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "posts/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postRepository.deleteById(postId);
    }

}
