package com.github.dickens.blogapp.comment;

import com.github.dickens.blogapp.user.User;
import com.github.dickens.blogapp.post.Post;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Comment {
    @TableGenerator(name = "Comment_Gen",
            table = "COMMENT_ID_GEN",
            pkColumnName = "COMMENT_ID",
            valueColumnName = "GEN_VAL",
            pkColumnValue = "Comment_Gen",
            initialValue = 1000,
            allocationSize = 100)

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Comment_Gen")
    private Long commentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @Lob
    @Column
    private String body;
    private int likes;
    private LocalDateTime postDate = LocalDateTime.now();

    public Comment(){};

    public Comment(Post post, User user, String body) {
        this.post = post;
        this.author = user;
        this.body = body;
    }

    public Long getCommentId() {
        return commentId;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public LocalDateTime getPostDate() {
        return postDate;
    }
}
