package com.example.orders_service.repository;

import com.example.orders_service.entity.Dish;
import org.apache.commons.lang3.stream.Streams;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

public interface DishRepository extends CrudRepository<Dish, Long> {
    default List<Dish> getAllWhereIsAvailableAndQuantityMoreThanZero() {
        var all = StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(findAll().iterator(), Spliterator.ORDERED),
                false);
         return all.filter(dish -> dish.isAvailable() && dish.getQuantity() > 0).toList();
    }
}
