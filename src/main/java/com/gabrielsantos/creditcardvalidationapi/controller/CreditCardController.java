package com.gabrielsantos.creditcardvalidationapi.controller;

import com.gabrielsantos.creditcardvalidationapi.dto.CreditCardDTO;
import com.gabrielsantos.creditcardvalidationapi.service.CreditCardService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "CreditCardValidation-API")
@RequestMapping(value = "/credit-card")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreditCardController {

    private final CreditCardService creditCardService;

    @ResponseBody
    @GetMapping(path = "/validateCreditCard")
    public ResponseEntity<String> validateCreditCard(@RequestBody CreditCardDTO creditCardDTO) {
        return creditCardService.validateCreditCard(creditCardDTO);
    }

    @ResponseBody
    @GetMapping(path = "/getSupportedCardIssuers")
    public ResponseEntity<String> getSupportedCardIssuers() {
        return creditCardService.getSupportedCardIssuers();
    }

}
