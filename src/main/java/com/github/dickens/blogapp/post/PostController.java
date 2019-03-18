package com.github.dickens.blogapp.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class PostController {

    @Autowired
    PostRepository postRepository;

    @PostMapping
    public void addPost(@RequestBody Post post) {
        postRepository.save(post);
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
