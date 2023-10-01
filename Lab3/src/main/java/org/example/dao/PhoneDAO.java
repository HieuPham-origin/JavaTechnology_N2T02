package org.example.dao;

import antlr.preprocessor.Hierarchy;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.boot.SchemaAutoTooling;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.example.utils.HibernateUtils;
import org.example.domain.Manufacture;
import org.example.domain.Phone;

import java.util.List;
import java.util.Scanner;

public class PhoneDAO {
    public static PhoneDAO getInstance(){
        return new PhoneDAO();
    }
    public boolean add(Phone p){
        try{
            Session session = HibernateUtils.getFactory().openSession();
            Phone phone = new Phone(p.getId(), p.getName(), p.getPrice(), p.getColor(), p.getCountry(), p.getQuantity(), p.getManufacture());
            session.getTransaction().begin();
            session.save(phone);
            session.getTransaction().commit();
            session.close();
            return true;

        }catch (HibernateException e){
            e.printStackTrace();
        }
        return false;
    }


    public Phone get(int id){
        Session session =  HibernateUtils.getFactory().openSession();
        List<Phone> s = session.createQuery("FROM Phone p WHERE p.id =" + id).getResultList();
        if(s.isEmpty()){
            return null;
        }
        session.close();
        return s.get(0);
    }

    public List<Phone> getAll(){
        Session session = HibernateUtils.getFactory().openSession();
        List<Phone> s = session.createQuery("FROM Phone").getResultList();
        session.close();
        return s;

    }

    public boolean remove(int id){
        try{
            Session session = HibernateUtils.getFactory().openSession();
            Phone p = session.get(Phone.class, id);
            session.getTransaction().begin();
            session.delete(p);
            session.getTransaction().commit();
            session.close();
            return true;

        }catch (HibernateException e){
            e.printStackTrace();
        }

        return false;

    }

    public boolean remove(Phone p){
        try{
            remove(p);
            return true;

        }catch (HibernateException e){
            e.printStackTrace();
        }

        return false;
    }

    public boolean update(Phone p){
        try{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Name : ");
            String name = scanner.nextLine();
            System.out.println("Price: ");
            int price = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Color: ");
            String color = scanner.nextLine();
            System.out.println("Country: ");
            String country = scanner.nextLine();
            System.out.println("Quantity: ");
            int quantity = scanner.nextInt();


            Session session = HibernateUtils.getFactory().openSession();
            Phone phone = session.get(Phone.class, p.getId());
            phone.setName(name);
            phone.setPrice(price);
            phone.setColor(color);
            phone.setCountry(country);
            phone.setQuantity(quantity);
            session.getTransaction().begin();
            session.save(phone);
            session.getTransaction().commit();
            session.close();
            return true;
        }catch (HibernateException e){
            e.printStackTrace();
        }
        return false;
    }


    public Phone bestSelling() {
        Session session = HibernateUtils.getFactory().openSession();
        Criteria cr = session.createCriteria(Phone.class);
        cr.setProjection(Projections.max("price"));
        String hql = "From Phone where price = ";
        if (cr.list().isEmpty()) {
            return null;
        } else
            hql +=  cr.list().get(0);
        List<Phone> list = session.createQuery(hql).getResultList();
        return list.get(0);
    }

    public List<Phone> sort() {
        Session session = HibernateUtils.getFactory().openSession();
        Criteria cr = session.createCriteria(Phone.class);
        cr.addOrder(Order.asc("country"));
        cr.addOrder(Order.desc("price"));
        List<Phone> list = cr.list();
        return list;
    }

    public boolean above50Milion() {
        Session session = HibernateUtils.getFactory().openSession();
        Criteria cr = session.createCriteria(Phone.class);
        cr.add(Restrictions.gt("price",50000000));
        if (cr.list().isEmpty())
            return false;
        return true;
    }

    public Phone meetCriteria() {
        Session session = HibernateUtils.getFactory().openSession();
        Criteria cr = session.createCriteria(Phone.class);
        cr.add(Restrictions.gt("price",1500));
        cr.add(Restrictions.ilike("color","Pink"));
        if (cr.list().isEmpty())
            return null;
        return (Phone) cr.list().get(0);
    }

}
