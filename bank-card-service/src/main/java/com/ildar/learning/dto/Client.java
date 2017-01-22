package com.ildar.learning.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

/**
 * Created by Ildar on 1/23/2017.
 */
@Value
@Builder
@AllArgsConstructor
public class Client {

    String firstName;
    String lastName;
    String ssn;
    String phoneNumber;
    LocalDate birthDate;
    LocalDate registrationDate;
}
