package com.ildar.learning.service.impl;

import com.ildar.learning.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Created by Ildar on 1/24/2017.
 */
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private WebClient webClient;
}
