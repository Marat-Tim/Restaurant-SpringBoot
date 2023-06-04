package com.example.orders_service.controller;

import com.example.orders_service.api.OrdersApi;
import com.example.orders_service.dto.NewOrderDto;
import com.example.orders_service.dto.OrderInfoDto;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrdersController implements OrdersApi {

    @Override
    public long createOrder(NewOrderDto newOrderDto) {
        return 0;
    }

    @Override
    public OrderInfoDto getInfoAboutOrder(Long orderId) {
        return null;
    }
}
