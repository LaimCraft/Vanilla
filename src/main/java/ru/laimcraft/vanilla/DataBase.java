package ru.laimcraft.vanilla;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataBase {

    public static HikariDataSource dataSource;
    private static HikariConfig config;

    public DataBase() {
        config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://127.0.0.1/?");
        config.setUsername(Settings.user);
        config.setPassword(Settings.password);

        // Настройка пула соединений
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.setConnectionTimeout(30000);
        config.setIdleTimeout(600000);
        config.setMaxLifetime(1800000);

        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void close() {
        if (dataSource != null) dataSource.close();
    }

}
