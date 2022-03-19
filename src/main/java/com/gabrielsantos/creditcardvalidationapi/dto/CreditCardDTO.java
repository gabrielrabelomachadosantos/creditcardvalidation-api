package com.gabrielsantos.creditcardvalidationapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gabrielsantos.creditcardvalidationapi.enums.CreditCardIssuer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardDTO {

    private Long number;

    private Long expiringDateInMillis;

    @JsonIgnore
    private CreditCardIssuer creditCardIssuer;

}
