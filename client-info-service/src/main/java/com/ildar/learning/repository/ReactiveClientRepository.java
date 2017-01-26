package com.ildar.learning.repository;

import com.ildar.learning.domain.Client;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by Ildar on 1/23/2017.
 */
public interface ReactiveClientRepository extends ReactiveCrudRepository<Client, String> {

    Flux<Client> findByBankId(String bankId);

    Mono<Client> findBySsnAndBankId(String ssn, String bankId);
}
