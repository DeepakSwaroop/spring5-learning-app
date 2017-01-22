package com.ildar.learning.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

/**
 * Created by Ildar on 1/22/2017.
 */
@Data
@Document
public class Bank {

    @Id
    String id;
    String name;
    String country;
    String address;
    LocalDate foundationDate;
}
