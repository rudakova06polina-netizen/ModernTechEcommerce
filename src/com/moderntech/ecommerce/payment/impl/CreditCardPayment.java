package com.moderntech.ecommerce.payment.impl;

import com.moderntech.ecommerce.payment.PaymentMethod;

public final class CreditCardPayment implements PaymentMethod {
    private final String cardNumber;
    private final String cardHolder;

    public CreditCardPayment(String cardNumber, String cardHolder) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
    }

    @Override
    public String getDetails() {
        String masked = "****" + cardNumber.substring(cardNumber.length() - 4);
        return String.format("Карта %s (%s)", masked, cardHolder);
    }
}