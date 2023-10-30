package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

    @Bean
    public Product product1() {
        Product product1 = new Product(1, "iPhone X" , 209.9,"The fisrt phone are used face ID");
        return product1;
    }

    @Bean
    public Product product2() {
        Product product2 = new Product(2, "iPhone 11" , 359.9,"New model in 2019");
        return product2;
    }

    @Bean
    @Scope("singleton")
    public Product product3() {
        Product product3 = new Product(3, "iPhone 15", 899.9, "New product in 2023");
        return product3;
    }
}

