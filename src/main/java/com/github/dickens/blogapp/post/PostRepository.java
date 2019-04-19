package com.github.dickens.blogapp.post;

import com.github.dickens.blogapp.Category;
import com.github.dickens.blogapp.user.User;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
    Iterable<Post> findByCategory(Category category);

    Iterable<Post> findByAuthor(User author);
}
