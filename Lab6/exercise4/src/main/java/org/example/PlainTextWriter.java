package org.example;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Qualifier
@Component("plainTextWriter")
public class PlainTextWriter implements TextWriter{
    @Override
    public void write(String fileName, String text) throws IOException {
        BufferedWriter writer = null;
        try{
            writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(text);
        }finally{
            if(writer != null){
                writer.close();
            }
        }
    }
}
