package com.github.dickens.blogapp.comment;

import com.github.dickens.blogapp.user.User;
import com.github.dickens.blogapp.post.Post;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Representing the Comment.
 *
 * @author Tommi Lepola
 * @author Ville-Veikko Nieminen
 * @since 1.0
 * @version 1.0
 */
@Entity
public class Comment {
    /**
     * Id for the comment.
     */
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

    /**
     * Post where comment belongs to.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    /**
     * User that has left the comment.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    /**
     * Comments body.
     */
    @Lob
    @Column
    private String body;
    /**
     * Amount of likes in the comment.
     */
    private int likes;
    /**
     * Date when comment is posted.
     */
    private LocalDateTime postDate = LocalDateTime.now();

    /**
     * Empty Constructor.
     */
    public Comment(){};

    /**
     * Constructor for comment.
     *
     * @param post containing info about post
     * @param user containing info about author
     * @param body containing body for comment
     */
    public Comment(Post post, User user, String body) {
        this.post = post;
        this.author = user;
        this.body = body;
    }

    /**
     * Getter for comments id.
     *
     * @return Long representing id of the comment
     */
    public Long getCommentId() {
        return commentId;
    }

    /**
     * Getter for the post that comment is part of.
     *
     * @return Post representing the post that comment is part of
     */
    public Post getPost() {
        return post;
    }

    /**
     * Setter for the post that comment is part of.
     *
     * @param post containing info about post
     */
    public void setPost(Post post) {
        this.post = post;
    }

    /**
     * Getter for comments author.
     *
     * @return User representing the author for the comment
     */
    public User getAuthor() {
        return author;
    }

    /**
     * Setter for comments author.
     *
     * @param author containing info about user
     */
    public void setAuthor(User author) {
        this.author = author;
    }

    /**
     * Getter for the body of the comment
     *
     * @return String representing the body of the comment
     */
    public String getBody() {
        return body;
    }

    /**
     * Setter for the body of the comment.
     *
     * @param body containing info about the body of the comment
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Getter for the likes of the comment.
     *
     * @return Integer representing amount of likes in the comment
     */
    public int getLikes() {
        return likes;
    }

    /**
     * Setter for the likes of the comment.
     *
     * @param likes containing amount of likes in the comment
     */
    public void setLikes(int likes) {
        this.likes = likes;
    }

    /**
     * Getter for date comment has been posted.
     *
     * @return LocalDateTime representing comments post date
     */
    public LocalDateTime getPostDate() {
        return postDate;
    }
}
