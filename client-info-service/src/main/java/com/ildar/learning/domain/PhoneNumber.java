package com.ildar.learning.domain;

import lombok.Value;

/**
 * Created by Ildar on 1/22/2017.
 */
@Value
public class PhoneNumber {

    String prefix;
    String countryCode;
    String number;

    @Override
    public String toString() {
        return prefix + " " + countryCode + " " + number;
    }
}
