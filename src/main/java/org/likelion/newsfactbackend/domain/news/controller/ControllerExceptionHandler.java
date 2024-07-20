package org.likelion.newsfactbackend.domain.news.controller;

import org.likelion.newsfactbackend.domain.news.exception.ErrorMessage;
import org.likelion.newsfactbackend.domain.news.exception.NewsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(NewsException.class)
    public ResponseEntity<ErrorMessage> handleNewsException(NewsException ex) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                ex.getField(),
                ex.getMessage()
        );
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

  /*  @ExceptionHandler
    public ResponseEntity<NewsException> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
    */
}
