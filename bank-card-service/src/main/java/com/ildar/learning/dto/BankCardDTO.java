package com.ildar.learning.dto;

import com.ildar.learning.domain.CardAccountType;
import com.ildar.learning.domain.PaymentServiceProvider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

/**
 * Created by Ildar on 1/23/2017.
 */
@Value
@Builder
@AllArgsConstructor
public class BankCardDTO {

    PaymentServiceProvider paymentServiceProvider;
    String clientId;
    String bankIssuerId;
    CardAccountType cardAccountType;
}
