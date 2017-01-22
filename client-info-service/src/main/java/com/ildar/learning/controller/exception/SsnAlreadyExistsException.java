package com.ildar.learning.controller.exception;

/**
 * Created by Ildar on 1/23/2017.
 */
public class SsnAlreadyExistsException extends RuntimeException {

    private String ssn;

    public SsnAlreadyExistsException(String ssn) {
        this.ssn = ssn;
    }

    public String getSsn() {
        return ssn;
    }
}
