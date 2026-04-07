package com.moderntech.ecommerce.main;

import com.moderntech.ecommerce.enums.OrderStatus;
import com.moderntech.ecommerce.enums.ProductCategory;
import com.moderntech.ecommerce.models.*;
import com.moderntech.ecommerce.payment.*;

import java.util.HashMap;
import java.util.Map;

public class ECommerceApp {
    public static void main(String[] args) {
        System.out.println("\nДобро пожаловать!\n");

        // 1. Каталог товаров
        Map<Integer, Product> catalog = new HashMap<>();

        Product p1 = new Product(1, "iPhone 17 Pro Max 512GB", 159999.99, 5, ProductCategory.SMARTPHONE);
        Product p2 = new Product(2, "MacBook Air M2", 139999.99, 3, ProductCategory.LAPTOP);
        Product p3 = new Product(3, "iPad Pro 11", 69999.99, 7, ProductCategory.TABLET);
        Product p4 = new Product(4, "AirPods Pro 2", 19999.99, 15, ProductCategory.ACCESSORY);
        Product p5 = new Product(5, "Sony A7 IV", 250999.99, 2, ProductCategory.CAMERA);

        catalog.put(p1.id(), p1);
        catalog.put(p2.id(), p2);
        catalog.put(p3.id(), p3);
        catalog.put(p4.id(), p4);
        catalog.put(p5.id(), p5);

        System.out.println("Каталог:");
        for (Product p : catalog.values()) {
            System.out.println(p);
        }

        // 2. Покупатель
        Customer customer = new Customer(1, "Рудакова Полина", "rudakova06polina@inbox.ru");
        System.out.println("\n" + customer);

        // 3. Корзина
        ShoppingCart cart = new ShoppingCart(customer);
        cart.addItem(new CartItem(p1, 1));
        cart.addItem(new CartItem(p4, 2));
        cart.addItem(new CartItem(p2, 1));

        cart.displayCart();

        // 4. Оформление заказа
        System.out.println("\nОформление...");
        Order order = new Order(customer, cart.getItems());
        order.displayOrder();

        order.setStatus(OrderStatus.CONFIRMED);
        order.setStatus(OrderStatus.PROCESSING);
        order.setStatus(OrderStatus.SHIPPED);

        //Три варианта платежей
        System.out.println("\nВарианты платежей");

        Payment ozonPayment = new OzonPayment();
        Payment wbPayment = new WildberriesPayment();

        //Ozon + банковская карта
        System.out.println("\n[Вариант 1]");
        PaymentMethod creditCard = new CreditCardPayment("1234567890123456", "ALEXEY IVANOV");
        ozonPayment.processPayment(order, creditCard);

        //Wildberries + электронный кошелёк
        System.out.println("\n[Вариант 2]");
        PaymentMethod digitalWallet = new DigitalWalletPayment("alexey@wb.ru");
        wbPayment.processPayment(order, digitalWallet);

        //Ozon + наложенный платёж
        System.out.println("\n[Вариант 3]");
        PaymentMethod cashOnDelivery = new CashOnDelivery();
        ozonPayment.processPayment(order, cashOnDelivery);

        //Итог
        System.out.println("\nИтоговая сводка");
        order.displayOrder();
    }
}