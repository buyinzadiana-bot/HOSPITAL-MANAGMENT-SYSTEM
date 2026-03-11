import java.sql.*;

public class AppointmentManager {

    public static void createTable(Connection conn) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS appointments (
                    id SERIAL PRIMARY KEY,
                    doctor_id INT REFERENCES doctors(id) ON DELETE CASCADE,
                    patient_id INT REFERENCES patients(id) ON DELETE CASCADE,
                    appointment_date TIMESTAMP NOT NULL,
                    status VARCHAR(20) DEFAULT 'Scheduled',
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                );
                """;
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Appointments table created.");
        }
    }

    public static void insertAppointment(Connection conn, int doctorId, int patientId, String dateTime) throws SQLException {
        String sql = "INSERT INTO appointments(doctor_id, patient_id, appointment_date) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, doctorId);
            pstmt.setInt(2, patientId);
            pstmt.setTimestamp(3, Timestamp.valueOf(dateTime));
            pstmt.executeUpdate();
            System.out.println("Inserted appointment for patient ID " + patientId);
        }
    }

    public static void updateAppointmentStatus(Connection conn, int appointmentId, String status) throws SQLException {
        String sql = "UPDATE appointments SET status = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, status);
            pstmt.setInt(2, appointmentId);
            pstmt.executeUpdate();
            System.out.println("Updated appointment ID " + appointmentId + " to " + status);
        }
    }
}
