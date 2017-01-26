package com.ildar.learning.service.impl;

import com.ildar.learning.dto.card.FullBankCardInfo;
import com.ildar.learning.dto.card.NewCardDTO;
import com.ildar.learning.service.CardService;
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
public class CardServiceImpl implements CardService {

    @Autowired
    private WebClient webClient;

    @Value("${services.bank-card-service-port}")
    private Integer bankCardServicePort;

    @Override
    public Mono<FullBankCardInfo> getCardById(String cardId) {
        val request = ClientRequest.GET(url() + "{id}", cardId)
                .accept(MediaType.APPLICATION_JSON)
                .build();

        return webClient.exchange(request)
                .flatMap(response -> response.bodyToMono(FullBankCardInfo.class))
                .single();
    }

    @Override
    public Flux<FullBankCardInfo> getCardsOfClient(String clientId) {
        val request = ClientRequest.GET(url() + "findByClient?clientId=" + clientId)
                .accept(MediaType.APPLICATION_JSON)
                .build();

        return webClient.exchange(request)
                .flatMap(response -> response.bodyToFlux(FullBankCardInfo.class));
    }

    @Override
    public Mono<String> createCard(NewCardDTO newCardDto) {
        return null;
    }

    private String url() {
        return "http://localhost:" + bankCardServicePort + "/bankCards/";
    }
}
