package com.example.footballmanager.exeption.handler;

import lombok.Data;
import org.springframework.validation.FieldError;

import java.io.Serializable;

/**
 * Dto for sending information about bad fields while validation.
 *
 */
@Data
public class ValidationExceptionDto implements Serializable {

    private String name;

    private String message;

    public ValidationExceptionDto(FieldError error) {
        this.name = error.getField();
        this.message = error.getDefaultMessage();
    }
}
