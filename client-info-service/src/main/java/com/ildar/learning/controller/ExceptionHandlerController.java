package com.ildar.learning.controller;

import com.ildar.learning.controller.exception.BankDoesNotExistException;
import com.ildar.learning.controller.exception.ClientAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by Ildar on 1/23/2017.
 */
@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(ClientAlreadyExistsException.class)
    public ResponseEntity<String> handleSsnAlreadyExists(ClientAlreadyExistsException exc) {
        return new ResponseEntity<>("SSN already exists: " + exc.getSsn(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(BankDoesNotExistException.class)
    public ResponseEntity<String> handlerBankDoesNotExist(BankDoesNotExistException exc) {
        return new ResponseEntity<>("The specified client's bank doesn't exist: bank ID = " + exc.getBankId(),
                HttpStatus.FORBIDDEN);
    }
}
