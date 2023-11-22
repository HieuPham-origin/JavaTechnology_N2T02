package com.example.lab9.service;


import com.example.lab9.model.OrderProduct;
import com.example.lab9.repository.OrderProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderProductServiceImpl implements OrderProductService{
    private OrderProductRepository orderProductRepository;
    public OrderProductServiceImpl(OrderProductRepository orderProductRepository){
        this.orderProductRepository = orderProductRepository;
    }
    @Override
    public OrderProduct createOrderProduct(OrderProduct orderProduct){
        return this.orderProductRepository.save(orderProduct);
    }
}
