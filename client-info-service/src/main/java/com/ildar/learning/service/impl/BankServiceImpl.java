package com.ildar.learning.service.impl;

import com.ildar.learning.service.BankService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Created by Ildar on 1/27/2017.
 */
@Service
public class BankServiceImpl implements BankService {

    @Value("${service.bank-info-service-port}")
    private String bankInfoServicePort;

    @Autowired
    private WebClient webClient;

    @Override
    public Mono<Boolean> bankExists(String bankId) {
        val request = ClientRequest.GET(url() + "{bankId}/exists", bankId)
                .build();

        return webClient.exchange(request)
                .flatMap(response -> response.bodyToMono(Boolean.class))
                .single();
    }

    private String url() {
        return "http://localhost:" + bankInfoServicePort + "/banks/";
    }
}
