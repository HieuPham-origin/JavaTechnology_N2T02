package org.example;

import org.springframework.beans.factory.annotation.Qualifier;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Qualifier
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
