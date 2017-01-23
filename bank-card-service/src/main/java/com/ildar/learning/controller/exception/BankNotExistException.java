package com.ildar.learning.controller.exception;

/**
 * Created by Ildar on 1/23/2017.
 */
public class BankNotExistException extends RuntimeException {

    private String bankIssuerId;

    public BankNotExistException(String bankIssuerId) {
        this.bankIssuerId = bankIssuerId;
    }

    public String getBankIssuerId() {
        return bankIssuerId;
    }
}
