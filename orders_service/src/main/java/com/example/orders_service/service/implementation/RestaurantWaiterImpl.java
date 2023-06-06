package com.example.orders_service.service.implementation;

import com.example.orders_service.domain.OrderException;
import com.example.orders_service.dto.DishToBeOrderedDto;
import com.example.orders_service.dto.NewOrderDto;
import com.example.orders_service.dto.OrderInfoDto;
import com.example.orders_service.entity.Dish;
import com.example.orders_service.entity.Order;
import com.example.orders_service.entity.OrderDish;
import com.example.orders_service.repository.DishRepository;
import com.example.orders_service.repository.OrderDishRepository;
import com.example.orders_service.repository.OrderRepository;
import com.example.orders_service.service.abstraction.RestaurantWaiter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantWaiterImpl implements RestaurantWaiter {
    private final OrderRepository orderRepository;

    private final OrderDishRepository orderDishRepository;

    private final DishRepository dishRepository;

    @Override
    public long createOrder(NewOrderDto newOrderDto, long userId) {
        Order order = new Order();
        order.setUserId(userId);
        order.setSpecialRequests(newOrderDto.getSpecialRequests());
        return saveOrderWithDishes(order, newOrderDto.getDishes());
    }

    @Override
    public OrderInfoDto getInfoAboutOrder(long orderId, long userId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderException("Заказа с таким id не существует"));
        if (order.getUserId() != userId) {
            throw new AccessDeniedException("Заказ с таким id принадлежит другому пользователю");
        }
        OrderInfoDto orderInfoDto = new OrderInfoDto();
        orderInfoDto.setStatus(order.getStatus());
        return orderInfoDto;
    }

    private long saveOrderWithDishes(Order order, List<DishToBeOrderedDto> dishes) {
        var savedOrder = orderRepository.save(order);
        List<Dish> dishEntities = new ArrayList<>(dishes.size());
        for (var dish : dishes) {
            dishEntities.add(dishRepository.findById(dish.getId())
                    .orElseThrow(() ->
                            new OrderException(
                                    String.format("Блюда с id = %d не существует", dish.getId()))));
        }
        for (int i = 0; i < dishEntities.size(); i++) {
            OrderDish orderDish = new OrderDish();
            orderDish.setDish(dishEntities.get(i));
            orderDish.setOrder(savedOrder);
            orderDish.setQuantity(dishes.get(i).getCount());
            orderDish.setPrice(
                    dishEntities.get(i).getPrice()
                            .multiply(new BigDecimal(orderDish.getQuantity())));
            orderDishRepository.save(orderDish);
        }
        return savedOrder.getId();
    }
}
