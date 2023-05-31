package com.Intuit.handlers.dtos;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class ExceptionResponseDto {
    private Timestamp timestamp;
    private HttpStatus httpStatus;
    private String message;
    private String path;

    public ExceptionResponseDto(HttpStatus httpStatus,String message){
        this.timestamp=Timestamp.valueOf(LocalDateTime.now());
        this.httpStatus=httpStatus;
        this.message=message;
        this.path= ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString();
    }
}
