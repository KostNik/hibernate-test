package com.edu.hibernate.repository;


import com.edu.hibernate.model.Category;

import java.util.List;

/**
 * Created by Kostiuk Nikita
 */
public interface CategoryRepository {

    Category save(Category category);

    List<Category> getAll();

    Category getById(Long id);

    List getItemsByCategoryId(Long id);
}
