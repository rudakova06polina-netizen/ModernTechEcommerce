package com.moderntech.ecommerce.payment;

import com.moderntech.ecommerce.payment.impl.CashOnDelivery;
import com.moderntech.ecommerce.payment.impl.CreditCardPayment;
import com.moderntech.ecommerce.payment.impl.DigitalWalletPayment;

public sealed interface PaymentMethod permits CreditCardPayment, DigitalWalletPayment, CashOnDelivery {
    String getDetails();
}