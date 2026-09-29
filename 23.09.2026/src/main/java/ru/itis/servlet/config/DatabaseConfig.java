package ru.itis.servlet.config;

import lombok.experimental.UtilityClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@UtilityClass
public class DatabaseConfig {

    private Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }

        try {
            connection = DriverManager.getConnection(
                    System.getenv("DB_URL"),
                    System.getenv("DB_USER"),
                    System.getenv("DB_PASSWORD")
            );
        } catch (SQLException e) {
            System.err.println("Не удалось создать подключение к бд: " + e.getMessage());
            System.exit(1);
        }
    }

    public Connection getDbConnection() {
        return connection;
    }

}
