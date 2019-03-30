package com.github.dickens.blogapp.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dickens.blogapp.user.role.Role;
import com.github.dickens.blogapp.utils.HashUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Transient
    HashUtils utils = new HashUtils();

    @TableGenerator(name = "User_Gen",
            table = "USER_ID_GEN",
            pkColumnName = "USER_ID",
            valueColumnName = "GEN_VAL",
            pkColumnValue = "User_Gen",
            initialValue = 1000,
            allocationSize = 100)

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "User_Gen")
    private Long userId;

    @NotBlank
    @Size(min = 4, max = 40)
    private String userName;

    @NotBlank
    @Size(min = 4, max = 40)
    private String userFirst;

    @NotBlank
    @Size(min = 4, max = 40)
    private String userLast;

    @JsonIgnore
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {

    }

    public User(String userName, String userFirst, String userLast, String password) {
        this.userName = userName;
        this.userFirst = userFirst;
        this.userLast = userLast;
        this.password = utils.hashMyPassword(password);
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserFirst() {
        return userFirst;
    }

    public void setUserFirst(String userFirst) {
        this.userFirst = userFirst;
    }

    public String getUserLast() {
        return userLast;
    }

    public void setUserLast(String userLast) {
        this.userLast = userLast;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = utils.hashMyPassword(password);
    }

    public Set<Role> getRoles() { return roles; }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
