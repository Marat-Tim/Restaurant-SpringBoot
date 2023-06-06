package com.example.orders_service.service.implementation;

import com.example.orders_service.domain.DishException;
import com.example.orders_service.dto.DishDto;
import com.example.orders_service.dto.DishInfoForManagerDto;
import com.example.orders_service.dto.DishInfoForUserDto;
import com.example.orders_service.entity.Dish;
import com.example.orders_service.repository.DishRepository;
import com.example.orders_service.service.abstraction.DishMapper;
import com.example.orders_service.service.abstraction.Warehouse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseImpl implements Warehouse {
    private final DishRepository dishRepository;

    private final DishMapper dishMapper;

    @Override
    public List<DishInfoForUserDto> getAllAvailableDishes() {
        return dishRepository.getAllWhereIsAvailableAndQuantityMoreThanZero()
                .stream().map(dishMapper::mapToDishForUserDto).toList();
    }

    @Override
    public long createNewDish(DishDto dishDto) {
        Dish dish = dishMapper.mapToDishEntity(dishDto);
        Dish savedDish = dishRepository.save(dish);
        return savedDish.getId();
    }

    @Override
    public DishInfoForManagerDto getDishById(long id) {
        Dish dish = dishRepository.findById(id)
                .orElseThrow(DishException::dishNotFound);
        return dishMapper.mapToDishForManagerDto(dish);
    }

    @Override
    public void updateDishById(long id, DishDto dishDto) {
        if (!dishRepository.existsById(id)) {
            throw DishException.dishNotFound();
        }
        Dish dish = dishMapper.mapToDishEntity(dishDto);
        dish.setId(id);
        dishRepository.save(dish);
    }

    @Override
    public void deleteDishById(long id) {
        if (!dishRepository.existsById(id)) {
            throw DishException.dishNotFound();
        }
        dishRepository.deleteById(id);
    }
}
