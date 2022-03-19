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

    private Long number;

    @JsonFormat(pattern = "yyyy/MM")
    @ApiModelProperty(required = true, example = "2025/12")
    private Date expiringDate;

    @JsonIgnore
    private CreditCardIssuer creditCardIssuer;

}
