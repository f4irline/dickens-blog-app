package com.github.dickens.blogapp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Post {

    @Id
    @GeneratedValue
    private int postId;
    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User author;
    private String title;
    private String body;
    private int likes;
    private LocalDate postDate = LocalDate.now();

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

    public LocalDate getPostDate() {
        return postDate;
    }
}
