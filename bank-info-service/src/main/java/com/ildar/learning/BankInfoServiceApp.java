package com.ildar.learning;

import com.ildar.learning.repository.RepositoryMarker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories(basePackageClasses = RepositoryMarker.class)
public class BankInfoServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(BankInfoServiceApp.class, args);
    }
}