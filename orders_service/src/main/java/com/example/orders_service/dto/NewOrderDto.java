package com.example.orders_service.dto;

import lombok.Data;

import java.util.List;

@Data
public class NewOrderDto {
    List<DishToBeOrderedDto> dishes;

    String specialRequests;
}
