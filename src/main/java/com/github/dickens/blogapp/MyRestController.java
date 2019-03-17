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
    @Autowired
    CommentRepository commentRepository;

    /*
    curl -H"Content-Type: application/json" -X POST -d {\"userName\":\"mkyong\",\"password\":\"abc\"} http://localhost:8080/user/add
     */
    @PostMapping(value = "/user/add")
    public void addUser(@RequestBody User user) {
        userRepository.save(user);
    }

    /*
    curl -i -X GET http://localhost:8080/posts/4
    */
    @GetMapping(value = "/posts/{postId}")
    public Optional<Post> getPost(@PathVariable int postId) {
        System.out.println("get");
        return postRepository.findById(postId);
    }

    /*
    curl -i -X GET http://localhost:8080/posts/all
    */
    @GetMapping(value = "/posts/all")
    public Iterable<Post> getAllPosts() {
        return postRepository.findAll();
    }

    /*
    curl -i -X DELETE http://localhost:8080/posts/4
    */
    @DeleteMapping(value = "posts/{postId}")
    public void deletePost(@PathVariable int postId) {
        postRepository.deleteById(postId);
    }
}
