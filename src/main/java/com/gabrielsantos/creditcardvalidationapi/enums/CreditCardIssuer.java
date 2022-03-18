package com.gabrielsantos.creditcardvalidationapi.enums;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public enum CreditCardIssuer {

    AMERICAN_EXPRESS(new ArrayList<>(List.of(34,37)),"American Express", new ArrayList<>(List.of(15)), 4),
    DINNERS_CLUB_CARTE(new ArrayList<>(List.of(300,301,302,303,304,305,2014,2149)),"Diner's Club - Carte", new ArrayList<>(List.of(14)),3),
    DINNERS_CLUB_INTERNATIONAL(new ArrayList<>(List.of(36)),"Diner's Club - International", new ArrayList<>(List.of(14)),3),
    DINNERS_CLUB_USA_CANADA(new ArrayList<>(List.of(54)),"Diner's Club - USA & Canada", new ArrayList<>(List.of(16)),3),
    DISCOVER(new ArrayList<>(List.of(6011,644,645,646,647,648,649,65)),"Discover", new ArrayList<>(List.of(16, 19)), 3),
    INSTA_PAYMENT(new ArrayList<>(List.of(637,638,639)),"InstaPayment", new ArrayList<>(List.of(16)), 3),
    JCB(new ArrayList<>(List.of(3,1800,2131)),"JCB", new ArrayList<>(List.of(16, 19)),3),
    MAESTRO(new ArrayList<>(List.of(5018,5020,5038,5893,6304,6759,6761,6762,6763)),"Maestro", new ArrayList<>(List.of(16, 19)), 3),
    MASTER_CARD(new ArrayList<>(List.of(51,52,53,54,55)),"Master Card", new ArrayList<>(List.of(16)), 3),
    VISA(new ArrayList<>(List.of(4)),"Visa", new ArrayList<>(List.of(13,16,19)), 3),
    VISA_ELECTRON(new ArrayList<>(List.of(4026,417500,4508,4844,4913,4917)),"Visa Electron", new ArrayList<>(List.of(16)), 3);

    private final ArrayList<Integer> issuer;
    private final String description;
    private final ArrayList<Integer> numberOfDigits;
    private final Integer cvvNumberOfDigits;

    public ArrayList<Integer> getIssuer() {
        return this.issuer;
    }

    public String getDescription() {
        return this.description;
    }

    public ArrayList<Integer> getNumberOfDigits() {
        return this.numberOfDigits;
    }

    public int getCvvNumberOfDigits() {
        return this.cvvNumberOfDigits;
    }

}
