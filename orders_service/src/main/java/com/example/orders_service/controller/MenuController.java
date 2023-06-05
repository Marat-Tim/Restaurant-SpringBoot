package com.example.orders_service.controller;

import com.example.orders_service.api.MenuApi;
import com.example.orders_service.dto.DishInfoForManagerDto;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MenuController implements MenuApi {
    @Override
    public List<DishInfoForManagerDto> getMenu() {
        return null;
    }
}
