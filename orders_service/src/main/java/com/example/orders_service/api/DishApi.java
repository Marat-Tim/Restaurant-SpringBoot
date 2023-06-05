package com.example.orders_service.api;

import com.example.orders_service.dto.DishDto;
import com.example.orders_service.dto.DishInfoForManagerDto;
import com.example.orders_service.entity.Dish;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/dish")
public interface DishApi {
    @PostMapping("/create")
    long createNewDish(@RequestBody DishDto dishDto);

    @GetMapping("/get")
    DishInfoForManagerDto getDish(@RequestParam long id);

    @PatchMapping("/update")
    void updateDish(@RequestParam long id, @RequestBody DishDto updateDishDto);

    @DeleteMapping("/delete")
    void deleteDish(@RequestParam long id);
}
