package com.moderntech.ecommerce.payment;

public final class CashOnDelivery implements PaymentMethod {
    @Override
    public String getDetails() {
        return "Наложенный платёж (оплата при получении)";
    }
}
