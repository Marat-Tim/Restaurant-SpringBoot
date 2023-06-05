package com.example.orders_service.service.abstraction;

import com.example.orders_service.dto.DishDto;
import com.example.orders_service.dto.DishInfoForManagerDto;
import com.example.orders_service.dto.DishInfoForUserDto;
import com.example.orders_service.entity.Dish;
import org.mapstruct.Mapper;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface DishMapper {
    Dish mapToDishEntity(DishDto dishDto);

    DishInfoForManagerDto mapToDishForManagerDto(Dish dish);

    DishInfoForUserDto mapToDishForUserDto(Dish dish);

    default LocalDateTime mapToLocalDateTime(Timestamp timestamp) {
        return timestamp.toLocalDateTime();
    }
}
