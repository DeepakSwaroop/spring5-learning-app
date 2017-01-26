package com.ildar.learning.service;

import com.ildar.learning.dto.bank.Bank;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by Ildar on 1/24/2017.
 */
public interface BankService {

    Mono<Bank> getById(String id);

    Flux<Bank> getAll();

    /**
     * Tries to save bank object, returns bank ID if successfully saved, otherwise returns empty Mono.
     */
    Mono<String> saveBank(Bank bank);
}
