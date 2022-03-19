package com.gabrielsantos.creditcardvalidationapi.builder;

import com.gabrielsantos.creditcardvalidationapi.dto.CreditCardDTO;

public class BuilderMethods {

    public CreditCardDTO buildCreditCardDTO() {
        return CreditCardDTO.builder()
                .expiringDateInMillis(1893380400000L)
                .number(5239551529162749L)
                .build();
    }

}
