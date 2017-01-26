package com.ildar.learning.controller;

import com.ildar.learning.dto.client.Client;
import com.ildar.learning.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * Created by Ildar on 1/24/2017.
 */
@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/findByBank/{bankId}")
    public Flux<Client> findClientsByBank(@PathVariable String bankId) {
        return clientService.getClientsByBank(bankId);
    }
}
