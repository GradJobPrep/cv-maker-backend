package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class Project {
    private String nameOfProject;
    private String nameOfOrganization;
    private String durationOfProject;
    private String projectDescription;
    private String city;
}
