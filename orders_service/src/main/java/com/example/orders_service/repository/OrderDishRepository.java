package com.example.orders_service.repository;

import com.example.orders_service.entity.OrderDish;
import org.springframework.data.repository.CrudRepository;

public interface OrderDishRepository extends CrudRepository<OrderDish, Long> {
}
