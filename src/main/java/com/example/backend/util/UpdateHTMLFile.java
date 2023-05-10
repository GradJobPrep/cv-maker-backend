package com.example.backend.util;

import com.example.backend.model.*;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdateHTMLFile {

    public static Document updateProjectsInCV(User user) throws Exception {
        Document parentDocument = UpdateHTMLFile.getDocument("src/main/resources/templates/cv.html");
        parentDocument.getElementById("name").text(user.getFirstName() + " " + user.getLastName());
        parentDocument.getElementById("organization-name").text(user.getDesignation() + "," + user.getCurrentCompany());
        parentDocument.getElementById("yearsOfExperience").text(user.getYearsOfExperience());

        for (Project project : user.getProjectsWorkedUpon()) {
            Document projectHTML = UpdateHTMLFile.getDocument("src/main/resources/templates/project_template.html");
            projectHTML.getElementById("nameOfProject").text(project.getNameOfProject());
            projectHTML.getElementById("nameOfOrganization").text(project.getNameOfOrganization());
            projectHTML.getElementById("durationOfProject").text(project.getDurationOfProject());
            projectHTML.getElementById("city").text(project.getCity());
            projectHTML.getElementById("projectDescription").text(project.getProjectDescription());
            parentDocument.getElementById("project").appendChild(projectHTML.getElementById("project"));
        }

        for (Education education : user.getEducationalDetails()) {
            Document educationHTML = UpdateHTMLFile.getDocument("src/main/resources/templates/education_template.html");
            educationHTML.getElementById("nameOfDeegree").text(education.getNameOfDeegree());
            educationHTML.getElementById("nameOfOrganization").text(education.getNameOfOrganization());
            educationHTML.getElementById("durationOfDeegree").text(education.getDurationOfDeegree());
            educationHTML.getElementById("city").text(education.getCity());
            educationHTML.getElementById("score").text(education.getScore());
            parentDocument.getElementById("education").appendChild(educationHTML.getElementById("education"));
        }

        for (Certification certification : user.getListOfCertifications()) {
            Document certificationHTML = UpdateHTMLFile.getDocument("src/main/resources/templates/certifications_template.html");
            certificationHTML.getElementById("certification-name").text(certification.getName());
            certificationHTML.getElementById("certification-score").text("Scored " + certification.getScored() + " out of " + certification.getMaxScale());
            parentDocument.getElementById("certification").appendChild(certificationHTML.getElementById("certification"));
        }


        Map<Integer, String> skillMap = new HashMap<>();
        for (Skill skill : user.getSkills()) {
            Integer skillLevel = skill.getSkillLevel();
            String skillName = skill.getSkillName();
            if (skillMap.get(skillLevel) == null) {
                skillMap.put(skillLevel, skillName);
            } else {
                skillMap.put(skillLevel, skillMap.get(skillLevel) + ", " + skillName);
            }
        }

        for (int i = 5; i >= 1; i--) {
            if (skillMap.get(i) != null) {
                Document skillProficiencyHTML = UpdateHTMLFile.getDocument("src/main/resources/templates/skill_proficiency_template.html");
                Document skillLevel = UpdateHTMLFile.getDocument("src/main/resources/templates/skill-level/level-" + i + ".html");

                skillProficiencyHTML.getElementById("skill-name").text(skillMap.get(i));
                skillProficiencyHTML.getElementById("skill-level").appendChild(skillLevel.getElementById("skill-level"));
                parentDocument.getElementById("skill").appendChild(skillProficiencyHTML.getElementById("skill"));
            }
        }


        UpdateHTMLFile.saveDocument("src/main/resources/templates/output.html", parentDocument);
        return parentDocument;
    }

    public static Document getDocument(String filePath) throws Exception {
        Path input = Path.of(filePath);
        Document document = Jsoup.parse(Files.readString(input), StandardCharsets.UTF_8.toString(), Parser.xmlParser());
        return document;
    }

    public static Document saveDocument(String filePath, Document document) throws Exception {
        final File f = new File(filePath);
        FileUtils.writeStringToFile(f, document.html(), StandardCharsets.UTF_8);
        return document;
    }
}