package com.ildar.learning.domain;

/**
 * Created by Ildar on 1/23/2017.
 */
public enum TransactionStatus {

    /**
     * If withdrawal/deposit was successfully made.
     */
    SUCCESSFUL,
    /**
     * Wrong amount entered, e.g. a negative value or zero.
     */
    WRONG_SUM_ENTERED,
    /**
     * If there's no such amount of money on debit card or credit limit is reached for credit card.
     */
    NOT_ENOUGH_MONEY;
}
