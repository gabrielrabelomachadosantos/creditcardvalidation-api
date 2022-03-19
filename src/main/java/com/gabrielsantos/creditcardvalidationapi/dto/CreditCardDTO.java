package com.gabrielsantos.creditcardvalidationapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gabrielsantos.creditcardvalidationapi.enums.CreditCardIssuer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardDTO {

    private Long number;

    @JsonFormat(pattern = "YYYY/MM")
    private Date expiringDate;

    @JsonIgnore
    private CreditCardIssuer creditCardIssuer;

}
