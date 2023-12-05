package com.wizer.booklibrary.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CategoryNotFoundException extends RuntimeException{

    private HttpStatus status;

    public CategoryNotFoundException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
    
}
