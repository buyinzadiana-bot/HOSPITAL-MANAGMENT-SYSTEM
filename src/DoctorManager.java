import java.sql.*;
import java.sql.*;

public class DoctorManager {

    public static void createTable(Connection conn) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS doctors (
                    id SERIAL PRIMARY KEY,
                    first_name VARCHAR(50) NOT NULL,
                    last_name VARCHAR(50) NOT NULL,
                    specialty VARCHAR(50),
                    phone_number VARCHAR(15),
                    email VARCHAR(100) UNIQUE NOT NULL,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                );
                """;
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Doctors table created.");
        }
    }

    public static void insertDoctor(Connection conn, String firstName, String lastName, String specialty,
                                    String phone, String email) throws SQLException {
        String sql = "INSERT INTO doctors(first_name, last_name, specialty, phone_number, email) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, specialty);
            pstmt.setString(4, phone);
            pstmt.setString(5, email);
            pstmt.executeUpdate();
            System.out.println("Inserted doctor: " + firstName + " " + lastName);
        }
    }
}
