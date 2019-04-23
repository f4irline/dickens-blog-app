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
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
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
 * @author Ville-Veikko Nieminen, Tommi Lepola
 * @since 1.8
 * @version 2019-23-04
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
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(RoleDefinition.ROLE_ADMIN));
        roles.add(new Role(RoleDefinition.ROLE_USER));

        roleRepository.saveAll(roles);
    }

    /**
     * Creates users, intializes them and adds them to database using userRepository.
     */
    private void initUsers() {
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

        List<User> lista = new ArrayList<>();
        lista.add(heikki);
        lista.add(tiina);
        lista.add(tommi);

        userRepository.saveAll(lista);
    }

    /**
     * Creates posts and adds those to databse using postRepository.
     */
    private void initPosts() {
        Lorem lorem = LoremIpsum.getInstance();

        postRepository.save(new Post(userRepository.findById(1001L).get(), lorem.getTitle(4, 6), lorem.getHtmlParagraphs(15, 20), "https://images.unsplash.com/photo-1552664622-2cdfdf76ed0d?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80", Category.MOVIES));
        postRepository.save(new Post(userRepository.findById(1002L).get(), lorem.getTitle(4, 6),lorem.getHtmlParagraphs(15, 20), "https://images.unsplash.com/photo-1552688400-5e74ea0e494b?ixlib=rb-1.2.1&auto=format&fit=crop&w=1950&q=80", Category.HEALTH));
        postRepository.save(new Post(userRepository.findById(1003L).get(), lorem.getTitle(4, 6),lorem.getHtmlParagraphs(15, 20), "https://images.unsplash.com/photo-1552838357-602b552f57b7?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1947&q=80", Category.MUSIC));
        postRepository.save(new Post(userRepository.findById(1001L).get(), lorem.getTitle(4, 6),lorem.getHtmlParagraphs(15, 20), "https://images.unsplash.com/photo-1552838357-602b552f57b7?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1947&q=80", Category.TECH));
        postRepository.save(new Post(userRepository.findById(1002L).get(), lorem.getTitle(4, 6),lorem.getHtmlParagraphs(15, 20), "https://images.unsplash.com/photo-1552838357-602b552f57b7?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1947&q=80", Category.STUDIES));

    }

    /**
     * Creates comments and adds those to databse using commentRepository.
     */
    private void initComments() {
        Lorem lorem = LoremIpsum.getInstance();

        commentRepository.save(new Comment(postRepository.findById(1001L).get(),userRepository.findById(1001L).get(),lorem.getParagraphs(1, 3)));
        commentRepository.save(new Comment(postRepository.findById(1002L).get(),userRepository.findById(1001L).get(),lorem.getParagraphs(1, 3)));
        commentRepository.save(new Comment(postRepository.findById(1003L).get(),userRepository.findById(1002L).get(),lorem.getParagraphs(1, 3)));
        commentRepository.save(new Comment(postRepository.findById(1002L).get(),userRepository.findById(1002L).get(),lorem.getParagraphs(1, 3)));
        commentRepository.save(new Comment(postRepository.findById(1001L).get(),userRepository.findById(1002L).get(),lorem.getParagraphs(1, 3)));
    }

    /**
     * Calls methods to create and save data.
     */
    public void initData() {

        initRoles();

        initUsers();

        initPosts();

        initComments();
    }
}
