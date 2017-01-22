package com.ildar.learning.controller;

import com.ildar.learning.controller.exception.SsnAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by Ildar on 1/23/2017.
 */
@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler
    public ResponseEntity<String> handleSsnAlreadyExists(SsnAlreadyExistsException exc) {
        return new ResponseEntity<>("SSN already exists: " + exc.getSsn(), HttpStatus.FORBIDDEN);
    }
}
