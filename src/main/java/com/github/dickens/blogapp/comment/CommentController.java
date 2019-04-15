package com.github.dickens.blogapp.comment;

import com.github.dickens.blogapp.post.PostRepository;
import com.github.dickens.blogapp.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class CommentController {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;

    @PostMapping(value="comments/add/{postId}/{userId}")
    public void addComment(@RequestBody Comment comment, @PathVariable int postId,@PathVariable int userId) {
        comment.setPost(postRepository.findById(postId).get());
        comment.setAuthor(userRepository.findById(userId).get());
        commentRepository.save(comment);
    }

    @GetMapping(value = "comments/{postId}")
    public Iterable<Comment> getCommentsByPost(@PathVariable int postId) {
        return commentRepository.findCommentsByPost(postRepository.findById(postId).get());
    }

    @DeleteMapping(value = "comments/{commentId}")
    public void deleteComment(@PathVariable int commentId) {
        commentRepository.deleteById(commentId);
    }

    @DeleteMapping(value = "comments/all/{postId}")
    public void deleteCommentsByPost(@PathVariable int postId) {
        commentRepository.deleteAll(commentRepository.findCommentsByPost(postRepository.findById(postId).get()));
    }
}
