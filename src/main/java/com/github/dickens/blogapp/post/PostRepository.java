package com.github.dickens.blogapp.post;

import com.github.dickens.blogapp.Category;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
    Iterable<Post> findByCategory(Category category);
}
