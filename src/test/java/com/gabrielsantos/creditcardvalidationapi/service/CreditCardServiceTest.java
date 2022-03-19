package com.gabrielsantos.creditcardvalidationapi.service;

import com.gabrielsantos.creditcardvalidationapi.builder.BuilderMethods;
import com.gabrielsantos.creditcardvalidationapi.exception.BadRequestException;
import com.gabrielsantos.creditcardvalidationapi.exception.InvalidCreditCardException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CreditCardServiceTest {

    @InjectMocks
    private CreditCardService creditCardService;

    private final BuilderMethods builderMethods = new BuilderMethods();

    private static final Date INVALID_EXPIRING_DATE = new Date(1262224800000L);

    private static final Long INCOMPLETE_MASTERCARD_NUMBER = 523955152L;

    private static final Long INVALID_MASTERCARD_NUMBER = 5239551529162748L;

    private static final Long UNKNOWN_ISSUER_CARD_NUMBER = 9239551529162749L;

    @Test
    @DisplayName("Credit card validation success")
    void creditCardValidationSuccess() {
        var response = creditCardService.validateCreditCard(builderMethods.buildCreditCardDTO());

        assertNotNull(response.getBody());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    @DisplayName("Credit card number validation error")
    void creditCardNumberValidationError() {
        var creditCard = builderMethods.buildCreditCardDTO();
        creditCard.setNumber(null);

        Throwable exception = assertThrows(BadRequestException.class,() ->
                creditCardService.validateCreditCard(creditCard));
        assertEquals("The cardNumber must not be null or 0.", exception.getMessage());

        creditCard.setNumber(INCOMPLETE_MASTERCARD_NUMBER);

        exception = assertThrows(InvalidCreditCardException.class,() ->
                creditCardService.validateCreditCard(creditCard));
        assertEquals("Invalid credit card. Invalid number of digits.", exception.getMessage());

        creditCard.setNumber(UNKNOWN_ISSUER_CARD_NUMBER);

        exception = assertThrows(InvalidCreditCardException.class,() ->
                creditCardService.validateCreditCard(creditCard));
        assertEquals("Invalid credit card. Unknown issuer.", exception.getMessage());
    }

    @Test
    @DisplayName("Credit card expiring date validation error")
    void creditCardExpiringDateValidationError() {
        var creditCard = builderMethods.buildCreditCardDTO();
        creditCard.setExpiringDate(null);

        Throwable exception = assertThrows(BadRequestException.class,() ->
                creditCardService.validateCreditCard(creditCard));
        assertEquals("The expiringDate must not be null or 0.", exception.getMessage());

        creditCard.setExpiringDate(INVALID_EXPIRING_DATE);

        exception = assertThrows(InvalidCreditCardException.class,() ->
                creditCardService.validateCreditCard(creditCard));
        assertEquals("Invalid credit card. Expired credit card.", exception.getMessage());
    }

    @Test
    @DisplayName("Credit card luhn validation error")
    void creditCardLuhnValidationError() {
        var creditCard = builderMethods.buildCreditCardDTO();
        creditCard.setNumber(INVALID_MASTERCARD_NUMBER);

        Throwable exception = assertThrows(InvalidCreditCardException.class,() ->
                creditCardService.validateCreditCard(creditCard));
        assertEquals("Invalid credit card. Invalid credit card number.", exception.getMessage());
    }

}
