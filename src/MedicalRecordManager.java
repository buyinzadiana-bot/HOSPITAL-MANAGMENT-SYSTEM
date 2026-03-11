import java.sql.*;

public class MedicalRecordManager {

    public static void createTable(Connection conn) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS medical_records (
                    id SERIAL PRIMARY KEY,
                    patient_id INT REFERENCES patients(id) ON DELETE CASCADE,
                    doctor_id INT REFERENCES doctors(id),
                    diagnosis TEXT,
                    treatment TEXT,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                );
                """;
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Medical Records table created.");
        }
    }

    public static void insertMedicalRecord(Connection conn, int patientId, int doctorId,
                                           String diagnosis, String treatment) throws SQLException {
        String sql = "INSERT INTO medical_records(patient_id, doctor_id, diagnosis, treatment) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, patientId);
            pstmt.setInt(2, doctorId);
            pstmt.setString(3, diagnosis);
            pstmt.setString(4, treatment);
            pstmt.executeUpdate();
            System.out.println("Inserted medical record for patient ID " + patientId);
        }
    }
}
