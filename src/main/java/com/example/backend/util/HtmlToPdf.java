package com.example.backend.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import com.example.backend.controller.CVMakerController;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xhtmlrenderer.layout.SharedContext;
import org.xhtmlrenderer.pdf.ITextRenderer;

public class HtmlToPdf {

    public static void main(String[] args) {
        try {
            // HTML file - Input
            final String baseDir = System.getProperty("user.dir");
            File inputHTML = new File(baseDir + "/src/main/resources/templates/cv.html");
            // Converted PDF file - Output
            File outputPdf = new File("D:\\Projects\\backend\\cv-test.pdf");
            HtmlToPdf htmlToPdf = new HtmlToPdf();
            //create well formed HTML
            String xhtml = htmlToPdf.createWellFormedHtml(inputHTML);
            System.out.println("Starting conversion to PDF...");
            htmlToPdf.xhtmlToPdf(xhtml, outputPdf);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private String createWellFormedHtml(File inputHTML) throws IOException {
        Document document = Jsoup.parse(inputHTML, "UTF-8");
        document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        System.out.println("HTML parsing done...");
        return document.html();
    }

    private void xhtmlToPdf(String xhtml, File outputPdf) throws IOException {
        OutputStream outputStream = null;
        try {
            ITextRenderer renderer = new ITextRenderer();
            SharedContext sharedContext = renderer.getSharedContext();
            sharedContext.setPrint(true);
            sharedContext.setInteractive(false);
            // Register custom ReplacedElementFactory implementation
            sharedContext.setReplacedElementFactory(new ReplacedElementFactoryImpl());
            sharedContext.getTextRenderer().setSmoothingThreshold(0);
            // Setting base URL to resolve the relative URLs
            String baseUrl = FileSystems.getDefault()
                    .getPath("D:\\", "Projects\\backend\\", "src\\main\\resources\\css")
                    .toUri()
                    .toURL()
                    .toString();
            renderer.setDocumentFromString(xhtml, baseUrl);
            renderer.layout();
            outputStream = new FileOutputStream(outputPdf);
            renderer.createPDF(outputStream);
            System.out.println("PDF creation completed");
        }finally {
            if(outputStream != null)
                outputStream.close();
        }
    }
}