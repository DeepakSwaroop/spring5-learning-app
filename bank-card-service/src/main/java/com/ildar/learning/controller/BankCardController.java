package com.ildar.learning.controller;

import com.ildar.learning.domain.BankCard;
import com.ildar.learning.dto.Bank;
import com.ildar.learning.dto.BankCardDTO;
import com.ildar.learning.dto.Client;
import com.ildar.learning.dto.FullBankCardInfo;
import com.ildar.learning.repository.ReactiveBankCardRepository;
import com.ildar.learning.service.BankService;
import com.ildar.learning.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * Created by Ildar on 1/23/2017.
 */
@RestController
@RequestMapping(value = "/bankCards", produces = MediaType.APPLICATION_JSON_VALUE)
public class BankCardController {

    @Autowired
    private BankService bankService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ReactiveBankCardRepository bankCardRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Mono<FullBankCardInfo> formFullBankCardInfo(@PathVariable String bankCardId) {
        /*
        3 steps involved here:
        1) Get BankCard instance from the mongo repository;
        2) When instance is fetched, use fields bankIssuerId and clientId to retrieve Bank and Client objects
           from bank-info-service and client-info-service for additional information about bank-issuer of card
           and card holder respectively;
        3) Unite information from these three objects into one FullBankCardInfo instance and send it to the client.
        */
        return bankCardRepository.findOne(bankCardId)
                .flatMap(bankCard -> {
                    Mono<Bank> getBankInfo = bankService.getById(bankCard.getBankIssuerId());
                    Mono<Client> getClientInfo = clientService.getById(bankCard.getClientId());
                    return getBankInfo.and(getClientInfo)
                            .map(tuple -> FullBankCardInfo.from(bankCard, tuple.getT1(), tuple.getT2()));
                })
                .single();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Mono<Void> createCard(@RequestBody Mono<BankCardDTO> bankCard) {
        //todo check that given bank and client do exist
        return bankCard
                .map(BankCard::from)
                .map(card -> bankCardRepository.save(card))
                .then();
    }
}
