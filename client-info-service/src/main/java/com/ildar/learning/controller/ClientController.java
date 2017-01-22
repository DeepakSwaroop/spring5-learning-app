package com.ildar.learning.controller;

import com.ildar.learning.controller.exception.SsnAlreadyExistsException;
import com.ildar.learning.domain.Client;
import com.ildar.learning.repository.ReactiveClientRepository;
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

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Mono<Client> findById(@PathVariable String id) {
        return clientRepository.findOne(id);
    }

    @RequestMapping(value = "/findByBankId", method = RequestMethod.GET)
    public Flux<Client> findByBankId(@RequestParam("bankId") String bankId) {
        return clientRepository.findByBankId(bankId);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Mono<Void> registerClient(@RequestBody Client client) {
        client.setId(null);
        return clientRepository.findBySsn(client.getSsn())
                .map(cl -> {
                    throw new SsnAlreadyExistsException(client.getSsn());
                })
                .otherwiseIfEmpty(clientRepository.save(client))
                .then();
    }
}
