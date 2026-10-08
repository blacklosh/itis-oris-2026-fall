package ru.itis.servlet.config;

import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@UtilityClass
public class DatabaseConfig {

    private Connection connection;

    @Getter
    private DataSource dataSource;

    @Getter
    private JdbcTemplate jdbcTemplate;

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

        loadDataSource();
        loadJdbcTemplate();
    }

    private void loadJdbcTemplate() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private void loadDataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUsername(System.getenv("DB_USER"));
        ds.setPassword(System.getenv("DB_PASSWORD"));
        ds.setUrl(System.getenv("DB_URL"));
        dataSource = ds;
    }

    public Connection getDbConnection() {
        return connection;
    }

}
