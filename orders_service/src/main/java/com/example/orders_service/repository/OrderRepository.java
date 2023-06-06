package com.example.orders_service.repository;

import com.example.orders_service.domain.OrderStatus;
import com.example.orders_service.entity.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> searchFirstByStatusIn(List<OrderStatus> statuses);
}
