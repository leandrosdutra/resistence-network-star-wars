package com.resistence.network.application.controller;

import com.resistence.network.domain.exceptions.BusinessException;
import com.resistence.network.domain.exceptions.NotFoundException;
import com.resistence.network.domain.exceptions.StandardError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> notFound(NotFoundException e, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis()));
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<StandardError> businessException(BusinessException e, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                             .body(new StandardError(HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage(), System.currentTimeMillis()));
    }

}