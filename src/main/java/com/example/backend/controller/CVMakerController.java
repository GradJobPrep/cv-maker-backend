package com.example.backend.controller;

import com.example.backend.model.User;
import com.example.backend.util.ConvertHTMLToPDF;
import com.example.backend.util.UpdateHTMLFile;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Path;

@RestController
public class CVMakerController {

    private static final Logger logger = LoggerFactory.getLogger(CVMakerController.class);


    @PostMapping(value = "/backend")
    public ResponseEntity<byte[]> createCV(@RequestBody User user) throws Exception {
        System.out.println("hello");
        System.out.println(user);

        logger.info("Creating CV based on rquest: "+user);
        Document document = UpdateHTMLFile.updateProjectsInCV(user);
        ConvertHTMLToPDF convertHTMLToPDF= new ConvertHTMLToPDF();
        String filePath = convertHTMLToPDF.convertHTMltoPDF(document);
        logger.info("Created CV successfully");
        return new ResponseEntity<byte[]>(Files.readAllBytes(Path.of(filePath)), HttpStatus.OK);
    }
}
