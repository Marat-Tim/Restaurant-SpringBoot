package com.example.orders_service.repository;

import com.example.orders_service.entity.Order;
import com.example.orders_service.entity.OrderDish;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderDishRepository extends CrudRepository<OrderDish, Long> {
    List<OrderDish> findByOrder(Order order);
}
