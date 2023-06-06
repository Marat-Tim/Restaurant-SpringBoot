package com.example.orders_service.api;

import com.example.orders_service.dto.DishInfoForUserDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@RequestMapping("/menu")
public interface MenuApi {
    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    List<DishInfoForUserDto> getMenu();
}
