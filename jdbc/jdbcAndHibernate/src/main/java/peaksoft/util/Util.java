package peaksoft.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String userName = "postgres";
    private static final String password = "1234";
    private static final String url = "jdbc:postgresql://localhost:5432/jdbc";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, userName, password);
            System.out.println("DB action");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

}
