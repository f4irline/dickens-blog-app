package com.github.dickens.blogapp.search;

import com.github.dickens.blogapp.post.Post;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;

/**
 * Hibernate search handles searching.
 *
 * @author Ville-Veikko Nieminen
 * @since 1.8
 * @version 2019-23-04
 */
@Transactional
@Repository
public class HibernateSearch {
    /**
     * The Entity manager.
     */
    @PersistenceContext
    EntityManager entityManager;

    /**
     * Returns Iterable containing Post found with search value.
     *
     * @param text containgin the search value
     * @return Iterable representing posts
     */
    public Iterable<Post> search(String text) {
        Iterable<Post> results = new ArrayList<>();
        org.apache.lucene.search.Query query;
        org.hibernate.search.jpa.FullTextQuery jpaQuery;

        FullTextEntityManager fullTextEntityManager =
                org.hibernate.search.jpa.Search.
                getFullTextEntityManager(entityManager);

        QueryBuilder queryBuilder =
                fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(Post.class).get();


        if(text.startsWith("\"") && text.endsWith("\"")) {
            query = queryBuilder.phrase().onField("title").andField("author.userWhole").sentence(text.replace("\"","")).createQuery();
            jpaQuery =
                    fullTextEntityManager.createFullTextQuery(query, Post.class);
            results = jpaQuery.getResultList();
        } else {
            query =
                    queryBuilder
                            .keyword()
                            .fuzzy()
                            .withPrefixLength(1)
                            .withEditDistanceUpTo(1)
                            .onFields("title", "author.userFirst", "author.userLast")
                            .matching(text)
                            .createQuery();

            jpaQuery =
                    fullTextEntityManager.createFullTextQuery(query, Post.class);

            if (jpaQuery.getResultSize() == 0) {
                query =
                        queryBuilder
                                .keyword()
                                .wildcard()
                                .onFields("title", "author.userFirst", "author.userLast")
                                .matching(text + "*")
                                .createQuery();

                jpaQuery =
                        fullTextEntityManager.createFullTextQuery(query, Post.class);
                results = jpaQuery.getResultList();
            } else {
                results = jpaQuery.getResultList();
            }
        }
        return results;
    }

}
