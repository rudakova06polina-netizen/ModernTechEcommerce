package com.moderntech.ecommerce.models;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private final Customer customer;
    private final List<CartItem> items;

    public ShoppingCart(Customer customer) {
        this.customer = customer;
        this.items = new ArrayList<>();
    }

    public void addItem(CartItem newItem) {
        Product product = newItem.product();
        for (int i = 0; i < items.size(); i++) {
            CartItem existing = items.get(i);
            if (existing.product().id() == product.id()) {
                items.set(i, new CartItem(product, existing.quantity() + newItem.quantity()));
                return;
            }
        }
        items.add(newItem);
    }

    public List<CartItem> getItems() {
        return new ArrayList<>(items);
    }

    public double getTotalWithoutVAT() {
        return items.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }

    public double getVAT() {
        return getTotalWithoutVAT() * 0.22;
    }

    public double getTotalWithVAT() {
        return getTotalWithoutVAT() + getVAT();
    }

    public void displayCart() {
        System.out.println("\n=== КОРЗИНА ===");
        System.out.println("Покупатель: " + customer.getName());
        if (items.isEmpty()) {
            System.out.println("Корзина пуста");
            return;
        }
        for (CartItem item : items) {
            System.out.println(item);
        }
        System.out.printf("Сумма без НДС: %.2f руб.%n", getTotalWithoutVAT());
        System.out.printf("НДС (22%%): %.2f руб.%n", getVAT());
        System.out.printf("ИТОГО: %.2f руб.%n", getTotalWithVAT());
    }
}