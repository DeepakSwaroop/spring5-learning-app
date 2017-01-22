package com.ildar.learning.controller.exception;

import java.math.BigDecimal;

/**
 * Created by Ildar on 1/23/2017.
 */
public class IllegalCashSumFormatException extends RuntimeException {

    private BigDecimal givenSum;

    public IllegalCashSumFormatException(BigDecimal givenSum) {
        this.givenSum = givenSum;
    }

    public BigDecimal getGivenSum() {
        return givenSum;
    }
}
