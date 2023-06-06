package com.example.orders_service.dto;

import com.example.orders_service.domain.OrderStatus;
import lombok.Data;

@Data
public class OrderInfoDto {
    OrderStatus status;
}
