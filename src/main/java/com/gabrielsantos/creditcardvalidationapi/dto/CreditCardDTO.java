package com.gabrielsantos.creditcardvalidationapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gabrielsantos.creditcardvalidationapi.enums.CreditCardIssuer;
import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(example = "5245 8364 2920 5934 or 5245836429205934")
    private String number;

    @JsonFormat(pattern = "yyyy/MM")
    @ApiModelProperty(example = "2030/12")
    private Date expiringDate;

    @JsonIgnore
    private CreditCardIssuer creditCardIssuer;

}
