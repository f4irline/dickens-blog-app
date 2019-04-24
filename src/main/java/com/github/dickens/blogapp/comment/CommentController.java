package com.github.dickens.blogapp.comment;

import com.github.dickens.blogapp.post.PostRepository;
import com.github.dickens.blogapp.security.auth.ApiResponse;
import com.github.dickens.blogapp.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Controller for the comment.
 *
 * @author Tommi Lepola
 * @author Ville-Veikko Nieminen
 * @since 2019.0317
 * @version 1.0
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
    public ResponseEntity<?> addComment(@RequestBody Comment comment, @PathVariable Long postId,@PathVariable Long userId, UriComponentsBuilder b) {
        comment.setPost(postRepository.findById(postId).get());
        comment.setAuthor(userRepository.findById(userId).get());
        commentRepository.save(comment);

        UriComponents components = b.path("/comments/{commentId}").buildAndExpand(comment.getCommentId());

        return ResponseEntity.status(HttpStatus.CREATED).location(components.toUri()).body(new ApiResponse(true, "Created new comment succesfully."));
    }

    /**
     * Returns Iterable containing all comments by post using commentRepository.
     *
     * @param postId containing id for the post
     * @return Iterable representing comments
     */
    @GetMapping(value = "comments/{postId}")
    public Iterable<Comment> getCommentsByPost(@PathVariable Long postId) {
        try {
            return commentRepository.findCommentsByPost(postRepository.findById(postId).get());
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No comments in post with id "+postId+" was found.", ex);
        }
    }

    /**
     * Delete comment from database using commentRepository.
     *
     * @param commentId containing id for the comment
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId) {
        try {
            commentRepository.deleteById(commentId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No comment with id "+commentId+" was found.", ex);
        }
    }

    /**
     * Delete comments by post from database using commentRepository.
     *
     * @param postId containing id of the post
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "comments/all/{postId}")
    public ResponseEntity<?> deleteCommentsByPost(@PathVariable Long postId) {
        try {
            commentRepository.deleteAll(commentRepository.findCommentsByPost(postRepository.findById(postId).get()));
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No comments in post with id "+postId+" was found.", ex);
        }
    }
}
