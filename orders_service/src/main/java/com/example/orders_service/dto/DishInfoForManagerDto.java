package com.example.orders_service.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class DishInfoForManagerDto {
    private String name;

    private String description;

    private BigDecimal price;

    private int quantity;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
