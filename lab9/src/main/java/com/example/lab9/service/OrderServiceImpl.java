package com.example.lab9.service;

import com.example.lab9.model.Order;
import com.example.lab9.model.Product;
import com.example.lab9.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public List<Order> getAllOrders(){
        return this.orderRepository.findAll();
    }
    @Override
    public Order create(Order order) {
        order.setDay_create(LocalDate.now());
        return this.orderRepository.save(order);
    }

    @Override
    public void update(Order order) {
        this.orderRepository.save(order);
    }

    @Override
    public Order getOrder(int id) {
        return this.orderRepository.findById(id).get();
    }

    @Override
    public void removeById(int id) {
        this.orderRepository.deleteById(id);
    }
}
