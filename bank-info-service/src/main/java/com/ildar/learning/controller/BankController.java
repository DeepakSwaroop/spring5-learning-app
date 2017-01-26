package com.ildar.learning.controller;

import com.ildar.learning.domain.Bank;
import com.ildar.learning.repository.ReactiveBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by Ildar on 1/22/2017.
 */
@RestController
@RequestMapping(value = "/banks", produces = MediaType.APPLICATION_JSON_VALUE)
public class BankController {

    @Autowired
    private ReactiveBankRepository bankRepository;

    @GetMapping("/")
    public Flux<Bank> allBanks() {
        return bankRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Bank> findById(@PathVariable String id) {
        return bankRepository.findOne(id);
    }

    @GetMapping("/{id}/exists")
    public Mono<Boolean> bankExists(@PathVariable String id) {
        return findById(id)
                .map(b -> true)
                .otherwiseIfEmpty(Mono.just(false));
    }

    @GetMapping("/findByName")
    public Flux<Bank> findByName(@RequestParam("name") String name) {
        return bankRepository.findByName(name);
    }

    @PostMapping("/")
    public Mono<Void> createBank(@RequestBody Mono<Bank> reqBank) {
        return reqBank
                .map(bank -> {
                    bank.setId(null);
                    return bankRepository.save(bank);
                })
                .then();
    }
}
