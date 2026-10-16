package hy.material8database.database;

import hy.material8database.config.AppConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static Connection connection;

    public static Connection connect() {
        try {
            if (connection == null || connection.isClosed()) {
                String url = "jdbc:postgresql://"
                    + AppConfig.HOST + ":"
                    + AppConfig.PORT + "/"
                    + AppConfig.DATABASE;

                connection = DriverManager.getConnection(
                    url,
                    AppConfig.USER,
                    AppConfig.PASSWORD
                );

                System.out.println("Koneksi database berhasil.");
            }

            return connection;
        } catch (SQLException e) {
            System.out.println("Koneksi database gagal.");
            System.out.println(e.getMessage());
            return null;
        }
    }
}