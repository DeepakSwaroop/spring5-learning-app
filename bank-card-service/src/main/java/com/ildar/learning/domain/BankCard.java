package com.ildar.learning.domain;

import com.ildar.learning.dto.BankCardDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

/**
 * Created by Ildar on 1/23/2017.
 */
@Document
@Data @Builder @AllArgsConstructor
public class BankCard {

    @Id
    String id;
    PaymentServiceProvider paymentServiceProvider;
    /**
     * ID of the card's holder
     */
    String clientId;
    /**
     * ID of the bank - issuer of the card
     */
    String bankIssuerId;
    /**
     * Current cash amount on account
     */
    BigDecimal currentSum;
    CardAccountType cardAccountType;

    public static BankCard from(BankCardDTO dto) {
        return BankCard.builder()
                .paymentServiceProvider(dto.getPaymentServiceProvider())
                .currentSum(BigDecimal.valueOf(0.0))
                .bankIssuerId(dto.getBankIssuerId())
                .cardAccountType(dto.getCardAccountType())
                .clientId(dto.getClientId())
                .build();
    }
}
