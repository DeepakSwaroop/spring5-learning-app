package com.ildar.learning.dto.bank;

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
public class Bank {

    String name;
    String country;
    String address;
    LocalDate foundationDate;
}
