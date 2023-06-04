package com.example.orders_service.controller;

import com.example.orders_service.api.DishApi;
import com.example.orders_service.dto.DishDto;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DishController implements DishApi {
    @Override
    public Long createNewDish(DishDto dishDto) {
        return null;
    }

    @Override
    public DishDto getDish(long id) {
        return null;
    }

    @Override
    public void updateDish(long id, DishDto updateDishDto) {

    }

    @Override
    public void deleteDish(long id) {

    }
}
