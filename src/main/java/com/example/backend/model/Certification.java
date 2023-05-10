package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Certification {
    private String name;
    private Integer maxScale;
    private Integer scored;

}
