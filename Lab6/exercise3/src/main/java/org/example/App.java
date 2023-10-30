package org.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        TextEditor textEditor = context.getBean(TextEditor.class);

        textEditor.input("Spring is coming Love you to the moon and back!\nSave as pdf file");
        textEditor.save("textpdf.pdf");

    }
}
