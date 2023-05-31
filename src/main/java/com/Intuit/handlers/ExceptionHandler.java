package com.Intuit.handlers;

import com.Intuit.handlers.dtos.ExceptionResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import java.util.NoSuchElementException;

@ControllerAdvice
@Slf4j
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<ExceptionResponseDto> itemNotFoundException(NoSuchElementException exception){
        return buildResponseEntity(HttpStatus.NOT_FOUND,"Player not found");
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ExceptionResponseDto> runtimeException(RuntimeException exception){
        return buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR,"Service not available");
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    public ResponseEntity<ExceptionResponseDto> exception(Exception exception){
        return buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR,"Service not available");
    }

    private ResponseEntity<ExceptionResponseDto> buildResponseEntity(HttpStatus httpStatus,String message){
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(httpStatus,message);
        log.error("[ExceptionHandler][buildResponseEntity] error accrued in request path: {} in time: {}",exceptionResponseDto.getPath(),exceptionResponseDto.getTimestamp());
        return new ResponseEntity<>(exceptionResponseDto, httpStatus);
    }

}
