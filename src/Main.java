import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

        try (Connection conn = DBConnection.getConnection()) {

            // Create all tables
            DoctorManager.createTable(conn);
            PatientManager.createTable(conn);
            AppointmentManager.createTable(conn);
            MedicalRecordManager.createTable(conn);
            DoctorPatientManager.createTable(conn);

            // Insert sample data
            DoctorManager.insertDoctor(conn, "John", "Doe", "Cardiology", "555-1234", "john.doe@hospital.com");
            DoctorManager.insertDoctor(conn, "Alice", "Smith", "Pediatrics", "555-5678", "alice.smith@hospital.com");
            DoctorManager.insertDoctor(conn, "Emma", "Johnson", "Neurology", "555-9012", "emma.johnson@hospital.com");

            PatientManager.insertPatient(conn, "Bob", "Brown", "2005-04-12", "Male", "555-1111", "bob.brown@gmail.com");
            PatientManager.insertPatient(conn, "Carol", "Davis", "2010-09-23", "Female", "555-2222", "carol.davis@gmail.com");
            PatientManager.insertPatient(conn, "David", "Miller", "2008-07-15", "Male", "555-3333", "david.miller@gmail.com");

            AppointmentManager.insertAppointment(conn, 1, 1, "2026-03-15 10:00:00");
            AppointmentManager.insertAppointment(conn, 2, 2, "2026-03-16 11:30:00");
            AppointmentManager.insertAppointment(conn, 3, 3, "2026-03-17 09:00:00");

            MedicalRecordManager.insertMedicalRecord(conn, 1, 1, "High Blood Pressure", "Prescribed medication");
            MedicalRecordManager.insertMedicalRecord(conn, 2, 2, "Flu", "Rest and hydration");
            MedicalRecordManager.insertMedicalRecord(conn, 3, 3, "Migraine", "Painkillers and rest");

            DoctorPatientManager.insertRelation(conn, 1, 1);
            DoctorPatientManager.insertRelation(conn, 2, 2);
            DoctorPatientManager.insertRelation(conn, 3, 3);

            // Update appointment status example
            AppointmentManager.updateAppointmentStatus(conn, 1, "Completed");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
