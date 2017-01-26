package com.ildar.learning.service;

import com.ildar.learning.dto.client.Client;
import reactor.core.publisher.Flux;

/**
 * Created by Ildar on 1/24/2017.
 */
public interface ClientService {

    Flux<Client> getClientsByBank(String bankId);
}
