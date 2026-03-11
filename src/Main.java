import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

        try (Connection conn = DBConnection.getConnection()) {

            // Create the students table
            DoctorManager.createTable(conn);

            // Insert sample students
            DoctorManager.insertStudent(conn, "Alice", 15, "10th");
            DoctorManager.insertStudent(conn, "Bob", 16, "11th");
            DoctorManager.insertStudent(conn, "Charlie", 14, "9th");

            // Fetch and display students
            DoctorManager.fetchStudents(conn);

            // Check if the students table exists
            DoctorManager.checkTableExists(conn, "students");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}