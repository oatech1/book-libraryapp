package com.wizer.booklibrary.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;



@Builder


public record BaseResponse<T>(HttpStatus status, String message, T result) {

    public BaseResponse(HttpStatus status, String message, T result) {
        this.status = status;
        this.message = message;
        this.result = result;

    }
}






