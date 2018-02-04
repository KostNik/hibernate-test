package com.edu.hibernate.repository;

import com.google.common.collect.ImmutableList;
import com.edu.hibernate.model.Item;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Kostiuk Nikita
 */
@Repository
@Transactional
public class BaseItemRepository implements ItemRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void save(Item item) {
        entityManager.persist(item);
    }

    @Override
    public List<Item> getAll() {
        TypedQuery<Item> typedQuery = entityManager.createNamedQuery("Item.getAll", Item.class);
        List<Item> resultList = typedQuery.getResultList();
        return ImmutableList.copyOf(resultList);
    }

    @Override
    public List<Item> getAllNatively() {
        Query nativeQuery = entityManager.createNativeQuery("SELECT * FROM Item", Item.class);
        return nativeQuery.getResultList();
    }

    @Override
    public Item getByNameLike(String pattern) {
        TypedQuery<Item> typedQuery = entityManager.createNamedQuery("Item.findByName", Item.class);
        Item singleResult = typedQuery.getSingleResult();
        return singleResult;
    }

    @Override
    public Item getByID(Long id) {
        return entityManager.find(Item.class, id);
    }

    @Override
    public void deleteByID(Long id) {
        Item item = entityManager.find(Item.class, id);
        if (item != null) {
            entityManager.remove(item);
        }
    }

}
