import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

        try (Connection conn = DBConnection.getConnection()) {

            // Create the students table
            StudentManager.createTable(conn);

            // Insert sample students
            StudentManager.insertStudent(conn, "Alice", 15, "10th");
            StudentManager.insertStudent(conn, "Bob", 16, "11th");
            StudentManager.insertStudent(conn, "Charlie", 14, "9th");

            // Fetch and display students
            StudentManager.fetchStudents(conn);

            // Check if the students table exists
            StudentManager.checkTableExists(conn, "students");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}