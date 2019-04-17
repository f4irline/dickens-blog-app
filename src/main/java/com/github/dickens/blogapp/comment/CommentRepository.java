package com.github.dickens.blogapp.comment;

import com.github.dickens.blogapp.post.Post;
import com.github.dickens.blogapp.user.User;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    public Iterable<Comment> findCommentsByPost(Post post);
    public Iterable<Comment> findByAuthor(User author);
}
