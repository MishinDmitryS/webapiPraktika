package com.javaapi.markdownapi.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class DownloadFile {
    private HtmlService htmlService;
    String inputHtmlPath = "C:/Users/Dmitry/Desktop/papka/document.html";
    String outputPdfPath = "C:/Users/Dmitry/Desktop/papka/test.pdf";

    public void download(String markdown) throws IOException, DocumentException {
        String htmlContent = htmlService.markdownToHtml(markdown);
        Document document = new Document();
        // Создаем writer для записи в pdf
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(outputPdfPath));
        // Открываем для чтения html страничку
        document.open();
        // Парсим её и записываем в PDF
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, new ByteArrayInputStream(htmlContent.getBytes()));
        document.close();
    }
}
