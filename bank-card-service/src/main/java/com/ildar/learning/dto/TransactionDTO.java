package com.ildar.learning.dto;

import com.ildar.learning.domain.TransactionStatus;
import com.ildar.learning.domain.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.NonFinal;

import java.math.BigDecimal;

/**
 * Created by Ildar on 1/23/2017.
 */
@Value
@Builder
@AllArgsConstructor
public class TransactionDTO {
    TransactionType transactionType;
    BigDecimal moneyCount;

    @Setter @NonFinal
    TransactionStatus transactionStatus;
}
