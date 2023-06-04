package com.example.orders_service.service.abstraction;

import com.example.orders_service.dto.DishDto;
import com.example.orders_service.entity.Dish;

public interface DishService {
    long createNewDish(DishDto dishDto);

    Dish getDishById(long id);

    void updateDishById(long id, DishDto dishDto);

    void deleteDishById(long id);
}
