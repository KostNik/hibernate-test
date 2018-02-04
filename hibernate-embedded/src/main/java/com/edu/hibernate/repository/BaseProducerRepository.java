package com.edu.hibernate.repository;

import com.edu.hibernate.model.Producer;
import com.google.common.collect.ImmutableList;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;

/**
 * Created by Kostiuk Nikita
 */
@Repository
@Transactional
public class BaseProducerRepository implements ProducerRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void save(Producer producer) {
        entityManager.persist(producer);
    }

    @Override
    public List<Producer> getAll() {
        TypedQuery<Producer> typedQuery = entityManager.createNamedQuery("Producer.getAll", Producer.class);
        List<Producer> resultList = typedQuery.getResultList();
        return ImmutableList.copyOf(resultList);
    }

    @Override
    public List<Producer> getAllNatively() {
        Query nativeQuery = entityManager.createNativeQuery("SELECT * FROM Producer", Producer.class);
        return nativeQuery.getResultList();
    }

    @Override
    public Producer getByNameLike(String pattern) {
        TypedQuery<Producer> typedQuery = entityManager.createNamedQuery("Producer.findByName", Producer.class);
        Producer singleResult = typedQuery.getSingleResult();
        return singleResult;
    }

    @Override
    public Producer getByID(Long id) {
        return entityManager.find(Producer.class, id);
    }

    @Override
    public void deleteByID(Long id) {
        Producer producer = entityManager.find(Producer.class, id);
        if (producer != null) {
            entityManager.remove(producer);
        }
    }

    @Override
    public Producer updateByID(Long id, Producer producer) {
        producer.setId(id);
        entityManager.merge(producer);
        return producer;
    }

}
