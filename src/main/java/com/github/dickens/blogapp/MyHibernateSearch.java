package com.github.dickens.blogapp;

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

@Transactional
@Repository
public class MyHibernateSearch {
    @PersistenceContext
    EntityManager entityManager;

    public Iterable<Post> search(String text) {
        FullTextEntityManager fullTextEntityManager =
                org.hibernate.search.jpa.Search.
                getFullTextEntityManager(entityManager);

        QueryBuilder queryBuilder =
                fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(Post.class).get();

        org.apache.lucene.search.Query query =
                queryBuilder
                        .keyword()
                        .onFields("title")
                        .matching(text)
                        .createQuery();

        org.hibernate.search.jpa.FullTextQuery jpaQuery =
                fullTextEntityManager.createFullTextQuery(query, Post.class);

        System.out.println(jpaQuery.getResultSize() +""+ jpaQuery.getFirstResult());

        Iterable<Post> results = jpaQuery.getResultList();

        return results;
    }

}
