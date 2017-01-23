package com.ildar.learning.controller;

import com.ildar.learning.controller.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by Ildar on 1/23/2017.
 */
@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(IllegalCashSumFormatException.class)
    public ResponseEntity<String> illegalCashSumFormat(IllegalCashSumFormatException exc) {
        return new ResponseEntity<>("Illegal specified cash sum: " + exc.getGivenSum(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CashWithdrawalLimitException.class)
    public ResponseEntity<String> cashWithdrawalLimit(CashWithdrawalLimitException exc) {
        return new ResponseEntity<>("Can't withdraw the specified amount - limit exceeded. " +
                "Current account sum: " + exc.getCurrentSum(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(BankCardDoesNotExistException.class)
    public ResponseEntity<String> bankCardDoesNotExist(BankCardDoesNotExistException exc) {
        return new ResponseEntity<>("The specified card doesn't exist.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BankNotExistException.class)
    public ResponseEntity<String> bankNotExist(BankNotExistException exc) {
        return new ResponseEntity<>("The specified bank does not exist: " + exc.getBankIssuerId(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ClientNotExistException.class)
    public ResponseEntity<String> clientNotExist(ClientNotExistException exc) {
        return new ResponseEntity<>("The specified client does not exist: " + exc.getCardHolderId(), HttpStatus.NOT_FOUND);
    }
}
