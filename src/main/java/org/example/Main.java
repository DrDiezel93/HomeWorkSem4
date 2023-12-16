package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        // Создание сессии
        Session session = sessionFactory.getCurrentSession();

        // Начало транзакции
        session.beginTransaction();

        String sql = "From " + Course.class.getSimpleName();
        System.out.println("sql = " + sql);

        // Создание объекта
        Course course = Course.create();
        session.save(course);
        System.out.println("Object student save successfully");

        // Чтение из базы
        Course retrievedStudent = session.get(Course.class, course.getId());
        System.out.println("Object student retrieved successfully");
        System.out.println("Retrieved student object: " + retrievedStudent);

        // Обновление объекта
        retrievedStudent.updateName();
        retrievedStudent.updateAge();
        session.update(retrievedStudent);
        System.out.println("Object student update successfully");

        session.delete(retrievedStudent);
        System.out.println("Object student delete successfully");

        session.getTransaction().commit();
        session.close();
    }

    private static void useDatabase(Connection connection) throws SQLException {
        String useDatabaseSQL =  "USE schoolDB;";
        try (PreparedStatement statement = connection.prepareStatement(useDatabaseSQL)) {
            statement.execute();
        }
    }

    private static void createTable(Connection connection) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS courses (id INT AUTO_INCREMENT PRIMARY KEY, title VARCHAR(255), duration INT);";
        try (PreparedStatement statement = connection.prepareStatement(createTableSQL)) {
            statement.execute();
        }
    }
}
