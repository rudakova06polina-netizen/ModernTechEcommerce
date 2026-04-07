package com.moderntech.ecommerce.payment;

import com.moderntech.ecommerce.models.Order;

public class OzonPayment implements Payment {
    @Override
    public boolean processPayment(Order order, PaymentMethod paymentMethod) {
        System.out.println("\nОПЛАТА ЧЕРЕЗ OZON");
        System.out.println("Платёжный провайдер: OZON");
        System.out.println("Способ оплаты: " + paymentMethod.getDetails());
        System.out.printf("Сумма платежа: %.2f руб.%n", order.getTotalAmount());

        if (paymentMethod instanceof CreditCardPayment) {
            System.out.println("Проверка банковской карты... Успешно!");
        } else if (paymentMethod instanceof DigitalWalletPayment) {
            System.out.println("Проверка электронного кошелька... Успешно!");
        } else if (paymentMethod instanceof CashOnDelivery) {
            System.out.println("Оформлен наложенный платёж. Оплата при получении.");
        }

        System.out.println("Платёж успешно обработан!");
        order.setPaymentStatus(PaymentStatus.SUCCESS);
        return true;
    }

    @Override
    public String getProviderName() {
        return "Ozon";
    }
}