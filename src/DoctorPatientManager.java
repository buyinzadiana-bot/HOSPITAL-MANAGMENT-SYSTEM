import java.sql.*;

public class DoctorPatientManager {

    public static void createTable(Connection conn) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS doctor_patient (
                    doctor_id INT REFERENCES doctors(id) ON DELETE CASCADE,
                    patient_id INT REFERENCES patients(id) ON DELETE CASCADE,
                    PRIMARY KEY (doctor_id, patient_id)
                );
                """;
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Doctor-Patient pivot table created.");
        }
    }

    public static void insertRelation(Connection conn, int doctorId, int patientId) throws SQLException {
        String sql = "INSERT INTO doctor_patient(doctor_id, patient_id) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, doctorId);
            pstmt.setInt(2, patientId);
            pstmt.executeUpdate();
            System.out.println("Linked doctor " + doctorId + " with patient " + patientId);
        }
    }
}
