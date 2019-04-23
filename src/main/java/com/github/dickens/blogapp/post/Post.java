package com.github.dickens.blogapp.post;

import com.github.dickens.blogapp.Category;
import com.github.dickens.blogapp.comment.Comment;
import com.github.dickens.blogapp.user.User;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.TermVector;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Representing the Post.
 *
 * @author Tommi Lepola
 * @author Ville-Veikko Nieminen
 * @since 1.0
 * @version 1.0
 */
@Entity
@Table(name = "posts")
@Indexed
public class Post extends ResourceSupport {
    /**
     * Id for the post.
     */
    @TableGenerator(name = "Post_Gen",
            table = "POST_ID_GEN",
            pkColumnName = "POST_ID",
            valueColumnName = "GEN_VAL",
            pkColumnValue = "Post_Gen",
            initialValue = 1000,
            allocationSize = 100)

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Post_Gen")
    private Long postId;

    /**
     * User/author for the post.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    @IndexedEmbedded
    private User author;

    /**
     * List for the comments in the post.
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post", orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    /**
     * Title for the post.
     */
    @Field
    private String title;

    /**
     * Body for the post.
     */
    @Lob
    @Column
    private String body;

    /**
     * Url to image for the post.
     */
    @Lob
    @Column
    private String imgUrl;

    /**
     * Category for the post.
     */
    @Enumerated(EnumType.STRING)
    private Category category;

    /**
     * Amount of likes in the post.
     */
    private int likes;
    /**
     * Date for the post.
     */
    private LocalDateTime postDate = LocalDateTime.now();

    /**
     * Empty constructor for the post.
     */
    public Post(){}

    /**
     * Constructor for the post. Instantiates a new Post.
     *
     * @param author containing info about posts author
     * @param title containing title for the post
     * @param body containing body for the post
     * @param imgUrl containing url for the image of the post
     * @param category containing category for the post
     */
    public Post(User author, String title, String body, String imgUrl, Category category) {
        this.author = author;
        this.title = title;
        this.body = body;
        this.imgUrl = imgUrl;
        this.likes = likes;
        this.category = category;
    }

    /**
     * Getter for the post id.
     *
     * @return Long representing id of the post
     */
    public Long getPostId() {
        return postId;
    }

    /**
     * Setter for the post id.
     *
     * @param postId containing id for the post
     */
    public void setPostId(Long postId) {
        this.postId = postId;
    }

    /**
     * Getter for the author of the post.
     *
     * @return User representing the author of the post
     */
    public User getAuthor() {
        return author;
    }

    /**
     * Setter for the author of the post.
     *
     * @param author containing info about user/author
     */
    public void setAuthor(User author) {
        this.author = author;
    }

    /**
     * Getter for the title of the post.
     *
     * @return String representing title of the post
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for the title of the post.
     *
     * @param title containing title for the post
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter for the body of the post.
     *
     * @return String representing body of the post
     */
    public String getBody() {
        return body;
    }

    /**
     * Setter for the body of the post.
     *
     * @param body containing body for the post
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Getter for the url of the post image.
     *
     * @return String representing url of the image
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * Setter for the url of the post image.
     *
     * @param imgUrl containing url for the image
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * Getter for the amount of likes in the post.
     *
     * @return Integer representing amount of likes in the post
     */
    public int getLikes() {
        return likes;
    }

    /**
     * Setter for the likes in the post.
     *
     * @param likes containing amount of likes in the post
     */
    public void setLikes(int likes) {
        this.likes = likes;
    }

    /**
     * Getter for the category of the post.
     *
     * @return Catefory representing category of the post
     */
    public Category getCategory() { return category; }

    /**
     * Setter for the category of the post.
     *
     * @param category containing category for the post
     */
    public void setCategory(Category category) { this.category = category; }

    /**
     * Getter for the date of the post.
     *
     * @return LocalDateTime representing date of the post
     */
    public LocalDateTime getPostDate() {
        return postDate;
    }
}
