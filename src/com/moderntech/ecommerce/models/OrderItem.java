package com.moderntech.ecommerce.models;

public record OrderItem(
        Product product,
        int quantity,
        double priceAtPurchaseTime
) {
    public double getTotalPrice() {
        return priceAtPurchaseTime * quantity;
    }

    @Override
    public String toString() {
        return String.format("%s x%d = %.2f руб. (по цене %.2f)",
                product.name(), quantity, getTotalPrice(), priceAtPurchaseTime);
    }
}