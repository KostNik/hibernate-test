package com.edu.hibernate.repository;

import com.edu.hibernate.model.Item;
import com.google.common.collect.ImmutableList;
import com.edu.hibernate.model.Category;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
    public Category save(Category category) {
        entityManager.persist(category);
        return category;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Category> getAll() {
        return ImmutableList.copyOf(entityManager.createNamedQuery("Category.getAll", Category.class).getResultList());

    }

    @Override
    public Category getById(Long id) {
        return entityManager.find(Category.class, id);
    }

    @Override
    public List<Item> getItemsByCategoryId(Long id) {
        Query query = entityManager.createQuery("select i from Item i join Category c on c.id = i.category.id where c.id= :id");
        query.setParameter("id", id);
        List resultList = query.getResultList();
        return resultList;
    }
}
