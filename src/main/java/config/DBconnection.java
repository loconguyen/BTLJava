package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {

    private static String url = "jdbc:mysql://localhost:3306/qldh?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static String login = "root";
    private static String pass = "123456";


    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Không load được driver", e);
        }
    }

  
    public static Connection openConnection() throws SQLException {
        return DriverManager.getConnection(url, login, pass);
    }

    
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}