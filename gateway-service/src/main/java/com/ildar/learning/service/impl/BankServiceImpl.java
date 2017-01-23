package com.ildar.learning.service.impl;

import com.ildar.learning.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Created by Ildar on 1/24/2017.
 */
@Service
public class BankServiceImpl implements BankService {

    @Autowired
    private WebClient webClient;


}
