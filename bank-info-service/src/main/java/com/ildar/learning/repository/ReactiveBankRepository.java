package com.ildar.learning.repository;

import com.ildar.learning.domain.Bank;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

/**
 * Created by Ildar on 1/22/2017.
 */
public interface ReactiveBankRepository extends ReactiveCrudRepository<Bank, String> {

    Flux<Bank> findByName(String name);
}
