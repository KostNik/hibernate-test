package com.edu.hibernate;

/**
 * Created by Kostiuk Nikita
 */

import com.edu.hibernate.repository.ItemRepository;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap {


    private final ItemRepository itemRepository;

    public Bootstrap(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    @EventListener(ContextRefreshedEvent.class)
    public void initContext() {

//        fillDB();

    }


}
