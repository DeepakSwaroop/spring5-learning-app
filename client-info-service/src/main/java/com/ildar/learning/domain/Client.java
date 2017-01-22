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
public class Client {

    @Id
    String id;
    String firstName;
    String lastName;
    /** SSN must be unique */
    String ssn;
    PhoneNumber phoneNumber;
    LocalDate birthDate;
    LocalDate registrationDate;
    String bankId;
}
