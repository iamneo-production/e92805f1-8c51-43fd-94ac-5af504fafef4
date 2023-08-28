package com.microminds.reminder.notificationmanagement.exception;

import com.microminds.reminder.notificationmanagement.dao.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotificationFailedException.class)
    ResponseEntity<ExceptionResponse> notificationFailedExceptionHandler(NotificationFailedException ex){
        return new ResponseEntity<>(ExceptionResponse.builder().mesage(ex.getMessage()).httpStatus(HttpStatus.BAD_GATEWAY).statusCode("502").build(),HttpStatus.BAD_GATEWAY );

    }
}
