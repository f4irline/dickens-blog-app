package com.github.dickens.blogapp;

import javax.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue
    private int commentId;
    @OneToOne
    @JoinColumn(name ="POST_ID")
    private Post post;
    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;
    private String body;
    private int likes;

    public Comment(Post post, User user, String body) {
        this.post = post;
        this.user = user;
        this.body = body;
    }

    public int getCommentId() {
        return commentId;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}
