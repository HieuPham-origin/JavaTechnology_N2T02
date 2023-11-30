package com.example.lab9.model;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "orderproduct")
@NoArgsConstructor
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
    @Transient
    public Product getProduct() {
        return this.id.getProduct();
    }

    @Transient
    public int getTotalPrice() {
        return getProduct().getPrice() * getQuantity();
    }
    public OrderProductId getPk() {
        return id;
    }

    public void setPk(OrderProductId id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof OrderProduct))
            return false;
        OrderProduct other = (OrderProduct) o;

        return this.id == other.id;
    }

    @Override
    public final int hashCode() {
        return id.hashCode();
    }

}