package com.example.orders_service.service.abstraction;

import com.example.orders_service.dto.DishDto;
import com.example.orders_service.dto.DishInfoForManagerDto;
import com.example.orders_service.dto.DishInfoForUserDto;

import java.util.List;

public interface Warehouse {
    List<DishInfoForUserDto> getAllAvailableDishes();

    long createNewDish(DishDto dishDto);

    DishInfoForManagerDto getDishById(long id);

    void updateDishById(long id, DishDto dishDto);

    void deleteDishById(long id);
}
