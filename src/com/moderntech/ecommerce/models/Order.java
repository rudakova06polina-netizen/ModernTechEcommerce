package com.moderntech.ecommerce.models;

import com.moderntech.ecommerce.enums.OrderStatus;
import com.moderntech.ecommerce.payment.PaymentStatus;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {
    private final String id;
    private final Customer customer;
    private final List<OrderItem> orderItems;
    private OrderStatus status;
    private PaymentStatus paymentStatus;
    private final LocalDateTime createdAt;

    public Order(Customer customer, List<CartItem> cartItems) {
        this.id = UUID.randomUUID().toString().substring(0, 8);
        this.customer = customer;
        this.orderItems = new ArrayList<>();
        for (CartItem item : cartItems) {
            this.orderItems.add(new OrderItem(
                    item.product(),
                    item.quantity(),
                    item.product().price()
            ));
        }
        this.status = OrderStatus.PENDING;
        this.paymentStatus = PaymentStatus.PENDING;
        this.createdAt = LocalDateTime.now();
    }

    public String getId() { return id; }
    public Customer getCustomer() { return customer; }
    public List<OrderItem> getOrderItems() { return new ArrayList<>(orderItems); }
    public OrderStatus getStatus() { return status; }
    public PaymentStatus getPaymentStatus() { return paymentStatus; }

    public void setStatus(OrderStatus newStatus) {
        System.out.printf("Статус заказа #%s изменён: %s → %s%n",
                id, this.status, newStatus);
        this.status = newStatus;
    }

    public void setPaymentStatus(PaymentStatus newStatus) {
        this.paymentStatus = newStatus;
    }

    public double getTotalAmount() {
        return orderItems.stream().mapToDouble(OrderItem::getTotalPrice).sum();
    }

    public void displayOrder() {
        System.out.println("\n=== Заказ #" + id + " ===");
        System.out.println("Дата: " + createdAt.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")));
        System.out.println("Покупатель: " + customer.getName());
        System.out.println("Статус: " + status);
        System.out.println("Статус оплаты: " + paymentStatus);
        System.out.println("\nТовары:");
        for (OrderItem item : orderItems) {
            System.out.println("  " + item);
        }
        System.out.printf("Общая сумма: %.2f руб.%n", getTotalAmount());
    }
}