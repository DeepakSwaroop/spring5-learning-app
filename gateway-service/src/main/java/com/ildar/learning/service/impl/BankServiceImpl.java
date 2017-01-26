package com.ildar.learning.service.impl;

import com.ildar.learning.dto.bank.Bank;
import com.ildar.learning.service.BankService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by Ildar on 1/24/2017.
 */
@Service
public class BankServiceImpl implements BankService {

    @Value("${services.bank-info-service-port}")
    private Integer bankInfoServicePort;

    @Autowired
    private WebClient webClient;

    @Override
    public Mono<Bank> getById(String id) {
        val request = ClientRequest.GET(url() + "{id}", id)
                .build();

        return webClient.exchange(request)
                .flatMap(response -> response.bodyToMono(Bank.class))
                .single();
    }

    @Override
    public Flux<Bank> getAll() {
        val request = ClientRequest.GET(url())
                .build();

        return webClient.exchange(request)
                .flatMap(response -> response.bodyToFlux(Bank.class));
    }

    @Override
    public Mono<String> saveBank(Bank bank) {
        val request = ClientRequest.POST(url())
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(bank), Bank.class);

        return webClient.exchange(request)
                .flatMap(response -> response.bodyToMono(String.class))
                .single();
    }

    private String url() {
        return "http://localhost:" + bankInfoServicePort + "/banks/";
    }
}
