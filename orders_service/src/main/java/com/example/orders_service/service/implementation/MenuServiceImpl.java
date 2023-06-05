package com.example.orders_service.service.implementation;

import com.example.orders_service.dto.DishInfoForUserDto;
import com.example.orders_service.repository.DishRepository;
import com.example.orders_service.service.abstraction.DishMapper;
import com.example.orders_service.service.abstraction.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final DishRepository dishRepository;

    private final DishMapper dishMapper;

    @Override
    public List<DishInfoForUserDto> getAllAvailableDishes() {
        return dishRepository.getAllWhereIsAvailableAndQuantityMoreThanZero()
                .stream().map(dishMapper::mapToDishForUserDto).toList();
    }
}
