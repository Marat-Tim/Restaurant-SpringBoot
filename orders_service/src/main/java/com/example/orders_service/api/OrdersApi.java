package com.example.orders_service.api;

import com.example.orders_service.dto.NewOrderDto;
import com.example.orders_service.dto.OrderInfoDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/order")
public interface OrdersApi {
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    long createOrder(@RequestBody NewOrderDto newOrderDto);

    @GetMapping("/info")
    @ResponseStatus(HttpStatus.OK)
    OrderInfoDto getInfoAboutOrder(@RequestHeader long orderId);
}
