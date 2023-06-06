package com.example.orders_service.controller;

import com.example.orders_service.api.MenuApi;
import com.example.orders_service.domain.DishException;
import com.example.orders_service.dto.DishInfoForUserDto;
import com.example.orders_service.service.abstraction.Warehouse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MenuController implements MenuApi {
    private final Warehouse warehouse;

    @Override
    public List<DishInfoForUserDto> getMenu() {
        return warehouse.getAllAvailableDishes();
    }
}
