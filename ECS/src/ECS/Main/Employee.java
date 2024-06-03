package ECS.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Employee {

    // --- Variables ---
    public int empID;
    private String empPass;
    public String empName;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/DB_NAME";
    private static final String DB_USER = "username";
    private static final String DB_PASSWORD = "password";
    private static Connection connection = null;

    // Contructor
    Employee(int empID, String empName, String empPass) {
        this.empID = empID;
        this.empName = empName;
        this.empPass = empPass;
    }

    // Constructor
    Employee() {
    }

    static {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // --- Functions ---
    // Add Employee (new hire)
    public static void addEmp(Employee emp) {
        String sql = "INSERT INTO employees (empID, empName, empPass) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, emp.empID);
            pstmt.setString(2, emp.empName);
            pstmt.setString(3, emp.empPass);
            pstmt.executeUpdate();
            //System.out.println("Employee added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Terminate Employee
    public static void terminateEmp(int empID) {
        String sql = "DELETE FROM employees WHERE empID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, empID);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                //System.out.println("Employee terminated successfully.");
            } else {
                //System.out.println("Employee not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Notify Employee (send email)
    public static void notifyEmp(String empName) {
        // Implement email notification logic here
        //System.out.println("Email notification sent to " + empName);
    }

    // Close the connection when the application is done
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        //Employee newEmp = new Employee(1, "Test", "password123");
        //addEmp(newEmp);
        //notifyEmp(newEmp.empName);
        //terminateEmp(1);
        //closeConnection();
    }
}
