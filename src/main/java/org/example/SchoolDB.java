package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.List;

public class SchoolDB {
    private static final SessionFactory sessionFactory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Course.class)
            .buildSessionFactory();
    private static final int ID_DELL = 5;
    private static final int ID_UP = 3;

    public SchoolDB() {
    }

    public static void createCourse(){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Course course = Course.create();
        session.save(course);
        System.out.println("Object course save successfully");
        session.getTransaction().commit();
        session.close();
    }

    public static void printCourse(){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Course> list = session.createQuery("from Course").list();
        list.forEach(System.out::println);
        session.close();
    }

    public static void updateCourse(){
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            String hql = "FROM Course WHERE id = :id";
            Query courseQuery = session.createQuery(hql, Course.class);
            courseQuery.setParameter("id", ID_UP);
            Course course = (Course) courseQuery.getSingleResult();
            course.updateAge();
            course.updateName();
            session.update(course);
            System.out.println("Object course update successfully");
            session.getTransaction().commit();
            session.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void dellCourse(){
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            String hql = "FROM Course WHERE id = :id";
            Query courseQuery = session.createQuery(hql, Course.class);
            courseQuery.setParameter("id", ID_DELL);
            Course course = (Course) courseQuery.getSingleResult();
            session.delete(course);
            System.out.println("Object course delete successfully");
            session.getTransaction().commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void dellAllCourse(){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Course> list = session.createQuery("from Course").list();
        list.forEach(session::delete);
        session.getTransaction().commit();
        System.out.println("Objects delete successfully");
        session.close();
    }
}
