package org.example.domain;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="manufacture")
public class Manufacture implements Serializable{
    @Id
    private int id;
    @Column
    private String name;
    @Column
    private String location;
    @Column
    private int employee;

    @OneToMany(mappedBy = "manufacture")
    private List<Phone> phones;
    public Manufacture(){}
    public Manufacture(int id, String name, String location, int employee){
        this.id = id;
        this.name = name;
        this.location = location;
        this.employee = employee;
    }
    public Manufacture(int id, String name, String location, int employee, List<Phone> phones){
        this.id = id;
        this.name = name;
        this.location = location;
        this.employee = employee;
        this.phones = phones;
    }

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getEmployee() {
        return employee;
    }

    public void setEmployee(int employee) {
        this.employee = employee;
    }

    public List<Phone> getProducts() {
        return phones;
    }

    public void setProducts(List<Phone> products) {
        this.phones = products;
    }

    @Override
    public String toString() {
        return "Manufacture {"+this.id+" "+this.name+" "+this.location+" "+this.employee+"}";
    }
}
