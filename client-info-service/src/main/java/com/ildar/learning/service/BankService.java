package com.ildar.learning.service;

import reactor.core.publisher.Mono;

/**
 * Created by Ildar on 1/27/2017.
 */
public interface BankService {

    Mono<Boolean> bankExists(String bankId);
}
