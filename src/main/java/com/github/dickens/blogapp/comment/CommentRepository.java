package com.github.dickens.blogapp.comment;

import com.github.dickens.blogapp.post.Post;
import com.github.dickens.blogapp.user.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository to use database for comments
 *
 * @author Tommi Lepola
 * @author Ville-Veikko Nieminen
 * @since 1.0
 * @version 1.0
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
