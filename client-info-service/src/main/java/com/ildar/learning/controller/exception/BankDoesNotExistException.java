package com.ildar.learning.controller.exception;

/**
 * Created by Ildar on 1/27/2017.
 */
public class BankDoesNotExistException extends RuntimeException {

    private String bankId;

    public BankDoesNotExistException(String bankId) {
        this.bankId = bankId;
    }

    public String getBankId() {
        return bankId;
    }
}
