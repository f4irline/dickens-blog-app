package com.github.dickens.blogapp.post;

import com.github.dickens.blogapp.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PostRepository extends CrudRepository<Post, Integer> {
    Iterable<Post> findByCategory(Category category);
}
