package com.ildar.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
//@EnableReactiveMongoRepositories(basePackageClasses = RepositoryMarker.class)
public class BankCardServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(BankCardServiceApp.class, args);
    }
}
