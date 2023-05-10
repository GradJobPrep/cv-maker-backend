package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class Education {
    private String nameOfDeegree;
    private String nameOfOrganization;
    private String durationOfDeegree;
    private String city;
    private String score;

}
