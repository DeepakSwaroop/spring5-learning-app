package com.ildar.learning.domain;

import com.ildar.learning.dto.TransactionDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by Ildar on 1/23/2017.
 */
@Document
@Data @Builder @AllArgsConstructor
public class Transaction {

    @Id
    String id;
    /**
     * ID of the bank card
     */
    String cardId;
    /**
     * Either withdrawal or deposit
     */
    TransactionType transactionType;
    /**
     * When transaction occurred
     */
    LocalDateTime transactionTime;
    /**
     * Amount of money involved in transaction
     */
    BigDecimal moneyCount;
    /**
     * Either successful or smth happened
     */
    TransactionStatus transactionStatus;

    public static Transaction from(String cardId, TransactionDTO transaction) {
        return Transaction.builder()
                .cardId(cardId)
                .moneyCount(transaction.getMoneyCount())
                .transactionStatus(transaction.getTransactionStatus())
                .transactionTime(LocalDateTime.now())
                .transactionType(transaction.getTransactionType())
                .build();
    }
}
