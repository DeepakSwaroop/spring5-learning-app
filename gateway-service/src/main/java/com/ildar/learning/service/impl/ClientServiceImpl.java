package com.ildar.learning.service.impl;

import com.ildar.learning.dto.client.Client;
import com.ildar.learning.service.ClientService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

/**
 * Created by Ildar on 1/24/2017.
 */
@Service
public class ClientServiceImpl implements ClientService {

    @Value("${services.client-info-service-port}")
    private Integer clientInfoServicePort;

    @Autowired
    private WebClient webClient;

    @Override
    public Flux<Client> getClientsByBank(String bankId) {
        val request = ClientRequest.GET(url() + "findByBank/{bankId}", bankId)
                .build();

        return webClient.exchange(request)
                .flatMap(response -> response.bodyToFlux(Client.class));
    }

    private String url() {
        return "http://localhost:" + clientInfoServicePort + "/clients/";
    }
}
