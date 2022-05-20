package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
public class JPQLQueryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void findByNameDescriptionIndexPosition(){
        List<Product> products = productRepository.findByNameAndDescriptionJPQL("redmi 5 pro", new BigDecimal(12999.00));
        products.forEach(p-> System.out.printf("Product: %s | description %s | price %f \n",p.getName(),p.getDescription(),p.getPrice()));
    }

    @Test
    void findByNameDescriptionNamingJPQL(){
        List<Product> products = productRepository.findByNameAndDescriptionNamingJPQL("redmi 5 pro", new BigDecimal(12999.00));
        products.forEach(p-> System.out.printf("Product: %s | description %s | price %f \n",p.getName(),p.getDescription(),p.getPrice()));
    }
}
