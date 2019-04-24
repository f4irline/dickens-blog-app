package com.github.dickens.blogapp.post;

import com.github.dickens.blogapp.Category;
import com.github.dickens.blogapp.comment.Comment;
import com.github.dickens.blogapp.comment.CommentController;
import com.github.dickens.blogapp.comment.CommentRepository;
import com.github.dickens.blogapp.search.HibernateSearch;
import com.github.dickens.blogapp.security.auth.ApiResponse;
import com.github.dickens.blogapp.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Iterator;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Controller for the comment.
 *
 * @author Tommi Lepola
 * @author Ville -Veikko Nieminen
 * @since 2019.0312
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/api")
public class PostController {

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

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
    @PreAuthorize("hasRole('ADMIN')")
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
        try {
            return postRepository.findByAuthor(userRepository.findById(userId).get());
        } catch (Exception ex) {
            logger.error(ex.toString());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No posts for author with id "+userId+" was found.", ex);
        }
    }


    /**
     * Returns Iterable containing posts found by search value.
     *
     * @param text containing search value
     * @return Iterable representing posts
     */
    @Transactional
    @GetMapping(value = "posts/search/{text}")
    public Iterable<Post> searchPosts(@PathVariable String text) {
        try {
            return hibernateSearch.search(text);
        } catch (Exception ex) {
            logger.error(ex.toString());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No posts for with text "+text+" was found.", ex);
        }
    }

    /**
     * Adds post to database using postRepository.
     *
     * @param post containing info about post
     * @param userId containing id of the user
     * @param b builder related to this action to build a location URI for response headers.
     * @return HTTP status 201 when succesful.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value ="posts/add/{userId}")
    public ResponseEntity<?> addPost(@RequestBody Post post, @PathVariable Long userId, UriComponentsBuilder b) {
        post.setAuthor(userRepository.findById(userId).get());
        postRepository.save(post);

        UriComponents components = b.path("/posts/{postId}").buildAndExpand(post.getPostId());

        return ResponseEntity.status(HttpStatus.CREATED).location(components.toUri()).body(new ApiResponse(true, "Created new post succesfully."));
    }

    /**
     * Returns post by id of the post
     *
     * @param postId containing id of the post
     * @return optional post representing the post
     */
    @GetMapping(value = "/posts/{postId}")
    public Post getPost(@PathVariable Long postId) {
        try {
            return postRepository.findById(postId).get();
        } catch (Exception ex) {
            logger.error(ex.toString());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No posts with id "+postId+" was found.", ex);
        }
    }

    /**
     * Returns Iterable containing posts by category.
     *
     * @param category containing the category
     * @return Iterable representing the posts
     */
    @Transactional
    @GetMapping(value = "/posts/category/{category}")
    public Iterable<Post> getPostByCategory(@PathVariable("category") Category category) {
        try {
            return postRepository.findByCategory(category);
        } catch (Exception ex) {
            logger.error(ex.toString());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No posts with category "+category+" was found.", ex);
        }
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
     * @return HTTP status no content if succesful
     * @throws ResponseStatusException if no posts with given id was found.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        try {
            postRepository.deleteById(postId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception ex) {
            logger.error(ex.toString());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No posts with id "+postId+" was found.", ex);
        }
    }

}
