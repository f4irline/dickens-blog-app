package com.github.dickens.blogapp.post;

import com.github.dickens.blogapp.Category;
import com.github.dickens.blogapp.user.User;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.TermVector;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Indexed
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
    @Field
    private String title;

    @Lob
    @Column
    private String body;

    @Lob
    @Column
    private String imgUrl;

    @Enumerated(EnumType.STRING)
    private Category category;

    private int likes;
    private LocalDateTime postDate = LocalDateTime.now();

    public Post(){}

    public Post(User author, String title, String body, String imgUrl, Category category) {
        this.author = author;
        this.title = title;
        this.body = body;
        this.imgUrl = imgUrl;
        this.likes = likes;
        this.category = category;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public Category getCategory() { return category; }

    public void setCategory(Category category) { this.category = category; }

    public LocalDateTime getPostDate() {
        return postDate;
    }
}
