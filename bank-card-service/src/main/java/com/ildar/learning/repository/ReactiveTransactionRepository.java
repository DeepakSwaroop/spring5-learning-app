package com.ildar.learning.repository;

import com.ildar.learning.domain.Transaction;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

/**
 * Created by Ildar on 1/23/2017.
 */
public interface ReactiveTransactionRepository extends ReactiveCrudRepository<Transaction, String> {

    Flux<Transaction> findByCardId(String cardId);
}
