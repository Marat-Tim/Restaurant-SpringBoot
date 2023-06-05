package com.example.orders_service.api;

import com.example.orders_service.dto.DishInfoForManagerDto;
import com.example.orders_service.dto.DishInfoForUserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/menu")
public interface MenuApi {
    @GetMapping("/get")
    List<DishInfoForUserDto> getMenu();
}
