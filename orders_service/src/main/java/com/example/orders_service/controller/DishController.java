package com.example.orders_service.controller;

import com.example.orders_service.api.DishApi;
import com.example.orders_service.domain.DishException;
import com.example.orders_service.dto.DishDto;
import com.example.orders_service.dto.DishInfoForManagerDto;
import com.example.orders_service.service.abstraction.Warehouse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('manager')")
public class DishController implements DishApi {
    private final Warehouse warehouse;

    @Override
    public long createNewDish(DishDto dishDto) {
        return warehouse.createNewDish(dishDto);
    }

    @Override
    public DishInfoForManagerDto getDish(long id) {
        return warehouse.getDishById(id);
    }

    @Override
    public void updateDish(long id, DishDto dishDto) {
        warehouse.updateDishById(id, dishDto);
    }

    @Override
    public void deleteDish(long id) {
        warehouse.deleteDishById(id);
    }

    @ExceptionHandler(DishException.class)
    public ErrorResponse handleException(DishException e) {
        return ErrorResponse.create(e, HttpStatus.NOT_FOUND, e.getMessage());
    }
}
