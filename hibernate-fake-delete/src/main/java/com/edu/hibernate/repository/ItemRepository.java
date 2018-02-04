package com.edu.hibernate.repository;


import com.edu.hibernate.model.Item;

import java.util.List;

/**
 * Created by Kostiuk Nikita
 */
public interface ItemRepository {

    void save(Item item);

    List<Item> getAll();

    List<Item> getAllNatively();

    Item getByNameLike(String pattern);

    Item getByID(Long id);

    void deleteByID(Long id);


}
