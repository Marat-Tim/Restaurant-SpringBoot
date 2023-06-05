package com.example.orders_service.service.implementation;

import com.example.orders_service.domain.DishException;
import com.example.orders_service.dto.DishDto;
import com.example.orders_service.dto.DishInfoForManagerDto;
import com.example.orders_service.entity.Dish;
import com.example.orders_service.repository.DishRepository;
import com.example.orders_service.service.abstraction.DishMapper;
import com.example.orders_service.service.abstraction.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {
    private final DishRepository dishRepository;

    private final DishMapper dishMapper;

    @Override
    public long createNewDish(DishDto dishDto) {
        Dish dish = dishMapper.mapToDishEntity(dishDto);
        Dish savedDish = dishRepository.save(dish);
        return savedDish.getId();
    }

    @Override
    public DishInfoForManagerDto getDishById(long id) {
        Dish dish = dishRepository.findById(id)
                .orElseThrow(() -> new DishException("Блюда с таким id не существует"));
        return dishMapper.mapToDishForManagerDto(dish);
    }

    @Override
    public void updateDishById(long id, DishDto dishDto) {
        if (dishRepository.findById(id).isEmpty()) {
            throw new DishException("Блюда с таким id не существует");
        }
        Dish dish = dishMapper.mapToDishEntity(dishDto);
        dish.setId(id);
        dishRepository.save(dish);
    }

    @Override
    public void deleteDishById(long id) {
        dishRepository.deleteById(id);
    }
}
