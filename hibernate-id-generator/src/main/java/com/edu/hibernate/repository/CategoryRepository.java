package com.edu.hibernate.repository;

import com.edu.hibernate.model.Category;
import com.edu.hibernate.model.Item;

import java.util.List;

/**
 * Created by Kostiuk Nikita
 */
public interface CategoryRepository {

    void save(Category category);

    List<Category> getAll();
}
