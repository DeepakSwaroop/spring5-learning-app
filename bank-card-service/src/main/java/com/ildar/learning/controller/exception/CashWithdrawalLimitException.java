package com.ildar.learning.controller.exception;

import java.math.BigDecimal;

/**
 * Created by Ildar on 1/23/2017.
 */
public class CashWithdrawalLimitException extends RuntimeException {

    private BigDecimal currentSum;

    public CashWithdrawalLimitException(BigDecimal currentSum) {
        this.currentSum = currentSum;
    }

    public BigDecimal getCurrentSum() {
        return currentSum;
    }
}
