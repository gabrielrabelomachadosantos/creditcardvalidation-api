package com.gabrielsantos.creditcardvalidationapi.builder;

import com.gabrielsantos.creditcardvalidationapi.dto.CreditCardDTO;

import java.util.Date;

public class BuilderMethods {

    public CreditCardDTO buildCreditCardDTO() {
        return CreditCardDTO.builder()
                .expiringDate(new Date(1893380400000L))
                .number("5239551529162749")
                .build();
    }

}
