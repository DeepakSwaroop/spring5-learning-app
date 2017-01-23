package com.ildar.learning.controller.exception;

/**
 * Created by Ildar on 1/23/2017.
 */
public class ClientNotExistException extends RuntimeException {

    private String cardHolderId;

    public ClientNotExistException(String cardHolderId) {
        this.cardHolderId = cardHolderId;
    }

    public String getCardHolderId() {
        return cardHolderId;
    }
}
