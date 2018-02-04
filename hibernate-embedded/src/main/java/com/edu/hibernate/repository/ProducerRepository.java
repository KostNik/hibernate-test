package com.edu.hibernate.repository;


import com.edu.hibernate.model.Producer;

import java.util.List;

/**
 * Created by Kostiuk Nikita
 */
public interface ProducerRepository {

    void save(Producer producer);

    List<Producer> getAll();

    List<Producer> getAllNatively();

    Producer getByNameLike(String pattern);

    Producer getByID(Long id);

    void deleteByID(Long id);

    Producer updateByID(Long id, Producer producer);


}
