package com.edu.hibernate;

import com.edu.hibernate.config.RootConfig;
import com.edu.hibernate.model.Category;
import com.edu.hibernate.model.Item;
import com.edu.hibernate.model.ItemCategorySummary;
import com.edu.hibernate.repository.CategoryRepository;
import com.edu.hibernate.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Created by Kostiuk Nikita
 */

@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class SubselectTest {


    private static final int MAX_ELEMENTS = 10;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Before
    public void setUp() {
        fillDB();
    }

    @Test
    public void testItemCategorySummaryEntity() {
        List<Item> items = itemRepository.getAll();
        items.forEach(i -> log.info("Item: {}", i));

        List<Category> categories = categoryRepository.getAll();
        categories.forEach(i -> log.info("Category: {}", i));

        TypedQuery<ItemCategorySummary> namedQuery = entityManager.createNamedQuery("ItemCategorySummary.byID", ItemCategorySummary.class);
        namedQuery.setParameter("id", "2");
        ItemCategorySummary singleResult = namedQuery.getSingleResult();
        log.info("ItemCategorySummary: {}", singleResult);

        assertEquals(MAX_ELEMENTS, singleResult.getNumberOfItems());
    }

    private void fillDB() {

        for (int i = 1; i <= 3; i++) {
            Category category = new Category();
            category.setName("Cat_" + i);
            Category saved = categoryRepository.save(category);
            log.info("Saved Category {}", saved);
        }

        generateAndSaveItems(2L);

        generateAndSaveItems(1L);


    }

    private void generateAndSaveItems(long categoryId) {
        for (int i = 0; i < MAX_ELEMENTS; i++) {

            Item item = new Item();
            item.setName("F_" + i);
            item.setDescription("D_" + i);
            item.setAuctionEnd(Date.from(LocalDateTime.now().plus(Duration.ofDays(i * i * 10)).toInstant(ZoneOffset.UTC)));
            Category category = categoryRepository.getById(categoryId);
            item.setCategory(category);
            itemRepository.save(item);
        }
    }


}
