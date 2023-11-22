package com.example.lab9.service;

import com.example.lab9.model.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> getAllOrders();
    Order createOrder(Order order);
    Optional<Order> getOrderById(int id);
    void deleteOrder(int id);
    Order updateOrder(int id, Order order);
}
