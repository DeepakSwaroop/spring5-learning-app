package com.ildar.learning.dto;

import com.ildar.learning.domain.BankCard;
import com.ildar.learning.domain.CardAccountType;
import com.ildar.learning.domain.PaymentServiceProvider;
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

    /**
     * Build <code>FullBankCardInfo</code> instance from three objects - bank card, bank and client DTOs.
     */
    public static FullBankCardInfo from(BankCard bankCard, Bank bank, Client client) {
        return FullBankCardInfo.builder()
                .bankCardId(bankCard.getId())
                .bankIssuer(bank)
                .cardHolder(client)
                .cardAccountType(bankCard.getCardAccountType())
                .currentSum(bankCard.getCurrentSum())
                .paymentServiceProvider(bankCard.getPaymentServiceProvider())
                .build();
    }
}
