package com.example.orders_service.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DishInfoForUserDto {
    long id;

    private String name;

    private String description;

    private BigDecimal price;

    private int quantity;

    private boolean isAvailable;
}
