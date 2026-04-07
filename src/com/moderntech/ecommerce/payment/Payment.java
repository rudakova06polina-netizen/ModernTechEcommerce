package com.moderntech.ecommerce.payment;

import com.moderntech.ecommerce.models.Order;

public interface Payment {
    boolean processPayment(Order order, PaymentMethod paymentMethod);
    String getProviderName();
}
