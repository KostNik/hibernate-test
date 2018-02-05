package com.edu.hibernate.repository;

import com.edu.hibernate.config.RootConfig;
import com.edu.hibernate.model.Address;
import com.edu.hibernate.model.ContactInfo;
import com.edu.hibernate.model.Producer;
import com.edu.hibernate.model.Telephone;
import org.hibernate.internal.util.Cloneable;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Kostiuk Nikita
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class ProducerRepositoryTest {

    public static final int    MAX_RESULT = 5;
    public static final String NAME       = "Name_1";

    @Autowired
    private ProducerRepository producerRepository;

    @Before
    public void setUp() throws Exception {
        for (int i = 0; i < MAX_RESULT; i++) {
            Producer producer = getProducer(i);
            producerRepository.save(producer);
        }

    }

    private Producer getProducer(int i) {
        Producer producer = new Producer();
        Address home = new Address();
        home.setCity("Home_City_" + i);
        home.setCountry("Home_Country_" + i);

        Address work = new Address();
        work.setCity("Work_City_" + i);
        work.setCountry("Work_Country_" + i);

        Telephone workTel = new Telephone("+(work_tel)_" + i);
        Telephone homeTel = new Telephone("+(home_tel)_" + i);

        producer.setName("Name_" + i);
        producer.setHome(new ContactInfo(home, homeTel));
        producer.setWork(new ContactInfo(work, workTel));
        return producer;
    }

    @Transactional
    @Test
    public void testGetAll() {
        List<Producer> producers = producerRepository.getAll();
        assertEquals(MAX_RESULT, producers.size());
    }

    @Transactional
    @Test
    public void testGetById() {
        Producer producerByName = producerRepository.getByNameLike(NAME);
        Long id = producerByName.getId();
        Producer producer = producerRepository.getByID(id);
        assertNotNull(producer);
        ContactInfo home = producer.getHome();
        assertNotNull(home);
    }

    @Transactional
    @Test
    public void testUpdateById() {
        Producer producerByName = producerRepository.getByNameLike(NAME);
        Long id = producerByName.getId();

        Producer producer = producerRepository.getByID(id);
        String country = producer.getHome().getAddress().getCountry();
        String newHomeCountry = "Lapland";
        producer.getHome().getAddress().setCountry(newHomeCountry);
        producerRepository.updateByID(producer.getId(), producer);
        Producer producer_updated = producerRepository.getByID(id);

        String country1 = producer_updated.getHome().getAddress().getCountry();
        assertNotEquals(country, country1);
        assertEquals(newHomeCountry, country1);
    }
}