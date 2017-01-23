package com.ildar.learning.controller;

import com.ildar.learning.controller.exception.BankCardDoesNotExistException;
import com.ildar.learning.controller.exception.CashWithdrawalLimitException;
import com.ildar.learning.controller.exception.IllegalCashSumFormatException;
import com.ildar.learning.domain.*;
import com.ildar.learning.dto.TransactionDTO;
import com.ildar.learning.repository.ReactiveBankCardRepository;
import com.ildar.learning.repository.ReactiveTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

/**
 * Created by Ildar on 1/23/2017.
 */
@RestController
@RequestMapping(value = "/cards/{cardId}/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionController {

    @Autowired
    private ReactiveTransactionRepository transactionRepository;
    @Autowired
    private ReactiveBankCardRepository bankCardRepository;

    @Value("${service.withdrawal.account-credit-limit}")
    private Integer accountCreditLimit;

    @GetMapping("/")
    public Flux<Transaction> transactions(@PathVariable String cardId) {
        return transactionRepository.findByCardId(cardId);
    }

    @PostMapping("/")
    public Mono<Void> addTransaction(@PathVariable String cardId,
                                     @RequestBody TransactionDTO transaction) {
        return bankCardRepository.findOne(cardId)
                .otherwiseIfEmpty(Mono.error(new BankCardDoesNotExistException(cardId)))
                .map(card -> {
                    checkGivenTransactionSum(card, transaction);
                    transaction.setTransactionStatus(TransactionStatus.SUCCESSFUL);
                    return card;
                })
                .doOnError(exc -> saveTransaction(cardId, transaction))
                .map(card -> setNewCardSum(card, transaction))
                .then(card -> {
                    Mono<Transaction> saveTr = saveTransaction(cardId, transaction);
                    Mono<BankCard> updateCardSum = bankCardRepository.save(card);
                    return saveTr.and(updateCardSum);
                })
                .then();
    }

    private Mono<Transaction> saveTransaction(String cardId, TransactionDTO transaction) {
        return transactionRepository.save(Transaction.from(cardId, transaction));
    }

    private BankCard setNewCardSum(BankCard card, TransactionDTO transaction) {
        BigDecimal delta = transaction.getMoneyCount();
        delta = transaction.getTransactionType() == TransactionType.WITHDRAWAL ? delta.negate() : delta;
        card.setCurrentSum(card.getCurrentSum().add(delta));
        return card;
    }

    /**
     * @throws IllegalCashSumFormatException if sum is null or <= 0
     * @throws CashWithdrawalLimitException if not enough money on card account
     */
    private void checkGivenTransactionSum(BankCard card, TransactionDTO transaction) {
        Integer minAllowedAmount = card.getCardAccountType() == CardAccountType.CREDIT
                ? -accountCreditLimit
                : 0;

        if (transaction.getMoneyCount() == null || transaction.getMoneyCount().compareTo(BigDecimal.ZERO) <= 0) {
            transaction.setTransactionStatus(TransactionStatus.WRONG_SUM_ENTERED);
            throw new IllegalCashSumFormatException(transaction.getMoneyCount());
        }
        if (transaction.getTransactionType() == TransactionType.WITHDRAWAL
                && card.getCurrentSum().doubleValue() - transaction.getMoneyCount().doubleValue() < minAllowedAmount) {
            transaction.setTransactionStatus(TransactionStatus.NOT_ENOUGH_MONEY);
            throw new CashWithdrawalLimitException(card.getCurrentSum());
        }
    }
}
