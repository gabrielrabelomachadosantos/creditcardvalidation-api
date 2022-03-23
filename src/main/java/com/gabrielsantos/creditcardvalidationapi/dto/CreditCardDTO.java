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

    @ApiModelProperty(example = "9999 9999 9999 9999 or 9999999999999999")
    private String number;

    @JsonFormat(pattern = "yyyy/MM")
    @ApiModelProperty(example = "yyyy/MM")
    private Date expiringDate;

    @JsonIgnore
    private CreditCardIssuer creditCardIssuer;

}
