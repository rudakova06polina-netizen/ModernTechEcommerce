package com.moderntech.ecommerce.payment;

import com.moderntech.ecommerce.models.Order;

public class WildberriesPayment implements Payment {
    @Override
    public boolean processPayment(Order order, PaymentMethod paymentMethod) {
        System.out.println("\nОПЛАТА ЧЕРЕЗ WILDBERRIES");
        System.out.println("Платёжный провайдер: Wildberries");
        System.out.println("Способ оплаты: " + paymentMethod.getDetails());
        System.out.printf("Сумма платежа: %.2f руб.%n", order.getTotalAmount());

        if (paymentMethod instanceof CreditCardPayment) {
            System.out.println("Валидация карты через WB Pay... Успешно!");
        } else if (paymentMethod instanceof DigitalWalletPayment) {
            System.out.println("Проверка WB Кошелька... Успешно!");
        } else if (paymentMethod instanceof CashOnDelivery) {
            System.out.println("Оплата при получении (ПВЗ Wildberries)");
        }

        System.out.println("Транзакция одобрена!");
        order.setPaymentStatus(PaymentStatus.SUCCESS);
        return true;
    }

    @Override
    public String getProviderName() {
        return "Wildberries";
    }
}