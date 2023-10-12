package tdtu.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Product {
    private int id;
    private String name;
    private int price;
    public Product(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    @Override
    public String toString(){
        return "id: " + this.id + ", name: " + this.name + ", price: " + this.price;
    }
}
