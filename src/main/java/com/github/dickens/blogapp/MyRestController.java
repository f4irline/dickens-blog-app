package com.github.dickens.blogapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Optional;

@RestController
public class MyRestController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;

    /*
    curl -H"Content-Type: application/json" -X POST -d {\"userName\":\"mkyong\",\"password\":\"abc\"} http://localhost:8080/user/add
     */
    @PostMapping(value = "/user/add")
    public void addUser(@RequestBody User user) {
        System.out.println("Post");
        userRepository.save(user);
    }

    /*
    curl -i GET http://localhost:8080/posts/4
    */
    @GetMapping(value = "/posts/{postId}")
    public Optional<Post> getPost(@PathVariable int postId) {
        Optional<Post> post = postRepository.findById(postId);
        System.out.println(post);
        return post;
    }

    @GetMapping(value = "/posts/all")
    public Iterable<Post> getAllPosts() {
        Iterable<Post> posts = postRepository.findAll();
        return posts;
    }
}
