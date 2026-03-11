import java.sql.*;

public class StudentManager {

    // Create the 'students' table if it doesn't exist
    public static void createTable(Connection conn) throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS students (
                id SERIAL PRIMARY KEY,
                name VARCHAR(100) NOT NULL,
                age INT NOT NULL,
                grade VARCHAR(10)
            );
        """;

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Students table created (if it didn't exist).");
        }
    }

    // Insert a student into the table
    public static void insertStudent(Connection conn, String name, int age, String grade) throws SQLException {
        String sql = "INSERT INTO students(name, age, grade) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, grade);
            pstmt.executeUpdate();
            System.out.println("Inserted student: " + name);
        }
    }

    // Fetch and display all students
    public static void fetchStudents(Connection conn) throws SQLException {
        String sql = "SELECT * FROM students";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n--- Students List ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        ", Name: " + rs.getString("name") +
                        ", Age: " + rs.getInt("age") +
                        ", Grade: " + rs.getString("grade"));
            }
        }
    }

    // Check if a table exists in the database
    public static void checkTableExists(Connection conn, String tableName) throws SQLException {
        DatabaseMetaData metaData = conn.getMetaData();
        try (ResultSet tables = metaData.getTables(null, "public", tableName, null)) {
            if (tables.next()) {
                System.out.println("Table '" + tableName + "' exists!");
            } else {
                System.out.println("Table '" + tableName + "' does NOT exist!");
            }
        }
    }
}