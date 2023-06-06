package com.example.orders_service.api;

import com.example.orders_service.dto.DishDto;
import com.example.orders_service.dto.DishInfoForManagerDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/dish")
public interface DishApi {
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    long createNewDish(@RequestBody DishDto dishDto);

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    DishInfoForManagerDto getDish(@RequestParam long id);

    @PatchMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    void updateDish(@RequestParam long id, @RequestBody DishDto updateDishDto);

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    void deleteDish(@RequestParam long id);
}
