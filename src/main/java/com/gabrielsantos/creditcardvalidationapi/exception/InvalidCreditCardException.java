package com.gabrielsantos.creditcardvalidationapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidCreditCardException extends RuntimeException {

    public InvalidCreditCardException(String message) {
        super("Invalid credit card. " + message);
    }

}
