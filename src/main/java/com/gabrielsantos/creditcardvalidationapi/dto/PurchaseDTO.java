package com.gabrielsantos.creditcardvalidationapi.dto;

import com.gabrielsantos.creditcardvalidationapi.enums.PurchaseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDTO {

    private Long id;

    private CreditCardDTO creditCardDTO;

    private PurchaseStatus purchaseStatus;

    private String buyerEmail;

}
