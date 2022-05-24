package com.example.employe;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.FieldError;

@AllArgsConstructor
@Data
public class ResponseApi {
    private String message;
    private Integer status;
    private FieldError fieldError;
}
