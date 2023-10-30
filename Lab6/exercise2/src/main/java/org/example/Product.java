package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Setter@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private int id;
    private String name;
    private double price;
    private String description;

    public String toString(){
        return "id: " + this.id + " - product name: " + this.name + " - price: " + this.price
                + "$ - description: " + this.description + ".";
    }
}
