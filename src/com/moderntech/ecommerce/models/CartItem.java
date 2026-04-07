package com.moderntech.ecommerce.models;

public record CartItem(Product product, int quantity) {
    public double getTotalPrice() {
        return product.price() * quantity;
    }

    @Override
    public String toString() {
        return String.format("%s x%d = %.2f руб.",
                product.name(), quantity, getTotalPrice());
    }
}