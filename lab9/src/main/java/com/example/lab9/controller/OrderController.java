package com.example.lab9.controller;

import com.example.lab9.dto.OrderDto;
import com.example.lab9.dto.OrderProductDto;
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

import javax.validation.constraints.NotNull;
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
    OrderService orderService;

    @Autowired
    ProductService productService;

    @Autowired
    OrderProductService orderProductService;

    @GetMapping(value = { "", "/" })
    public @NotNull Iterable<Order> getOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping(value = {"/{id}"})
    public Order getOrder(@PathVariable int id) throws Exception {
        return orderService.getOrder(id);
    }



    private void validateProductsExistence(List<OrderProductDto> orderProducts) {
        List<OrderProductDto> list = orderProducts
                .stream()
                .filter(op -> {
                    try {
                        return Objects.isNull(productService.getProductById(op
                                .getProduct()
                                .getProduct_id()));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(list)) {
            new Exception("Product not found");
        }
    }

    @PutMapping(value = {"/{id}"})
    public ResponseEntity<Order> updateProduct(@PathVariable int id, @RequestBody OrderDto orderDto) throws Exception {
        Order order = orderService.getOrder(id);
        if (orderDto.getStatus() != null) order.setStatus(orderDto.getStatus());
        orderService.update(order);
        return new ResponseEntity(order, HttpStatus.OK);
    }

    @DeleteMapping(value = {"/{id}"})
    public ResponseEntity<Product> deleteOrder(@PathVariable int id) {
        orderService.removeById(id);
        return new ResponseEntity(null, HttpStatus.OK);
    }
}
