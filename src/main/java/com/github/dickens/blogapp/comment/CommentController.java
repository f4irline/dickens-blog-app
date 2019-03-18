package com.github.dickens.blogapp.comment;

import com.github.dickens.blogapp.post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class CommentController {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;

    @PostMapping(value="comments/add")
    public void addComment(@RequestBody Comment comment) {
        commentRepository.save(comment);
    }

    @GetMapping(value = "comments/{postId}")
    public Iterable<Comment> getCommentsByPost(@PathVariable int postId) {
        return commentRepository.findCommentsByPost(postRepository.findById(postId).get());
    }
}
