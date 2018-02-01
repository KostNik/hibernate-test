package com.edu.hibernate;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Kostiuk Nikita
 */
public class TestApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.edu");
        context.start();

    }
}
