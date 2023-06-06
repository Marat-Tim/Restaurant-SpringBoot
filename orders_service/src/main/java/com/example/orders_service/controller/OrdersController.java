package com.example.orders_service.controller;

import com.example.orders_service.api.OrdersApi;
import com.example.orders_service.domain.OrderException;
import com.example.orders_service.dto.NewOrderDto;
import com.example.orders_service.dto.OrderInfoDto;
import com.example.orders_service.security.JwtAuthentication;
import com.example.orders_service.service.abstraction.RestaurantWaiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrdersController implements OrdersApi {
    private final RestaurantWaiter restaurantWaiter;

    @Override
    public long createOrder(NewOrderDto newOrderDto) {
        return restaurantWaiter.createOrder(
                newOrderDto,
                ((JwtAuthentication) SecurityContextHolder.getContext().getAuthentication()).getUserId());
    }

    @Override
    public OrderInfoDto getInfoAboutOrder(long orderId) {
        return restaurantWaiter.getInfoAboutOrder(orderId,
                ((JwtAuthentication) SecurityContextHolder.getContext().getAuthentication()).getUserId());
    }

    @ExceptionHandler(OrderException.class)
    public ErrorResponse handleException(OrderException e) {
        return ErrorResponse.create(e, HttpStatus.NOT_FOUND, e.getMessage());
    }
}
