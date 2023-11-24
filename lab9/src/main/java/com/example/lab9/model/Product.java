package com.example.lab9.model;
import lombok.*;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Setter@ToString@Getter
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int product_id;
    private String brand;
    private int price;
    private String product_name;
    private String color;
    @Builder
    public Product (int id, String product_name, int price, String brand, String color){
        this.product_id = id;

        this.brand = brand;
        this.price = price;
        this.product_name = product_name;

        this.color = color;
    }
}
