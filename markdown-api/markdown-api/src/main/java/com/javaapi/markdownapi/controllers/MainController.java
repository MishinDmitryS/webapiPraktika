package com.javaapi.markdownapi.controllers;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.javaapi.markdownapi.service.HtmlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;

@Controller
public class MainController {
    @Autowired
    private HtmlService htmlService;

    @Value("${upload.path}")
    String uploadPath;

    //String inputHtmlPath = "C:\\Users\\Dmitry\\Desktop\\papka\\document.html";
    //String outputPdfPath = "C:\\Users\\Dmitry\\Desktop\\papka\\test.pdf";
    String inputHtmlPath = "C:/Users/Dmitry/Desktop/papka/document.html";
    String outputPdfPath = "C:/Users/Dmitry/Desktop/papka/test.pdf";
    String outputPdfPath2 = "C:/Users/Dmitry/Desktop/papka/test2.pdf";

    @RequestMapping("/redactor")
    public String index() {
        //model.addAttribute("name", "World");
        return "redactor";
    }

    public void downl(String markdown) {
        try {
            OutputStream file = new FileOutputStream(new File(uploadPath + "test2.pdf"));
            Document document = new Document();
            PdfWriter.getInstance(document, file);
            document.open();
            HTMLWorker htmlWorker = new HTMLWorker(document);
            htmlWorker.parse(new StringReader(markdown));
            document.close();
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/previewFile")
    public ModelAndView previewFile(@RequestParam String markdown) {
        ModelAndView modelAndView = new ModelAndView("previewFile");
        String htmlContent = htmlService.markdownToHtml(markdown);
        downl(markdown);
        modelAndView.addObject("htmlContent", htmlContent);
        return modelAndView;
    }

    @PostMapping("/redactor")
    public void downloadFile(@RequestParam String  markdown) throws IOException, DocumentException {
        try {
            OutputStream file = new FileOutputStream(new File(uploadPath + "test2.pdf"));
            Document document = new Document();
            PdfWriter.getInstance(document, file);
            document.open();
            HTMLWorker htmlWorker = new HTMLWorker(document);
            htmlWorker.parse(new StringReader(markdown));
            document.close();
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}