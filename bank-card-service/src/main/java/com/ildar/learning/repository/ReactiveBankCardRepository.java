package com.ildar.learning.repository;

import com.ildar.learning.domain.BankCard;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * Created by Ildar on 1/23/2017.
 */
public interface ReactiveBankCardRepository extends ReactiveCrudRepository<BankCard, String> {
}
