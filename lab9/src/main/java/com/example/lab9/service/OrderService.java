package com.example.lab9.service;

import com.example.lab9.model.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> getAllOrders();
    Order create(Order order);

    void update(Order order);

    Order getOrder(int id);

    void removeById(int id);
}
