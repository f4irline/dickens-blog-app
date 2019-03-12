package com.github.dickens.blogapp;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.GetMapping;

public interface PostRepository extends CrudRepository<Post, Integer> {

}
