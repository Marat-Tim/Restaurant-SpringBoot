package com.example.orders_service.service.implementation;

import com.example.orders_service.domain.OrderStatus;
import com.example.orders_service.entity.Dish;
import com.example.orders_service.entity.Order;
import com.example.orders_service.entity.OrderDish;
import com.example.orders_service.repository.DishRepository;
import com.example.orders_service.repository.OrderDishRepository;
import com.example.orders_service.repository.OrderRepository;
import com.example.orders_service.service.abstraction.Chef;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChefImpl implements Chef {
    private final OrderRepository orderRepository;

    private final OrderDishRepository orderDishRepository;

    private final DishRepository dishRepository;

    @Override
    @Scheduled(fixedDelay = 10 * 1000)
    @Transactional(rollbackFor = ChefException.class)
    public void cook() {
        List<Order> orders = orderRepository.searchFirstByStatusIn(
                List.of(OrderStatus.in_progress));
        if (orders.isEmpty()) {
            return;
        }
        int randomIndex = new Random().nextInt(orders.size());
        Order order = orders.get(randomIndex);
        List<OrderDish> orderDishList = orderDishRepository.findByOrder(order);
        for (OrderDish orderDish : orderDishList) {
            if (orderDish.getQuantity() > orderDish.getDish().getQuantity()) {
                throw new ChefException("На складе не хватило товаров");
            } else if (!orderDish.getDish().isAvailable()) {
                throw new ChefException("Товар не доступен");
            } else {
                Dish dish = orderDish.getDish();
                dish.setQuantity(dish.getQuantity() - orderDish.getQuantity());
                dishRepository.save(dish);
            }
        }
        order.setStatus(OrderStatus.ready);
        orderRepository.save(order);
        log.info("Повар приготовил заказ номер {}", order.getId());
    }

    private static class ChefException extends RuntimeException {
        ChefException(String message) {
            super(message);
        }
    }
}
