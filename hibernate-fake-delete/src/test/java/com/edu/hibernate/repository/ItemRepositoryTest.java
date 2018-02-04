package com.edu.hibernate.repository;

import com.edu.hibernate.config.RootConfig;
import com.edu.hibernate.model.Item;
import com.edu.hibernate.model.ItemState;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by Kostiuk Nikita
 */

@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Before
    public void setUp() {
        for (int i = 0; i < 5; i++) {
            Item item = new Item();
            item.setName("Name_" + i);
            item.setState(ItemState.ACTIVE);
            itemRepository.save(item);
        }
    }

    @Test
    public void deleteByID() {
        List<Item> all = itemRepository.getAll();
        log.info("ALL items {}", all);

        itemRepository.deleteByID(3L);

        List<Item> all_active_after = itemRepository.getAll();
        log.info("ALL ACTIVE items {}", all_active_after);

        List<Item> allNatively = itemRepository.getAllNatively();
        log.info("ALL REAL items {}", allNatively);

        assertEquals(4, all_active_after.size());
        assertEquals(5, allNatively.size());


    }

}