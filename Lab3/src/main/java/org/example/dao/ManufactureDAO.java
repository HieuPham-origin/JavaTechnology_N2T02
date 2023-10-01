package org.example.dao;

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

import javax.persistence.ManyToOne;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

public class ManufactureDAO {
    public static ManufactureDAO getInstance(){
        return new ManufactureDAO();
    }
    public boolean add(Manufacture m){
        try{
            Session session = HibernateUtils.getFactory().openSession();
            Manufacture manu = new Manufacture(m.getId(), m.getName(), m.getLocation(), m.getEmployee());
            session.getTransaction().begin();
            session.save(manu);
            session.getTransaction().commit();
            session.close();
            return true;

        }catch (HibernateException e){
            e.printStackTrace();
        }
        return false;
    }

//
    public Manufacture get(int id){

        Session session =  HibernateUtils.getFactory().openSession();
        Manufacture m = session.get(Manufacture.class, id);
        session.close();
        return m;
    }

    public List<Manufacture> getAll(){
        Session session = HibernateUtils.getFactory().openSession();
        List<Manufacture> m = session.createQuery("FROM Manufacture ").getResultList();
        session.close();
        return m;

    }

    public boolean remove(int id){
        try{
            Session session = HibernateUtils.getFactory().openSession();
            Manufacture m = session.get(Manufacture.class, id);
            session.getTransaction().begin();
            session.delete(m);
            session.getTransaction().commit();
            session.close();
            return true;

        }catch (HibernateException e){
            e.printStackTrace();
        }

        return false;

    }

    public boolean remove(Manufacture m){
        try{
            remove(m);
            return true;

        }catch (HibernateException e){
            e.printStackTrace();
        }

        return false;
    }

    public boolean update(Manufacture m){
        try{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Name : ");
            String name = scanner.nextLine();
            System.out.println("Location: ");
            String location = scanner.nextLine();
            System.out.println("Employee: ");
            int emp = scanner.nextInt();

            Session session = HibernateUtils.getFactory().openSession();
            Manufacture manu = session.get(Manufacture.class, m.getId());
            manu.setName(name);
            manu.setLocation(location);
            manu.setEmployee(emp);
            session.getTransaction().begin();
            session.save(manu);
            session.getTransaction().commit();
            session.close();
            return true;
        }catch (HibernateException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean MoreT100() {
        Session session = HibernateUtils.getFactory().openSession();
        Criteria cr = session.createCriteria(Manufacture.class);
        cr.add(Restrictions.gt("employee",100));
        if (cr.list().isEmpty())
            return false;
        return true;
    }

    public int count() {
        Session session = HibernateUtils.getFactory().openSession();
        Criteria cr = session.createCriteria(Manufacture.class);
        cr.setProjection(Projections.sum("employee"));
        if (cr.list().isEmpty()){
            return -1;
        }
        String str = cr.list().get(0).toString();
        return Integer.parseInt(str);
    }

    public Manufacture meetCriteria() {
        Session session = HibernateUtils.getFactory().openSession();
        Criteria cr = session.createCriteria(Manufacture.class);
        cr.add(Restrictions.ilike("location","%usa%"));
        if (cr.list().isEmpty())
            return null;
        return (Manufacture) cr.list().get(0);
    }

}
