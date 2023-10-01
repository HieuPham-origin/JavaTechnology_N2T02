package org.example.domain;
import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name="phone")
public class Phone implements Serializable{
    @Id
    private int id;
    @Column
    private  String name;
    @Column
    private int price;
    @Column
    private String color;
    @Column
    private String country;
    @Column
    private int quantity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "m_id")
    private Manufacture manufacture;
    public Manufacture getManufacture() {
        return manufacture;
    }

    public void setManufacture(Manufacture manufacture) {
        this.manufacture = manufacture;
    }
    public Phone() {

    }

    public Phone(int id, String name, int price, String color, String country, int quantity, Manufacture manufacture) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.color = color;
        this.country = country;
        this.quantity = quantity;
        this.manufacture = manufacture;
    }

    // get, set
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    public String toString() {
        return "Phone{" +  "id=" + id + ", name=" + name  + ", price=" + price + ", color=" + color + ", country=" + country  + ", quantity=" + quantity + ", manufacture_id: "+ manufacture.getId() + '}';
    }
}
