package com.github.dickens.blogapp.comment;

import com.github.dickens.blogapp.post.PostRepository;
import com.github.dickens.blogapp.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for the comment.
 *
 * @author Ville-Veikko Nieminen, Tommi Lepola
 * @since 1.8
 * @version 2019-23-04
 */
@RestController
@RequestMapping(value = "/api")
public class CommentController {

    /**
     * CrudRepository for the comment.
     */
    @Autowired
    CommentRepository commentRepository;
    /**
     * CrudRepository for the post.
     */
    @Autowired
    PostRepository postRepository;
    /**
     * CrudRepository for the user.
     */
    @Autowired
    UserRepository userRepository;

    /**
     * Adds comment to the database via commentRepository.
     *
     * @param comment containing info about comment
     * @param postId containing id for the post
     * @param userId containing id for the user/author
     */
    @PreAuthorize("hasRole('USER')")
    @PostMapping(value="comments/add/{postId}/{userId}")
    public void addComment(@RequestBody Comment comment, @PathVariable Long postId,@PathVariable Long userId) {
        comment.setPost(postRepository.findById(postId).get());
        comment.setAuthor(userRepository.findById(userId).get());
        commentRepository.save(comment);
    }

    /**
     * Returns Iterable containing all comments by post using commentRepository.
     *
     * @param postId containing id for the post
     * @return Iterable representing comments
     */
    @GetMapping(value = "comments/{postId}")
    public Iterable<Comment> getCommentsByPost(@PathVariable Long postId) {
        return commentRepository.findCommentsByPost(postRepository.findById(postId).get());
    }

    /**
     * Delete comment from database using commentRepository.
     *
     * @param commentId containing id for the comment
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "comments/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentRepository.deleteById(commentId);
    }

    /**
     * Delete comments by post from database using commentRepository.
     *
     * @param postId containing id of the post
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "comments/all/{postId}")
    public void deleteCommentsByPost(@PathVariable Long postId) {
        commentRepository.deleteAll(commentRepository.findCommentsByPost(postRepository.findById(postId).get()));
    }
}
