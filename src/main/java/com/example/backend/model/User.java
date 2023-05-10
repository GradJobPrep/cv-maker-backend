package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class User {
    private String firstName;
    private String lastName;
    private String designation;
    private String currentCompany;
    private String yearsOfExperience;
    private String email;
    private String mobile;
    private String linkedInUrl;

    private List<Project> projectsWorkedUpon;

    private List<Education> educationalDetails;
    private List<Certification> listOfCertifications;
    List<Skill> skills;
    List<String> hobbies;
}
