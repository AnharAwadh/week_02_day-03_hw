package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.FieldError;

@AllArgsConstructor
@Data
class ResponseApi {
    private String message;
    private Integer status;
    private FieldError fieldError;

}