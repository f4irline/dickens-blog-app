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

@RestController
@RequestMapping(value = "/api")
public class PostController {

    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    HibernateSearch hibernateSearch;

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

    @GetMapping(value = "posts/all/{userId}")
    public Iterable<Post> getPostsByAuthor(@PathVariable Long userId) {
        return postRepository.findByAuthor(userRepository.findById(userId).get());
    }


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

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value ="posts/add/{userId}")
    public void addPost(@RequestBody Post post, @PathVariable Long userId) {
        post.setAuthor(userRepository.findById(userId).get());
        postRepository.save(post);
    }

    @GetMapping(value = "/posts/{postId}")
    public Optional<Post> getPost(@PathVariable Long postId) {
        return postRepository.findById(postId);
    }

    @GetMapping(value = "/posts/category/{category}")
    public Iterable<Post> getPostByCategory(@PathVariable("category") Category category) {
        return postRepository.findByCategory(category);
    }

    @GetMapping(value = "/posts/all")
    public Iterable<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "posts/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postRepository.deleteById(postId);
    }

}
