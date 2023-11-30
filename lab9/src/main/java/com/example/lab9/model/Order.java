package com.example.lab9.model;
import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter@ToString@Getter
@NoArgsConstructor@AllArgsConstructor
@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int order_id;
    private LocalDate day_create;
    private String status;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProduct> orderItems = new ArrayList<>();
    @Transient
    public Double getTotalOrderPrice() {
        double sum = 0D;
        List<OrderProduct> orderProducts = getOrderItems();
        for (OrderProduct op : orderProducts) {
            sum += op.getTotalPrice();
        }
        return sum;
    }

    @Transient
    public int getNumberOfProducts() {
        return this.orderItems.size();
    }
}
