package com.ildar.learning.service;

import com.ildar.learning.dto.Client;
import reactor.core.publisher.Mono;

/**
 * Created by Ildar on 1/23/2017.
 */
public interface ClientService {

    /**
     * Retrieve client instance from client-info-service by its ID
     */
    Mono<Client> getById(String clientId);
}
