package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        // exercise 2
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Product product1 = (Product) context.getBean("product1");
        Product product2 = (Product) context.getBean("product2");
        Product product3 = (Product) context.getBean("product3");

        // Print the product information to the console
        System.out.println(product1.toString());
        System.out.println(product2.toString());
        System.out.println(product3.toString());
    }
}
