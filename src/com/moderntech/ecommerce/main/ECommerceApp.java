package com.moderntech.ecommerce.main;

import com.moderntech.ecommerce.enums.OrderStatus;
import com.moderntech.ecommerce.enums.ProductCategory;
import com.moderntech.ecommerce.models.*;
import com.moderntech.ecommerce.payment.*;
import com.moderntech.ecommerce.payment.impl.CashOnDelivery;
import com.moderntech.ecommerce.payment.impl.CreditCardPayment;
import com.moderntech.ecommerce.payment.impl.DigitalWalletPayment;

import java.util.HashMap;
import java.util.Map;

public class ECommerceApp {
    public static void main(String[] args) {
        System.out.println("\nДОБРО ПОЖАЛОВАТЬ В MODERN TECH MALL\n");

        // 1. Каталог товаров
        Map<Integer, Product> catalog = new HashMap<>();

        Product p1 = new Product(1, "iPhone 15 Pro", 99999.99, 5, ProductCategory.SMARTPHONE);
        Product p2 = new Product(2, "MacBook Air M2", 119999.99, 3, ProductCategory.LAPTOP);
        Product p3 = new Product(3, "iPad Pro 11", 79999.99, 7, ProductCategory.TABLET);
        Product p4 = new Product(4, "AirPods Pro 2", 24999.99, 15, ProductCategory.ACCESSORY);
        Product p5 = new Product(5, "Sony A7 IV", 199999.99, 2, ProductCategory.CAMERA);

        catalog.put(p1.id(), p1);
        catalog.put(p2.id(), p2);
        catalog.put(p3.id(), p3);
        catalog.put(p4.id(), p4);
        catalog.put(p5.id(), p5);

        System.out.println("КАТАЛОГ ТОВАРОВ:");
        for (Product p : catalog.values()) {
            System.out.println(p);
        }

        // 2. Покупатель
        Customer customer = new Customer(1, "Алексей Иванов", "alexey@example.com");
        System.out.println("\n" + customer);

        // 3. Корзина
        ShoppingCart cart = new ShoppingCart(customer);
        cart.addItem(new CartItem(p1, 1));
        cart.addItem(new CartItem(p4, 2));
        cart.addItem(new CartItem(p2, 1));

        cart.displayCart();

        // 4. Оформление заказа
        System.out.println("\nОФОРМЛЕНИЕ ЗАКАЗА...");
        Order order = new Order(customer, cart.getItems());
        order.displayOrder();

        order.setStatus(OrderStatus.CONFIRMED);
        order.setStatus(OrderStatus.PROCESSING);
        order.setStatus(OrderStatus.SHIPPED);

        // 5. Три платёжных сценария
        System.out.println("\nПЛАТЁЖНЫЕ СЦЕНАРИИ");

        Payment ozonPayment = new OzonPayment();
        Payment wbPayment = new WildberriesPayment();

        // Сценарий 1: Ozon + банковская карта
        System.out.println("\n[СЦЕНАРИЙ 1]");
        PaymentMethod creditCard = new CreditCardPayment("1234567890123456", "ALEXEY IVANOV");
        ozonPayment.processPayment(order, creditCard);

        // Сценарий 2: Wildberries + электронный кошелёк
        System.out.println("\n[СЦЕНАРИЙ 2]");
        PaymentMethod digitalWallet = new DigitalWalletPayment("alexey@wb.ru");
        wbPayment.processPayment(order, digitalWallet);

        // Сценарий 3: Ozon + наложенный платёж
        System.out.println("\n[СЦЕНАРИЙ 3]");
        PaymentMethod cashOnDelivery = new CashOnDelivery();
        ozonPayment.processPayment(order, cashOnDelivery);

        // 6. Итоговая сводка
        System.out.println("\nИТОГОВАЯ СВОДКА ПО ЗАКАЗУ");
        order.displayOrder();

        System.out.println("\nСпасибо за покупку в Modern Tech Mall!");
    }
}