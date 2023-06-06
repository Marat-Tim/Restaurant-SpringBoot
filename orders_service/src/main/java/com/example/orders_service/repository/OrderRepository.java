package com.example.orders_service.repository;

import com.example.orders_service.entity.Order;
import org.springframework.data.repository.CrudRepository;


public interface OrderRepository extends CrudRepository<Order, Long> {
}
