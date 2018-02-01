package com.edu.hibernate.repository;

import com.edu.hibernate.model.Category;
import com.google.common.collect.ImmutableList;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Kostiuk Nikita
 */

@Repository
@Transactional
public class BaseCategoryRepository implements CategoryRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void save(Category category) {
        entityManager.persist(category);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Category> getAll() {
        return ImmutableList.copyOf(entityManager.createNamedQuery("Category.getAll", Category.class).getResultList());

    }
}
