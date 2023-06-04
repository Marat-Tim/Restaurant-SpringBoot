package com.example.orders_service.api;

import com.example.orders_service.dto.DishDto;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/dish")
public interface DishApi {
    @PostMapping("/create")
    Long createNewDish(@RequestBody DishDto dishDto);

    @GetMapping("/get")
    DishDto getDish(@RequestParam long id);

    @PatchMapping("/update")
    void updateDish(@RequestParam long id, @RequestBody DishDto updateDishDto);

    @DeleteMapping("/delete")
    void deleteDish(@RequestParam long id);
}
