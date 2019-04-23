package com.github.dickens.blogapp.post;

import com.github.dickens.blogapp.Category;
import com.github.dickens.blogapp.comment.Comment;
import com.github.dickens.blogapp.comment.CommentController;
import com.github.dickens.blogapp.comment.CommentRepository;
import com.github.dickens.blogapp.search.HibernateSearch;
import com.github.dickens.blogapp.user.UserController;
import com.github.dickens.blogapp.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Controller for the comment.
 *
 * @author Ville -Veikko Nieminen, Tommi Lepola
 * @version 2019 -23-04
 * @since 1.8
 */
@RestController
@RequestMapping(value = "/api")
public class PostController {

    /**
     * CrudRepository for the post.
     */
    @Autowired
    PostRepository postRepository;
    /**
     * CrudRepository for the user.
     */
    @Autowired
    UserRepository userRepository;
    /**
     * CrudRepository for the comment.
     */
    @Autowired
    CommentRepository commentRepository;
    /**
     * The Hibernate search.
     */
    @Autowired
    HibernateSearch hibernateSearch;

    /**
     * Gets posts for HATEOAS.
     *
     * @return Resources containing posts and links for HATEOAS
     */
    @GetMapping(value = "posts", produces = {"application/hal+json"})
    public Resources<Post> getPosts() {
        Iterable<Post> allPosts = postRepository.findAll();
        for(Post post : allPosts) {
            Long postId = post.getPostId();
            Link selfLink = linkTo(PostController.class).slash("/posts/"+postId).withSelfRel();
            post.add(selfLink);

            Iterable<Comment> allComments = commentRepository.findCommentsByPost(postRepository.findById(postId).get());
            Iterator it = allComments.iterator();
            if(it.hasNext()) {
                Link commentsLink = linkTo(methodOn(CommentController.class).getCommentsByPost(postId))
                        .slash("comments/"+postId).withRel("allComments");
                post.add(commentsLink);
            }
        }
        Link link = linkTo(PostController.class).withSelfRel();
        Resources<Post> result = new Resources<>(allPosts,link);
        return result;
    }

    /**
     * Returns Iterable containing posts by author.
     *
     * @param userId containing id of the user
     * @return Iterable representing posts
     */
    @GetMapping(value = "posts/all/{userId}")
    public Iterable<Post> getPostsByAuthor(@PathVariable Long userId) {
        return postRepository.findByAuthor(userRepository.findById(userId).get());
    }


    /**
     * Returns Iterable containing posts found by search value.
     *
     * @param text containing search value
     * @return Iterable representing posts
     */
    @GetMapping(value = "posts/search/{text}")
    public Iterable<Post> searchPosts(@PathVariable String text) {
        System.out.println(text);
        Iterable<Post> results = null;
        try {
            results = hibernateSearch.search(text);
        } catch (Exception e) {
            System.out.println(e);
        }
        return results;
    }

    /**
     * Adds post to database using postRepository.
     *
     * @param post containing info about post
     * @param userId containing id of the user
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value ="posts/add/{userId}")
    public void addPost(@RequestBody Post post, @PathVariable Long userId) {
        post.setAuthor(userRepository.findById(userId).get());
        postRepository.save(post);
    }

    /**
     * Returns post by id of the post
     *
     * @param postId containing id of the post
     * @return optional post representing the post
     */
    @GetMapping(value = "/posts/{postId}")
    public Optional<Post> getPost(@PathVariable Long postId) {
        return postRepository.findById(postId);
    }

    /**
     * Returns Iterable containing posts by category.
     *
     * @param category containing the category
     * @return Iterable representing the posts
     */
    @GetMapping(value = "/posts/category/{category}")
    public Iterable<Post> getPostByCategory(@PathVariable("category") Category category) {
        return postRepository.findByCategory(category);
    }

    /**
     * Return Iterable containing all posts.
     *
     * @return Iterable representing posts
     */
    @GetMapping(value = "/posts/all")
    public Iterable<Post> getAllPosts() {
        return postRepository.findAll();
    }

    /**
     * Delete post from database using postRepository.
     *
     * @param postId containing id of the post
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "posts/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postRepository.deleteById(postId);
    }

}
