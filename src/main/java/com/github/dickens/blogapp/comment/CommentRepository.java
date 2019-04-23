package com.github.dickens.blogapp.comment;

import com.github.dickens.blogapp.post.Post;
import com.github.dickens.blogapp.user.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository to use database for comments
 *
 * @author Ville-Veikko Nieminen
 * @version 1.8
 * @since 2019-04-21
 */
public interface CommentRepository extends CrudRepository<Comment, Long> {
    /**
     * Returns Iterable containing all comments by post.
     *
     * @param post containing info about post
     * @return Iterable representing comments
     */
    public Iterable<Comment> findCommentsByPost(Post post);

    /**
     * Returns Iterable containing comments bu author.
     *
     * @param author containing info about user
     * @return Iterable containing comments
     */
    public Iterable<Comment> findByAuthor(User author);
}
