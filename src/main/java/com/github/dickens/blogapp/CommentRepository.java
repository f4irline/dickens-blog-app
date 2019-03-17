package com.github.dickens.blogapp;

import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
    public Iterable<Comment> findCommentsByPost(Post post);
}
