package com.example.orders_service.api;

import com.example.orders_service.dto.NewOrderDto;
import com.example.orders_service.dto.OrderInfoDto;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/order")
public interface OrdersApi {
    @PostMapping("/create")
    long createOrder(@RequestBody NewOrderDto newOrderDto);

    @GetMapping("/info")
    OrderInfoDto getInfoAboutOrder(@RequestHeader Long orderId);
}
