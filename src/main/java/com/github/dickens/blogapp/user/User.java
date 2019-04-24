package com.github.dickens.blogapp.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.dickens.blogapp.comment.Comment;
import com.github.dickens.blogapp.post.Post;
import com.github.dickens.blogapp.user.role.Role;
import com.github.dickens.blogapp.utils.HashUtils;
import org.hibernate.search.annotations.Field;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;

/**
 * User entity object for database implementation.
 *
 * @author Tommi Lepola
 * @author Ville-Veikko Nieminen
 * @version 3.0
 * @since 2019.0312
 */
@Entity
@Table(name = "users")
public class User extends ResourceSupport {

    /**
     * Utilities for password hashing.
     */
    @Transient
    HashUtils utils = new HashUtils();

    /**
     * Generator which is used to generate user's ID the way we want.
     */
    @TableGenerator(name = "User_Gen",
            table = "USER_ID_GEN",
            pkColumnName = "USER_ID",
            valueColumnName = "GEN_VAL",
            pkColumnValue = "User_Gen",
            initialValue = 1000,
            allocationSize = 100)

    /**
     * User's ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "User_Gen")
    private Long userId;

    /**
     * User's username.
     */
    @NotBlank
    @Size(min = 4, max = 40)
    private String userName;

    /**
     * User's first name.
     */
    @NotBlank
    @Field
    @Size(min = 4, max = 40)
    private String userFirst;

    /**
     * User's last name.
     */
    @NotBlank
    @Field
    @Size(min = 4, max = 40)
    private String userLast;

    /**
     * User's whole name.
     */
    @Field
    private String userWhole;

    /**
     * User's password.
     */
    @JsonIgnore
    private String password;

    /**
     * User's roles joined with the role table.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    /**
     * Posts that user has made.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "author")
    private Collection<Post> posts;

    /**
     * Comments that user has made.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "author")
    private Collection<Comment> comments;

    public User() {

    }

    /**
     * Constructor for user entity.
     *
     * @param userName the user's name to use when logging in.
     * @param userFirst the user's first name.
     * @param userLast the user's last name.
     * @param password the user's password.
     */
    public User(String userName, String userFirst, String userLast, String password) {
        this.userName = userName;
        this.userFirst = userFirst;
        this.userLast = userLast;
        this.password = utils.hashMyPassword(password);
        this.userWhole = userFirst+" "+userLast;
    }

    /**
     * Returns user ID.
     *
     * @return user ID.
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets user ID.
     *
     * @param userId user ID.
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Returns user's username.
     *
     * @return user's username.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets user's username.
     *
     * @param userName user's username.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Returns user's first name.
     *
     * @return user's first name.
     */
    public String getUserFirst() {
        return userFirst;
    }

    /**
     * Sets user's first name.
     *
     * @param userFirst user's first name.
     */
    public void setUserFirst(String userFirst) {
        this.userFirst = userFirst;
    }

    /**
     * Returns user's last name.
     *
     * @return user's last name.
     */
    public String getUserLast() {
        return userLast;
    }

    /**
     * Sets user's last name.
     *
     * @param userLast user's last name.
     */
    public void setUserLast(String userLast) {
        this.userLast = userLast;
    }

    /**
     * Returns user's whole name.
     *
     * @return user's whole name.
     */
    public String getWholeName() {return userWhole;}

    /**
     * Returns user's password.
     *
     * @return user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Hashes and set's users password.
     *
     * @param password user's password.
     */
    public void setPassword(String password) {
        this.password = utils.hashMyPassword(password);
    }

    /**
     * Returns user's roles.
     *
     * @return user's roles.
     */
    public Collection<Role> getRoles() { return roles; }

    /**
     * Sets user's roles.
     *
     * @param roles user's roles.
     */
    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    /**
     * Returns posts that user has made.
     *
     * @return posts that user has made.
     */
    public Collection<Post> getPosts() {
        return posts;
    }

    /**
     * Sets posts that user has made.
     *
     * @param posts posts that user has made.
     */
    public void setPosts(Collection<Post> posts) {
        this.posts = posts;
    }
}
