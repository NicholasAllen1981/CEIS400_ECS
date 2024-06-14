/**
 *
 * @author Nicholas Allen, Daniel F Diaz Santiago
 */
package ECS.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import java.sql.ResultSet;
import java.security.SecureRandom;

public class Employee {

    // --- Variables ---
    public static int empID;
    public static int DepotEmpID;
    public static int maintID;
    private String empPass;
    public static String empFirstName;
    public static String empLastName;
    public static String empEmail;
    public static String empAddress;
    public static String empCity;
    public static String empState;
    public static String empZip;
    public static Long empPhone;
    /*
        empPhone needs to be BIGINT or STRING in database.
        int is too small for a full phone number, and long is too big for an INT in sql.
        As of now, adding and employee will always fail because of this.
    */
    public static String empSkills;
    public static String empStatus;
    //public static int empDepotID;
    //public static int maintID;

    // Database variables
    private static final String DB_URL = "jdbc:mysql://localhost:3306/CEIS400_group_project";
    private static final String DB_USER = "groupc";
    private static final String DB_PASSWORD = "oI209[^X`XHF";
    private static Connection connection = null;
    private static SecureRandom random = new SecureRandom();

    // Contructor
    Employee(String _empFirstName, String _empLastName, String _empAddress, 
            String _empCity, String _empState, String _empZip, Long _empPhone, 
            String _empEmail, String _empSkills, String _empStatus) {
        this.empFirstName = _empFirstName;
        this.empLastName = _empLastName;
        this.empAddress = _empAddress;
        this.empCity = _empCity;
        this.empState = _empState;
        this.empZip = _empZip;
        this.empPhone = _empPhone;
        this.empEmail = _empEmail;
        this.empSkills = _empSkills;
        this.empStatus = _empStatus;
    }

    // Constructor
    Employee() {
    }

    // Database (MySQL) connection
    static {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // --- Functions ---
    // Used by generatePass()
    private static String shuffleString(String input) {
        char[] characters = input.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            int randomIndex = random.nextInt(characters.length);
            char temp = characters[i];
            characters[i] = characters[randomIndex];
            characters[randomIndex] = temp;
        }
        return new String(characters);
    }

    // Generate a temporary password for new employees
    private final static String generatePass() {
        final String UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
        final String DIGITS = "0123456789";
        final String SPECIAL_CHARACTERS = "!@#$%^&*()-_=+<>?";
        final String ALL_CHARACTERS = UPPER_CASE + LOWER_CASE + DIGITS + SPECIAL_CHARACTERS;
        final int length = 10;

        if (length < 4) {
            throw new IllegalArgumentException("Password length must be at least 4 characters");
        }

        StringBuilder password = new StringBuilder(length);

        // Ensure the password contains at least one of each required character type
        password.append(UPPER_CASE.charAt(random.nextInt(UPPER_CASE.length())));
        password.append(LOWER_CASE.charAt(random.nextInt(LOWER_CASE.length())));
        password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        password.append(SPECIAL_CHARACTERS.charAt(random.nextInt(SPECIAL_CHARACTERS.length())));

        // Fill the remaining characters
        for (int i = 4; i < length; i++) {
            password.append(ALL_CHARACTERS.charAt(random.nextInt(ALL_CHARACTERS.length())));
        }

        // Shuffle the characters to avoid predictable patterns
        return shuffleString(password.toString());
    }

    // Generate a unique empID
    private final static int generateEmpID() {
        String sql = "SELECT MAX(empID) FROM Employee";
        int nextEmpID = 1;
        try (PreparedStatement pstmt = connection.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                int maxEmpID = rs.getInt(1);
                nextEmpID = maxEmpID + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nextEmpID;
    }

     // Generate a unique DepotEmpID
    private final static int generateDepotEmpID() {
        String sql = "SELECT MAX(DepotEmpID) FROM Employee";
        int nextDepotEmpID = 1;
        try (PreparedStatement pstmt = connection.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                int maxEmpID = rs.getInt(1);
                nextDepotEmpID = maxEmpID + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nextDepotEmpID;
    }

    // Generate a unique MaintID
    private final static int generateMaintID() {
        String sql = "SELECT MAX(maintID) FROM Employee";
        int nextMaintID = 1;
        try (PreparedStatement pstmt = connection.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                int maxEmpID = rs.getInt(1);
                nextMaintID = maxEmpID + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nextMaintID;
    }

    // Add Employee (new hire)
    public static void addEmp(Employee emp) {
        emp.empPass = generatePass();
        emp.empID = generateEmpID();
        emp.DepotEmpID = generateDepotEmpID();
        emp.maintID = generateMaintID();
        JFrame frame = new JFrame();

        String sql = "INSERT INTO Employee (empID, FirstName, LastName, empPassword, empAddress, empCity, empState, empZip, "
                + "empPhone, empEmail, empSkills, TerminationStatus, DepotEmpID, maintID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, emp.empID);
            pstmt.setString(2, emp.empFirstName);
            pstmt.setString(3, emp.empLastName);
            pstmt.setString(4, emp.empPass);
            pstmt.setString(5, emp.empAddress);
            pstmt.setString(6, emp.empCity);
            pstmt.setString(7, emp.empState);
            pstmt.setString(8, emp.empZip);
            pstmt.setLong(9, emp.empPhone);
            pstmt.setString(10, emp.empEmail);
            pstmt.setString(11, emp.empSkills);
            pstmt.setString(12, emp.empStatus);
            pstmt.setInt(13, emp.DepotEmpID);
            pstmt.setInt(14, emp.maintID);
            pstmt.executeUpdate();
            System.out.println("Employee added successfully.");
            notifyEmp(true);
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) { // MySQL error code for duplicate entry
                System.out.println("Employee ID already exists.");
                JOptionPane.showMessageDialog(frame, "Employee ID already exists.", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                e.printStackTrace();
            }
        } finally {
            closeConnection();
        }
    }

    // Terminate Employee
    public static void terminateEmp(int empID) {
        String sql = "UPDATE Employee SET TerminationStatus = 'Terminated' WHERE empID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, empID);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Employee ID " + empID + " terminated successfully.");
                notifyEmp(false); // Email notification - successful termination
            } else {
                // ** Add a warning dialog **
                JFrame frame = new JFrame();
                JOptionPane.showMessageDialog(frame, "Employee ID " + empID + " not found.", "Warning", JOptionPane.WARNING_MESSAGE);
                System.out.println("Employee ID " + empID + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    // Notify Employee (send email)
    // ** if action == false, send notification of termination **
    // ** if action == true, send notification for added **
    private static void notifyEmp(boolean action) {
        // Implement email notification logic here
        // Send the generated temporary password to the new employee (empPass)
        // Send empID, DepotEmpID, and maintID
        if (!action) {
            System.out.println("Employee Terminated\nEmail notification sent to "
                    + empFirstName + " " + empLastName + " (Email: " + empEmail + ")");
        }
        System.out.println("Employee Added\nEmail notification sent to " + empFirstName
                + " " + empLastName + " (Email: " + empEmail + ")");
    }

    // Close the connection when the application is done
    private static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Connection closed");
    }

    public static void main(String[] args) {

    }
}
