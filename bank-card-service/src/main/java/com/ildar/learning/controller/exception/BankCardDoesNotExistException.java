package com.ildar.learning.controller.exception;

/**
 * Created by Ildar on 1/23/2017.
 */
public class BankCardDoesNotExistException extends RuntimeException {

    private String cardId;

    public BankCardDoesNotExistException(String cardId) {
        this.cardId = cardId;
    }

    public String getCardId() {
        return cardId;
    }
}
