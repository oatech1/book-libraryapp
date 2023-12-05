package com.wizer.booklibrary.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public class BookNotFoundException extends RuntimeException{

    private HttpStatus status;

    public BookNotFoundException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
    
}
