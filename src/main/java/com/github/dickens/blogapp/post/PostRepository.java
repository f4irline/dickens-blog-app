package com.github.dickens.blogapp.post;

import com.github.dickens.blogapp.Category;
import com.github.dickens.blogapp.user.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository to use database for posts
 *
 * @author Ville-Veikko Nieminen
 * @author Tommi Lepola
 * @since 1.0
 * @version 1.0
 */
public interface PostRepository extends CrudRepository<Post, Long> {
    /**
     * Returns Iterable containing posts by category.
     *
     * @param category containing category of the posts
     * @return Iterable representing posts
     */
    Iterable<Post> findByCategory(Category category);

    /**
     * Returns Iterable containing posts by author.
     *
     * @param author containing author of the posts
     * @return Iterable representing posts
     */
    Iterable<Post> findByAuthor(User author);
}
