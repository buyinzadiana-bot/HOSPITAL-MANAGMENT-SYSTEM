import java.sql.*;

public class PatientManager {

    public static void createTable(Connection conn) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS patients (
                    id SERIAL PRIMARY KEY,
                    first_name VARCHAR(50) NOT NULL,
                    last_name VARCHAR(50) NOT NULL,
                    date_of_birth DATE,
                    gender VARCHAR(10),
                    phone_number VARCHAR(15),
                    email VARCHAR(100) UNIQUE NOT NULL,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                );
                """;
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Patients table created.");
        }
    }

    public static void insertPatient(Connection conn, String firstName, String lastName,
                                     String dob, String gender, String phone, String email) throws SQLException {
        String sql = "INSERT INTO patients(first_name, last_name, date_of_birth, gender, phone_number, email) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setDate(3, Date.valueOf(dob));
            pstmt.setString(4, gender);
            pstmt.setString(5, phone);
            pstmt.setString(6, email);
            pstmt.executeUpdate();
            System.out.println("Inserted patient: " + firstName + " " + lastName);
        }
    }
}
