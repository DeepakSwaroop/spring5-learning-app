package com.ildar.learning.service.impl;

import com.ildar.learning.dto.Client;
import com.ildar.learning.service.ClientService;
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
public class ClientServiceImpl implements ClientService {

    @Value("${services.client-info-service-port}")
    private Integer bankInfoServicePort;

    @Autowired
    private WebClient webClient;

    @Override
    public Mono<Client> getById(String clientId) {
        val request = ClientRequest.GET(url() + "{clientId}", clientId)
                .accept(MediaType.APPLICATION_JSON)
                .build();

        return webClient.exchange(request)
                .flatMap(response -> response.bodyToMono(Client.class))
                .single();
    }

    private String url() {
        return "http://localhost:" + bankInfoServicePort + "/";
    }
}
