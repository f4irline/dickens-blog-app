package com.github.dickens.blogapp;

import com.github.dickens.blogapp.utils.DataInit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for this app.
 *
 * @author Ville-Veikko Nieminen
 * @author Tommi Lepola
 * @since 1.8
 * @version 2019-23-04
 */
@SpringBootApplication
public class BlogApp implements CommandLineRunner {

	/**
	 * The Data init.
	 */
	@Autowired
    DataInit dataInit;

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(BlogApp.class, args);
	}

	/**
	 * Run method called from main method. Calls initData() -method for setting some data to app.
	 *
	 * @param args containing the input arguments
	 * @throws Exception exception
	 */
	@Override
	public void run(String... args) throws Exception {
        dataInit.initData();
    }
}
