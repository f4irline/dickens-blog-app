package com.github.dickens.blogapp.comment;

import com.github.dickens.blogapp.post.Post;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
    public Iterable<Comment> findCommentsByPost(Post post);
}
