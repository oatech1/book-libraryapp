package com.wizer.booklibrary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorManager handleBookNotFoundException(BookNotFoundException ex){
        String message = ex.getMessage();
        return ErrorManager.builder()
                .errorMessage(ex.getMessage())
                .status(ex.getStatus())
                .timeStamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT))
                .build();
    }
    @ExceptionHandler(CategoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorManager handleCategoryNotFoundException(CategoryNotFoundException ex){
        String message = ex.getMessage();
        return ErrorManager.builder()
                .errorMessage(ex.getMessage())
                .status(ex.getStatus())
                .timeStamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT))
                .build();
    }



}
