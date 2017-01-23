package com.ildar.learning.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

/**
 * Created by Ildar on 1/23/2017.
 */
@Value
@Builder
@AllArgsConstructor
public class FullBankCardInfo {

    String bankCardId;
    Bank bankIssuer;
    Client cardHolder;
    PaymentServiceProvider paymentServiceProvider;
    BigDecimal currentSum;
    CardAccountType cardAccountType;
}
