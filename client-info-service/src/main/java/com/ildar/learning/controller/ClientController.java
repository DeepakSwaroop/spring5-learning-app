package com.ildar.learning.controller;

import com.ildar.learning.controller.exception.BankDoesNotExistException;
import com.ildar.learning.controller.exception.ClientAlreadyExistsException;
import com.ildar.learning.domain.Client;
import com.ildar.learning.repository.ReactiveClientRepository;
import com.ildar.learning.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by Ildar on 1/23/2017.
 */
@RestController
@RequestMapping(value = "/clients", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {

    @Autowired
    private ReactiveClientRepository clientRepository;
    @Autowired
    private BankService bankService;

    @GetMapping("/{id}")
    public Mono<Client> findById(@PathVariable String id) {
        return clientRepository.findOne(id);
    }

    @GetMapping("/{id}/exists")
    public Mono<Boolean> clientExists(@PathVariable String id) {
        return findById(id)
                .map(b -> true)
                .otherwiseIfEmpty(Mono.just(false));
    }

    @GetMapping("/findByBank/{bankId}")
    public Flux<Client> findByBankId(@PathVariable("bankId") String bankId) {
        return clientRepository.findByBankId(bankId);
    }

    @PostMapping("/")
    public Mono<Void> registerClient(@RequestBody Client client) {
        client.setId(null);

        return clientRepository.findBySsnAndBankId(client.getSsn(), client.getBankId())
                .and(bankService.bankExists(client.getBankId()))
                .map(tuple -> {
                    if (tuple.getT1() != null) {
                        //User with this SSN and bank ID already exists
                        throw new ClientAlreadyExistsException(client.getSsn(), client.getBankId());
                    }
                    if (!tuple.getT2()) {
                        //Bank doesn't exist
                        throw new BankDoesNotExistException(client.getBankId());
                    }
                    return client;
                })
                .map(clientRepository::save)
                .then();
    }
}
