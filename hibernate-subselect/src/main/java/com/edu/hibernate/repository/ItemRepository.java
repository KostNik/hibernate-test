package com.edu.hibernate.repository;


import com.edu.hibernate.model.Item;

import java.util.List;

/**
 * Created by Kostiuk Nikita
 */
public interface ItemRepository {

    void save(Item item);

    List<Item> getAll();
}
