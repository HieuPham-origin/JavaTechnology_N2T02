package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Setter @Getter
@AllArgsConstructor
public class Product {
    private int id;
    private String name;
    private Double price;
    private String description;

    public Product(Product product) {
        this.name = product.name;
        this.id = product.id;
        this.price = product.price;
        this.description = product.description;
    }
}

