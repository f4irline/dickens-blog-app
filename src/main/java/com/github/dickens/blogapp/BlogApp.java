package com.github.dickens.blogapp;

import com.github.dickens.blogapp.user.User;
import com.github.dickens.blogapp.user.UserRepository;
import com.github.dickens.blogapp.comment.Comment;
import com.github.dickens.blogapp.comment.CommentRepository;
import com.github.dickens.blogapp.post.Post;
import com.github.dickens.blogapp.post.PostRepository;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BlogApp implements CommandLineRunner {

	@Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

	public static void main(String[] args) {
		SpringApplication.run(BlogApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	    Lorem lorem = LoremIpsum.getInstance();

        User heikki = new User("Hexa","Heiki","Kinnunen","Koirannimi",User.USER);
        User tiina = new User("Tintti","Tiina","Kullitettu","Tiina96",User.USER);
        User tommi = new User("Tombha","Tomi","Reikämies","GhostRider98",User.ADMIN);
        List<User> lista = new ArrayList<>();
        lista.add(heikki);
        lista.add(tiina);
        lista.add(tommi);

		userRepository.saveAll(lista);
		//user author, String title, String body, int likes
		postRepository.save(new Post(heikki, lorem.getTitle(4, 6), lorem.getHtmlParagraphs(15, 20), "https://images.unsplash.com/photo-1552664622-2cdfdf76ed0d?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80", Category.CULTURE));
        postRepository.save(new Post(tiina, lorem.getTitle(4, 6),lorem.getHtmlParagraphs(15, 20), "https://images.unsplash.com/photo-1552688400-5e74ea0e494b?ixlib=rb-1.2.1&auto=format&fit=crop&w=1950&q=80", Category.HEALTH));
        postRepository.save(new Post(tommi, lorem.getTitle(4, 6),lorem.getHtmlParagraphs(15, 20), "https://images.unsplash.com/photo-1552838357-602b552f57b7?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1947&q=80", Category.POLITICS));
        postRepository.save(new Post(heikki, lorem.getTitle(4, 6),lorem.getHtmlParagraphs(15, 20), "https://images.unsplash.com/photo-1552838357-602b552f57b7?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1947&q=80", Category.TECH));
        postRepository.save(new Post(tommi, lorem.getTitle(4, 6),lorem.getHtmlParagraphs(15, 20), "https://images.unsplash.com/photo-1552838357-602b552f57b7?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1947&q=80", Category.STUDIES));

        commentRepository.save(new Comment(postRepository.findById(1001).get(),userRepository.findById(1001).get(),lorem.getParagraphs(1, 3)));
        commentRepository.save(new Comment(postRepository.findById(1002).get(),userRepository.findById(1001).get(),lorem.getParagraphs(1, 3)));
        commentRepository.save(new Comment(postRepository.findById(1003).get(),userRepository.findById(1002).get(),lorem.getParagraphs(1, 3)));
        commentRepository.save(new Comment(postRepository.findById(1002).get(),userRepository.findById(1002).get(),lorem.getParagraphs(1, 3)));
        commentRepository.save(new Comment(postRepository.findById(1001).get(),userRepository.findById(1002).get(),lorem.getParagraphs(1, 3)));
    }
}
