package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CVMakerResponse {
    String status;
    String s3Url;
    String ErrorMessage;
}
