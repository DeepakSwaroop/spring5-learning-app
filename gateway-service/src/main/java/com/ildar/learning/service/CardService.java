package com.ildar.learning.service;

import com.ildar.learning.dto.FullBankCardInfo;
import com.ildar.learning.dto.NewCardDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by Ildar on 1/24/2017.
 */
public interface CardService {

    Mono<FullBankCardInfo> getCardById(String cardId);

    Flux<FullBankCardInfo> getCardsOfClient(String clientId);

    Mono<String> createCard(NewCardDTO newCardDto);
}
