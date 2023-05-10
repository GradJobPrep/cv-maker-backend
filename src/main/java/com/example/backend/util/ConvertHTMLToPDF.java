package com.example.backend.util;

import org.jsoup.nodes.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class ConvertHTMLToPDF {

    public String convertHTMltoPDF(Document document) throws Exception {
        final String baseDir = System.getProperty("user.dir");
        System.out.println("About to convert html to pdf");
        File output = new File(baseDir + "\\cv.pdf");
        ITextRenderer renderer = new ITextRenderer();

        renderer.getSharedContext()
                .setUserAgentCallback(new CustomOpenPdfUserAgent(renderer.getOutputDevice()));
        renderer.getSharedContext().setReplacedElementFactory(new ReplacedElementFactoryImpl());

//        renderer.setDocument("templates/output.html");
        renderer.setDocumentFromString(document.html());
        renderer.layout();
        OutputStream outputStream = new FileOutputStream(output);
        renderer.createPDF(outputStream);
        outputStream.close();
        return baseDir + "\\cv.pdf";
    }
}
