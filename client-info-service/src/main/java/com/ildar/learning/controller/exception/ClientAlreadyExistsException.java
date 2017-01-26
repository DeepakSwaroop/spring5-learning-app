package com.ildar.learning.controller.exception;

/**
 * Created by Ildar on 1/23/2017.
 */
public class ClientAlreadyExistsException extends RuntimeException {

    private String ssn;
    private String bankId;

    public ClientAlreadyExistsException(String ssn, String bankId) {
        this.ssn = ssn;
        this.bankId = bankId;
    }

    public String getSsn() {
        return ssn;
    }

    public String getBankId() {
        return bankId;
    }
}
