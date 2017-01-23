package com.ildar.learning.service.impl;

import com.ildar.learning.dto.Bank;
import com.ildar.learning.service.BankService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Created by Ildar on 1/23/2017.
 */
@Service
public class BankServiceImpl implements BankService {

    @Value("${services.bank-info-service-port}")
    private Integer bankInfoServicePort;

    @Autowired
    private WebClient webClient;

    @Override
    public Mono<Bank> getById(String bankId) {
        val request = ClientRequest.GET(url() + "{bankId}", bankId)
                .accept(MediaType.APPLICATION_JSON)
                .build();

        return webClient.exchange(request)
                .flatMap(response -> response.bodyToMono(Bank.class))
                .single();
    }

    @Override
    public Mono<Boolean> bankExists(String bankIssuerId) {
        val request = ClientRequest.GET(url() + "{bankId}/exists", bankIssuerId)
                .build();

        return webClient.exchange(request)
                .flatMap(response -> response.bodyToMono(Boolean.class))
                .single();
    }

    private String url() {
        return "http://localhost:" + bankInfoServicePort + "/";
    }
}
