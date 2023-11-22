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
    public Order createOrder(Order order){
        order.setDay_create(LocalDate.now());
        return this.orderRepository.save(order);
    }
    @Override
    public Optional<Order> getOrderById(int id){
        return orderRepository.findById(id);
    }

    @Override
    public void deleteOrder(int id){
        Optional<Order> order = orderRepository.findById(id);
        if(order.isPresent()){
            orderRepository.deleteById(id);
        }
        else{
            return;
        }
    }
    @Override
    public Order updateOrder(int id, Order order){
        Optional<Order> update = orderRepository.findById(id);
        if(update.isPresent()){
            order.setOrder_id(id);
            return orderRepository.save(order);
        }else{
            return null;
        }
    }
}
