package com.ildar.learning.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

/**
 * Created by Ildar on 1/24/2017.
 */
@Value
@Builder
@AllArgsConstructor
public class NewCardDTO {

    String bankIssuerId;
    String cardHolderId;
    PaymentServiceProvider paymentServiceProvider;
    CardAccountType cardAccountType;
}

