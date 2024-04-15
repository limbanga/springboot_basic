package com.example.demo.exceptions;


import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CustomValidationException
        extends Exception {

    public CustomValidationException(String field, String message) {
        super(message);
        this.field = field;
    }

    private String field;
}
