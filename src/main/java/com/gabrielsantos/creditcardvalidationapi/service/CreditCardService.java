package com.gabrielsantos.creditcardvalidationapi.service;

import com.gabrielsantos.creditcardvalidationapi.dto.CreditCardDTO;
import com.gabrielsantos.creditcardvalidationapi.enums.CreditCardIssuer;
import com.gabrielsantos.creditcardvalidationapi.exception.BadRequestException;
import com.gabrielsantos.creditcardvalidationapi.exception.InvalidCreditCardException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.EnumSet;

@Service
public class CreditCardService {

    public ResponseEntity<String> validateCreditCard(CreditCardDTO creditCardDTO) {
        creditCardDTO.setCreditCardIssuer(getCreditCardFlag(creditCardDTO.getNumber()));

        if (creditCardDTO.getCreditCardIssuer() == null) {
            throw new InvalidCreditCardException("Unknown issuer.");
        }

        if (!validateCreditCardExpiringDate(creditCardDTO.getExpiringDateInMillis())) {
            throw new InvalidCreditCardException("Expired credit card.");
        }

        if (!validateCreditCardNumberOfDigits(creditCardDTO)) {
            throw new InvalidCreditCardException("Invalid number of digits.");
        }

        if (!luhnAlgorithmValidation(creditCardDTO.getNumber(), creditCardDTO.getCreditCardIssuer())) {
            throw new InvalidCreditCardException("Invalid credit card number.");
        }

        return new ResponseEntity<>("Valid credit card.\nIssuer: " + creditCardDTO.getCreditCardIssuer().getDescription(), HttpStatus.OK);
    }

    private CreditCardIssuer getCreditCardFlag(Long cardNumber) {
        if (cardNumber == null || cardNumber == 0) {
            throw new BadRequestException("The cardNumber must not be null or 0.");
        }

        ArrayList<CreditCardIssuer> allCreditCardIssuers = new ArrayList<>(EnumSet.allOf(CreditCardIssuer.class));

        for (CreditCardIssuer creditCardFlag : allCreditCardIssuers) {
            for (int i = 0; i < creditCardFlag.getIssuer().size(); i++) {
                String creditCardIssuer = String.valueOf(creditCardFlag.getIssuer().get(i));
                String firstCardNumberDigits = "";

                for (int j = 0; j < creditCardIssuer.length(); j++) {
                    firstCardNumberDigits += String.valueOf(cardNumber).charAt(j);
                }

                if (firstCardNumberDigits.length() == 4
                        && (Integer.parseInt(firstCardNumberDigits) >= 3528
                        && Integer.parseInt(firstCardNumberDigits) <= 3589)) {
                    return CreditCardIssuer.JCB;
                }

                if (firstCardNumberDigits.length() == 6
                        && (Integer.parseInt(firstCardNumberDigits) >= 222100
                        && Integer.parseInt(firstCardNumberDigits) <= 272099)) {
                    return CreditCardIssuer.MASTER_CARD;
                }

                if (firstCardNumberDigits.length() == 6
                        && (Integer.parseInt(firstCardNumberDigits) >= 622126
                        && Integer.parseInt(firstCardNumberDigits) <= 622925)) {
                    return CreditCardIssuer.DISCOVER;
                }

                if (firstCardNumberDigits.equals(creditCardIssuer)) {
                    return creditCardFlag;
                }
            }
        }

        return null;
    }

    private Boolean validateCreditCardExpiringDate(Long expiringDate) {
        if (expiringDate == null || expiringDate == 0) {
            throw new BadRequestException("The expiringDate must not be null or 0.");
        }

        return expiringDate > System.currentTimeMillis();
    }

    private Boolean validateCreditCardNumberOfDigits(CreditCardDTO creditCardDTO) {
        for (int i = 0; i < creditCardDTO.getCreditCardIssuer().getNumberOfDigits().size(); i++) {
            if (String.valueOf(creditCardDTO.getNumber()).length() == creditCardDTO.getCreditCardIssuer().getNumberOfDigits().get(i)) {
                return true;
            }
        }

        return false;
    }

    private Boolean luhnAlgorithmValidation(Long creditCardNumber, CreditCardIssuer issuer) {
        String creditCardNumberString = String.valueOf(creditCardNumber);
        int oddIndexSum = 0;
        int evenIndexSum = 0;

        for (int i = creditCardNumberString.length() - 1; i >= 0; i--) {
            if (i % 2 != 0) {
                oddIndexSum += Character.getNumericValue(creditCardNumberString.charAt(i));
            } else {
                int evenIndexNumber = Character.getNumericValue(creditCardNumberString.charAt(i)) * 2;

                if (evenIndexNumber >= 10) {
                    String evenIndexString = String.valueOf(evenIndexNumber);
                    evenIndexNumber = Character.getNumericValue(evenIndexString.charAt(0)) + Character.getNumericValue(evenIndexString.charAt(1));
                }

                evenIndexSum += evenIndexNumber;
            }
        }

        if (CreditCardIssuer.AMERICAN_EXPRESS.equals(issuer)) {
            return (oddIndexSum + evenIndexSum) % 10 != 0;
        } else {
            return (oddIndexSum + evenIndexSum) % 10 == 0;
        }
    }

    public ResponseEntity<String> getSupportedCardIssuers() {
        ArrayList<CreditCardIssuer> allCreditCardIssuers = new ArrayList<>(EnumSet.allOf(CreditCardIssuer.class));

        String cardIssuersDescription = "";

        for (CreditCardIssuer allCreditCardIssuer : allCreditCardIssuers) {
            cardIssuersDescription += "\n" + allCreditCardIssuer.getDescription();
        }

        StringBuilder supportedCardIssuers = new StringBuilder(cardIssuersDescription);
        supportedCardIssuers.deleteCharAt(0);

        return new ResponseEntity<>(String.valueOf(supportedCardIssuers), HttpStatus.OK);
    }

}
