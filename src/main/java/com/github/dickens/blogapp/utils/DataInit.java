package com.github.dickens.blogapp.utils;

import com.github.dickens.blogapp.Category;
import com.github.dickens.blogapp.comment.Comment;
import com.github.dickens.blogapp.comment.CommentRepository;
import com.github.dickens.blogapp.post.Post;
import com.github.dickens.blogapp.post.PostRepository;
import com.github.dickens.blogapp.user.User;
import com.github.dickens.blogapp.user.UserRepository;
import com.github.dickens.blogapp.user.role.Role;
import com.github.dickens.blogapp.user.role.RoleDefinition;
import com.github.dickens.blogapp.user.role.RoleRepository;
import com.google.common.collect.Iterables;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class for putting some data in application.
 *
 * @author Tommi Lepola
 * @author Ville-Veikko Nieminen
 * @since 2019.0330
 * @version 1.0
 */
@Component
public class DataInit {

    /**
     * CrudRepository for the user.
     */
    @Autowired
    UserRepository userRepository;

    /**
     * CrudRepository for the post.
     */
    @Autowired
    PostRepository postRepository;

    /**
     * CrudRepository for the comment.
     */
    @Autowired
    CommentRepository commentRepository;

    /**
     * CrudRepository for the role.
     */
    @Autowired
    RoleRepository roleRepository;


    /**
     * Creates roles and saves it to database using roleRepository.
     */
    private void initRoles() {

        if (Iterables.size(roleRepository.findAll()) > 0) {
            return;
        }

        List<Role> roles = new ArrayList<>();
        roles.add(new Role(RoleDefinition.ROLE_ADMIN));
        roles.add(new Role(RoleDefinition.ROLE_USER));

        roleRepository.saveAll(roles);
    }

    /**
     * Creates users, initializes them and adds them to database using userRepository.
     */
    private void initUsers() {
        if (Iterables.size(userRepository.findAll()) > 0) {
            return;
        }

        Role userRole = roleRepository.findByDefinition(RoleDefinition.ROLE_USER)
                .orElseThrow(() -> new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR));
        Role adminRole = roleRepository.findByDefinition(RoleDefinition.ROLE_ADMIN)
                .orElseThrow(() -> new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR));

        User heikki = new User("Hexa","Heikki","Kinnunen","Koirannimi");

        List<Role> roles = new ArrayList<>();
        roles.add(userRole);
        roles.add(adminRole);
        heikki.setRoles(roles);

        User tiina = new User("Tintti","Tiina","Salonen","Tiina96");
        tiina.setRoles(Collections.singleton(userRole));

        User tommi = new User("Tombha","Tomi","Syrjänen","GhostRider98");
        tommi.setRoles(Collections.singleton(userRole));

        List<User> listOfUsers = new ArrayList<>();
        listOfUsers.add(heikki);
        listOfUsers.add(tiina);
        listOfUsers.add(tommi);

        userRepository.saveAll(listOfUsers);
    }

    /**
     * Creates posts and adds those to database using postRepository.
     */
    private void initPosts() {
        if (Iterables.size(postRepository.findAll()) > 0) {
            return;
        }

        Lorem lorem = LoremIpsum.getInstance();

        postRepository.save(new Post(userRepository.findById(1001L).get(), lorem.getTitle(4, 6), lorem.getHtmlParagraphs(15, 20), "https://images.unsplash.com/photo-1552664622-2cdfdf76ed0d?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80", Category.MOVIES));
        postRepository.save(new Post(userRepository.findById(1002L).get(), lorem.getTitle(4, 6),lorem.getHtmlParagraphs(15, 20), "https://images.unsplash.com/photo-1552688400-5e74ea0e494b?ixlib=rb-1.2.1&auto=format&fit=crop&w=1950&q=80", Category.HEALTH));
        postRepository.save(new Post(userRepository.findById(1003L).get(), lorem.getTitle(4, 6),lorem.getHtmlParagraphs(15, 20), "https://images.unsplash.com/photo-1552838357-602b552f57b7?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1947&q=80", Category.MUSIC));
        postRepository.save(new Post(userRepository.findById(1001L).get(), lorem.getTitle(4, 6),lorem.getHtmlParagraphs(15, 20), "https://images.unsplash.com/photo-1552838357-602b552f57b7?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1947&q=80", Category.TECH));
        postRepository.save(new Post(userRepository.findById(1002L).get(), lorem.getTitle(4, 6),lorem.getHtmlParagraphs(15, 20), "https://images.unsplash.com/photo-1552838357-602b552f57b7?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1947&q=80", Category.STUDIES));

    }

    /**
     * Creates comments and adds those to database using commentRepository.
     */
    private void initComments() {
        if (Iterables.size(commentRepository.findAll()) > 0) {
            return;
        }

        Lorem lorem = LoremIpsum.getInstance();

        commentRepository.save(new Comment(postRepository.findById(1001L).get(),userRepository.findById(1001L).get(),lorem.getParagraphs(1, 3)));
        commentRepository.save(new Comment(postRepository.findById(1002L).get(),userRepository.findById(1001L).get(),lorem.getParagraphs(1, 3)));
        commentRepository.save(new Comment(postRepository.findById(1003L).get(),userRepository.findById(1002L).get(),lorem.getParagraphs(1, 3)));
        commentRepository.save(new Comment(postRepository.findById(1002L).get(),userRepository.findById(1002L).get(),lorem.getParagraphs(1, 3)));
        commentRepository.save(new Comment(postRepository.findById(1001L).get(),userRepository.findById(1002L).get(),lorem.getParagraphs(1, 3)));
    }

    /**
     * Exposes curl commands explained.
     */
    private void exposeCurlCommands() {
        Logger logger = LoggerFactory.getLogger(DataInit.class);
        logger.info("CURL COMMANDS FOR REST TESTING: ");
        logger.info("Register: ");
        logger.info("\"curl -v -XPOST -H \"Content-type: application/json\" -d '{\"userName\": \"Test\", \"password\": \"12345\", \"userFirst\":\"TestFirst\", \"userLast\":\"TestLast\"}' 'http://localhost:8080/api/auth/register'\"");
        logger.info("------------------------------------------------------------------");
        logger.info("Login with admin: ");
        logger.info("\"curl -v -XPOST -H \"Content-type: application/json\" -d '{\"userName\":\"Hexa\", \"password\":\"Koirannimi\"}' 'http://localhost:8080/api/auth/login'\"");
        logger.info("!! SAVE YOUR ACCESS TOKEN FROM THE RESPONSE. !!");
        logger.info("------------------------------------------------------------------");
        logger.info("Get all posts: ");
        logger.info("\"curl -v 'http://localhost:8080/api/posts/all'\"");
        logger.info("------------------------------------------------------------------");
        logger.info("Get comments from a post: ");
        logger.info("\"curl -v 'http://localhost:8080/api/comments/1001\"");
        logger.info("------------------------------------------------------------------");
        logger.info("Get all posts (HATEOAS with Admin rights)");
        logger.info("\"curl -v -XGET -H 'Authorization: Bearer <YOUR ACCESS TOKEN HERE>' 'http://localhost:8080/api/posts'\"");
        logger.info("------------------------------------------------------------------");
        logger.info("Create a post");
        logger.info("\"curl -v -XPOST -H 'Authorization: Bearer <YOUR ACCESS TOKEN HERE>' -H \"Content-type: application/json\" -d '{\"body\": \"Test post\", \"category\": \"TECH\", \"imgUrl\": \"https://images.unsplash.com/photo-1556038024-07f7daf0b84f?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1951&q=80\", \"title\": \"Test title\"}' 'http://localhost:8080/api/posts/add/1001'\"");
        logger.info("------------------------------------------------------------------");
        logger.info("Create a comment");
        logger.info("\"curl -v -XPOST -H 'Authorization: Bearer <YOUR ACCESS TOKEN HERE>' -H \"Content-type: application/json\" -d '{\"body\": \"Test comment\"}' 'http://localhost:3000/api/comments/add/1001/1001'\"");
        logger.info("------------------------------------------------------------------");
        logger.info("Delete a post");
        logger.info("\"curl -v -XDELETE -H 'Authorization: Bearer <YOUR ACCESS TOKEN HERE>' 'http://localhost:8080/api/posts/1005'\"");
    }

    /**
     * Calls methods to create and save data.
     */
    public void initData() {
        initRoles();
        initUsers();
        initPosts();
        initComments();
        exposeCurlCommands();
    }
}
