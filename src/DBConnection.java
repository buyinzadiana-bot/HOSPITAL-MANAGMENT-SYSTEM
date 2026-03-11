import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/HospitalManagement";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Eustache1998";

    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("Database connected successfully!");
        return conn;
    }


}