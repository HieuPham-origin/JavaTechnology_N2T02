package org.example;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Qualifier
public class PdfTextWriter implements TextWriter{
    @Override
    public void write(String fileName, String text) throws IOException {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(new File(fileName)));
            document.open();
            document.add(new Paragraph(text));
        } catch (DocumentException e) {
            throw new IOException("Error creating PDF file", e);
        } finally {
            document.close();
        }
    }

}
