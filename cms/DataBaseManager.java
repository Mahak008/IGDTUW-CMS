package cms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseManager {

    static final String DB_URL = "jdbc:mysql://localhost:3306/project";
    static final String USER = "root";
    static final String PASS = "Bharti@304";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public static void insertStudent(String name,String enroll, String email, String department, String password) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            String sql = "INSERT INTO student_info (sname, enrollment_number,email, department, password) VALUES (?,?,?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, enroll);
            stmt.setString(3, email);
            stmt.setString(4, department);
            stmt.setString(5, password);
            stmt.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            closeConnection(conn, stmt, null);
        }
    }
}