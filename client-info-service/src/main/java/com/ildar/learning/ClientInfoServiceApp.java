package com.ildar.learning;

import com.ildar.learning.domain.DomainMarker;
import com.ildar.learning.repository.RepositoryMarker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories(basePackageClasses = RepositoryMarker.class)
@EntityScan(basePackageClasses = DomainMarker.class)
public class ClientInfoServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(ClientInfoServiceApp.class, args);
    }
}
