package com.baeldung.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;

public class DataSource {

    private DataSource() {}

    private static BasicDataSource dataSource;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");
        dataSource.setMinIdle(5);
        dataSource.setMaxTotal(300);
        dataSource.setMaxWait(Duration.ofMinutes(1));
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
