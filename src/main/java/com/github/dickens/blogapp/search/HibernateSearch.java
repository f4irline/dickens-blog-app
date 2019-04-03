package com.github.dickens.blogapp.search;

import com.github.dickens.blogapp.post.Post;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;

@Transactional
@Repository
public class HibernateSearch {
    @PersistenceContext
    EntityManager entityManager;

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