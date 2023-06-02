package com.example.orders_service.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "order_dish")
@Data
public class OrderDish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "dish_id", referencedColumnName = "id", nullable = false)
    private Dish dish;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "price", precision = 10, scale = 2, nullable = false)
    private BigDecimal price;
}

