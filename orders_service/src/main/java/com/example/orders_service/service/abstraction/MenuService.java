package com.example.orders_service.service.abstraction;

import com.example.orders_service.dto.DishInfoForUserDto;

import java.util.List;

public interface MenuService {
    List<DishInfoForUserDto> getAllAvailableDishes();
}
