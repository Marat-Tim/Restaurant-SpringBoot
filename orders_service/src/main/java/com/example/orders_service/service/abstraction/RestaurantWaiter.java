package com.example.orders_service.service.abstraction;

import com.example.orders_service.dto.NewOrderDto;
import com.example.orders_service.dto.OrderInfoDto;

public interface RestaurantWaiter {
    long createOrder(NewOrderDto newOrderDto, long userId);

    OrderInfoDto getInfoAboutOrder(long orderId, long userId);
}
