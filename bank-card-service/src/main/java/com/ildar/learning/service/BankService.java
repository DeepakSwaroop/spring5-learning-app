package com.ildar.learning.service;

import com.ildar.learning.dto.Bank;
import reactor.core.publisher.Mono;

/**
 * Created by Ildar on 1/23/2017.
 */
public interface BankService {

    /**
     * Retrieve bank instance from bank-info-service by its ID
     */
    Mono<Bank> getById(String bankId);

    Mono<Boolean> bankExists(String bankIssuerId);
}
