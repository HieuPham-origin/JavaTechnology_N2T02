package com.example.lab9.model;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "orderproduct")
@Getter @Setter @NoArgsConstructor
public class OrderProduct {
    @EmbeddedId
    private OrderProductId id;

    @ManyToOne
    @MapsId("order_id")
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @MapsId("product_id")
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    public OrderProduct(Order order, Product product) {
        this.order = order;
        this.product = product;
        this.id = new OrderProductId(order.getOrder_id(), product.getProduct_id());
    }
}