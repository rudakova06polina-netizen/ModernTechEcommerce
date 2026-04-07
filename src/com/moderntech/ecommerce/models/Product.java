package com.moderntech.ecommerce.models;

import com.moderntech.ecommerce.enums.ProductCategory;

public record Product(
        int id,
        String name,
        double price,
        int stock,
        ProductCategory category
) {
    @Override
    public String toString() {
        return String.format("%-3d | %-20s | %8.2f руб. | Остаток: %-3d | %s",
                id, name, price, stock, category);
    }
}