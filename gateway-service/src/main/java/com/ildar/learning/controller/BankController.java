package com.ildar.learning.controller;

import com.ildar.learning.dto.bank.Bank;
import com.ildar.learning.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by Ildar on 1/24/2017.
 */
@RestController
@RequestMapping(value = "/banks")
public class BankController {

    @Autowired
    private BankService bankService;

    @GetMapping("/")
    public Flux<Bank> getAllBanks() {
        return bankService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<Bank> getById(@PathVariable String id) {
        return bankService.getById(id);
    }

    @PostMapping("/")
    public Mono<String> createBank(@RequestBody Bank bank) {
        return bankService.saveBank(bank);
    }
}
