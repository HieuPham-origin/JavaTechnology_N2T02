package org.example;

/**
 * Hello world!
 *
 */
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
@Configuration
@ComponentScan
public class App
{
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        TextEditor textEditor = context.getBean(TextEditor.class);

        textEditor.input("hello tdtu, Spring is coming!!");
        textEditor.save("test.txt");

        context.close();
    }
}
