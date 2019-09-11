package com.lambdaschool.crudyrestaurants.services;

import com.lambdaschool.crudyrestaurants.models.Order;

import java.util.List;

public interface OrderService
{
    List<Order> findAll();

    Order findOrderById(long id);

    void delete(long id);

    Order save(Order order);

    Order update(Order order, long id);
}
