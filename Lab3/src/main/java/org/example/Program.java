package org.example;

import org.hibernate.Session;
import org.example.utils.HibernateUtils;
import org.example.domain.Phone;
import org.example.domain.Manufacture;
import org.example.dao.ManufactureDAO;
import org.example.dao.PhoneDAO;
import java.util.ArrayList;
import java.util.List;
public class Program {
    public static void main(String[] args) {
        //ADD PHONE
//        Manufacture m = new Manufacture(1, "apple", "usa", 100);
//        ManufactureDAO.getInstance().add(m);
//        Phone p = new Phone (1, "iphone 11", 12000000, "black", "USA", 10, m);
//        System.out.println(PhoneDAO.getInstance().add(p));
//        Manufacture m2 = new Manufacture(2, "samsung", "korea", 100);
//        Phone p2 = new Phone (2, "iphone X", 9000000, "black", "USA", 10, m);
//        System.out.println(PhoneDAO.getInstance().add(p2));
//        System.out.println(PhoneDAO.getInstance().remove(2));


        //get Phone by ID
//        System.out.println(PhoneDAO.getInstance().get(1));
//
//        //get all Phone
//        System.out.println("Get all phones: ");
//        for(Phone p : PhoneDAO.getInstance().getAll()){
//            System.out.println(p);
//        }

////        delete Phone by ID
//        System.out.println(PhoneDAO.getInstance().remove(1));
        Manufacture m2 = new Manufacture(3, "samsung", "usa", 150);
////
//        System.out.println(ManufactureDAO.getInstance().add(m2));
        // delete Phone by Object
//        System.out.println(PhoneDAO.getInstance().remove(p));
        Phone p3 = new Phone (8, "iphone 14", 90000000, "black", "USA", 10, m2);
//        System.out.println(PhoneDAO.getInstance().add(p3));
          //Update
//        System.out.println(PhoneDAO.getInstance().update(p3));






        System.out.println(ManufactureDAO.getInstance().get(2).toString());

        //        System.out.println("Get all manufacture: ");
        for(Manufacture m : ManufactureDAO.getInstance().getAll()){
            System.out.println(m.toString());
        }

//        System.out.println(ManufactureDAO.getInstance().remove("m1"));


        System.out.println(PhoneDAO.getInstance().bestSelling());
        System.out.println(PhoneDAO.getInstance().above50Milion());
        System.out.println(PhoneDAO.getInstance().meetCriteria());


        System.out.println(ManufactureDAO.getInstance().MoreT100());
        System.out.println(ManufactureDAO.getInstance().count());
        System.out.println(ManufactureDAO.getInstance().meetCriteria());

    }
}
