package com.example.lab9.controller;

import com.example.lab9.model.Order;
import com.example.lab9.model.OrderProduct;
import com.example.lab9.model.Product;
import com.example.lab9.service.OrderProductService;
import com.example.lab9.service.OrderService;
import com.example.lab9.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderProductService orderProductService;
    @GetMapping(value = { "", "/" })
    public List<Order> getAllOrders() {
        return this.orderService.getAllOrders();
    }
    @GetMapping(value = {"/{id}"})
    public Optional<Order> getOrder(@PathVariable Integer id) {
        return orderService.getOrderById(id);
    }

    @DeleteMapping(value = {"/{id}"})
    public ResponseEntity<Product> deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrder(id);
        return new ResponseEntity(null, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {
        List<OrderProduct> orderProducts = order.getOrderItems();
        for (OrderProduct orderProduct : orderProducts) {
            Optional<Product> product = productService.getProductById(orderProduct.getProduct().getProduct_id());
            if (product.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            orderProduct.setOrder(order);
            orderProduct.setProduct(product.get());
        }

        order.setOrderItems(orderProducts);
        Order createdOrder = orderService.createOrder(order);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdOrder.getOrder_id()).toUri());
        return new ResponseEntity<>(createdOrder, headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Integer id, @RequestBody Order order) {
        Optional<Order> existingOrder = orderService.getOrderById(id);
        if (existingOrder.isPresent()) {
            List<OrderProduct> orderProducts = order.getOrderItems();
            for (OrderProduct orderProduct : orderProducts) {
                Optional<Product> product = productService.getProductById(orderProduct.getProduct().getProduct_id());
                if (product.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                orderProduct.setOrder(existingOrder.get());
                orderProduct.setProduct(product.get());
            }

            Order updatedOrder = existingOrder.get();
            updatedOrder.setDay_create(order.getDay_create());
            updatedOrder.setStatus(order.getStatus());
            updatedOrder.setOrderItems(orderProducts);

            orderService.updateOrder(id, updatedOrder);
            return ResponseEntity.ok(updatedOrder);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
