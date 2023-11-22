package com.example.lab9.repository;

import com.example.lab9.model.OrderProduct;
import com.example.lab9.model.OrderProductId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductId> {
}
