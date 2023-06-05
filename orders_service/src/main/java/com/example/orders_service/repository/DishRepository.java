package com.example.orders_service.repository;

import com.example.orders_service.entity.Dish;
import org.springframework.data.repository.CrudRepository;

public interface DishRepository extends CrudRepository<Dish, Long> {
}
