package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.*;
import java.util.List;

public class Main {
    private final static String url = "jdbc:mysql://localhost:3306/";
    private final static String user = "root";
    private final static String password = "8520";
    public static void main(String[] args) {
        try(Connection connection = DriverManager.getConnection(url, user, password)){
            // Создание базы данных
            createDatabase(connection);
            System.out.println("Database created successfully");
            // Использование базы данных
            useDatabase(connection);
            System.out.println("Use database successfully");
            // Создание таблицы
            createTable(connection);
            System.out.println("Create table successfully");
            SchoolDB.createCourse();
            SchoolDB.printCourse();
//            SchoolDB.updateCourse();
//            SchoolDB.printCourse();
//            SchoolDB.dellCourse();
//            SchoolDB.printCourse();
//            SchoolDB.dellAllCourse();
//            dropSchema(connection);
//            System.out.println("Database delete successfully");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static void createDatabase(Connection connection) throws SQLException {
        String createDatabaseSQL =  "CREATE DATABASE IF NOT EXISTS schoolDB;";
        try (PreparedStatement statement = connection.prepareStatement(createDatabaseSQL)) {
            statement.execute();
        }
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

    private static void dropSchema(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("DROP SCHEMA schoolDB");
    }
}
