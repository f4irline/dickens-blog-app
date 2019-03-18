package com.github.dickens.blogapp.post;

import com.github.dickens.blogapp.user.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Post {
    @TableGenerator(name = "Post_Gen",
            table = "POST_ID_GEN",
            pkColumnName = "POST_ID",
            valueColumnName = "GEN_VAL",
            pkColumnValue = "Post_Gen",
            initialValue = 1000,
            allocationSize = 100)

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Post_Gen")
    private int postId;
    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User author;
    private String title;

    @Lob
    @Column
    private String body;
    private int likes;
    private LocalDateTime postDate = LocalDateTime.now();

    public Post(){}

    public Post(User author, String title, String body) {
        this.author = author;
        this.title = title;
        this.body = body;
        this.likes = likes;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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