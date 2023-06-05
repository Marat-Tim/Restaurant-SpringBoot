package com.example.orders_service.controller;

import com.example.orders_service.api.DishApi;
import com.example.orders_service.dto.DishDto;
import com.example.orders_service.dto.DishInfoForManagerDto;
import com.example.orders_service.entity.Dish;
import com.example.orders_service.service.abstraction.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('manager')")
public class DishController implements DishApi {
    private final DishService dishService;

    @Override
    public long createNewDish(DishDto dishDto) {
        return dishService.createNewDish(dishDto);
    }

    @Override
    public DishInfoForManagerDto getDish(long id) {
        return dishService.getDishById(id);
    }

    @Override
    public void updateDish(long id, DishDto dishDto) {
        dishService.updateDishById(id, dishDto);
    }

    @Override
    public void deleteDish(long id) {
        dishService.deleteDishById(id);
    }
}
