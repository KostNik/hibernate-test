package com.edu.hibernate;

import com.edu.hibernate.config.RootConfig;
import com.edu.hibernate.model.Category;
import com.edu.hibernate.model.Item;
import com.edu.hibernate.repository.CategoryRepository;
import com.edu.hibernate.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.TemporalAmount;
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
public class IDGeneratorTest {

    private static final int MAX_ELEMENTS = 10;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public IDGeneratorTest() {
    }

    @Before
    public void setUp() {
        fillDB();
    }

    @Test
    public void testIDGenerator() {
        List<Item> items = itemRepository.getAll();
        items.forEach(i -> log.info("Item: {}", i));

        List<Category> categories = categoryRepository.getAll();
        categories.forEach(i -> log.info("Category: {}", i));

        Item item_last = items.get(items.size() - 1);
        Category category_last = categories.get(categories.size() - 1);
        Long id_max = item_last.getId() > category_last.getId() ? item_last.getId() : category_last.getId();


        assertEquals(new Long(1019), id_max);

    }

    private void fillDB() {
        for (int i = 0; i < MAX_ELEMENTS; i++) {

            Category category = new Category();
            category.setName("Cat_" + i);
            categoryRepository.save(category);

            Item item = new Item();
            item.setName("F_" + i);
            item.setDescription("D_" + i);
            item.setAuctionEnd(Date.from(LocalDateTime.now().plus(Duration.ofDays(i * i * 10)).toInstant(ZoneOffset.UTC)));


            item.setCategory(category);
            itemRepository.save(item);
        }


    }

}
