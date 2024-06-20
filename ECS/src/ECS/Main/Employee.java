/**
 *
 * @author Nicholas Allen, Daniel F Diaz Santiago
 */
package ECS.Main;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Employee {

    /* ----------------     Class variables     ---------------- */
    public static int empID;
    public static int DepotEmpID;
    public static int maintID;
    private static String empPass;
    public static String empFirstName;
    public static String empLastName;
    public static String empEmail;
    public static String empAddress;
    public static String empCity;
    public static String empState;
    public static String empZip;
    public static Long empPhone;
    public static String empSkills;
    public static String empStatus;
    //public static int empDepotID;
    //public static int maintID;

    /* ----------------     Database variables  ---------------- */
    private static final String DB_URL = "jdbc:mysql://localhost:3306/CEIS400_group_project";
    private static final String DB_USER = "groupc";
    private static final String DB_PASSWORD = "oI209[^X`XHF";
    private static Connection connection = null;
    private static SecureRandom random = new SecureRandom();

    /* ----------------     Email variables     ---------------- */
    // Temporary email until we make one. We can make a temporary gmail.
    final private static String from = "email@gmail.com";
    final private static String password = "password";

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

    /* ----------------     Functions       ---------------- */
 /* ----------------     Generating Functions (Start)       ---------------- */
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

    /* ----------------     Generating Functions (End)       ---------------- */
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
        String subject;
        String body;
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
        
        /* ---------------- Employee termination email notification ---------------- */
        if (!action) {
            subject = "Notice of Termination of Employment";
            body = "Dear " + empFirstName + " " + empLastName + ",\n\n"
                    + "I hope this message finds you well.\n\n"
                    + "We regret to inform you that, effective " + today.format(formatter) + ", your employment with GB Manufacturing will "
                    + "be terminated. This decision is final and has been made after careful consideration.\n\n"
                    + "We request that you return any company property in your possession by " + today.plusDays(5).format(formatter) + ".\n\n"
                    + "We appreciate the efforts you have contributed to the company during your tenure. If you have any questions or "
                    + "need further clarification, please do not hesitate to contact HR at (915) 555-0123.\n\n"
                    + "We wish you all the best in your future endeavors.\n\n"
                    + "Sincerely,\n\n"
                    + "John Smith\n"
                    + "Human Resources Manager\n"
                    + "GB Manufacturing\n"
                    + "(915) 555-0123";

            sendEmail(empEmail, subject, body);  // Email notification
            System.out.println("Employee Terminated\nEmail notification sent to "
                    + empFirstName + " " + empLastName + " (Email: " + empEmail + ")");
        }

        /* ---------------- New employee email notification ---------------- */
        subject = "Welcome to GB Manufacturing - New Employee Information";
        body = "Dear " + empFirstName + " " + empLastName + ",\n\n"
                + "Welcome to GB Manufacturing! We are excited to have you join our team.\n\n"
                + "As part of our onboarding process, here are your new employee details:\n\n"
                + "Employee ID: " + Integer.toString(empID) + "\n"
                + "Depot Employee ID: " + Integer.toString(DepotEmpID) + "\n"
                + "Maintenance Employee ID: " + Integer.toString(maintID) + "\n"
                + "Temporary Password: " + empPass + "\n"
                + "Please make sure to change your temporary password after your first login for security purposes.\n\n"
                + "If you have any questions or need further assistance, feel free to reach out to our HR department "
                + "at (915) 555-0123.\n\n"
                + "Once again, welcome to the team! We look forward to working with you.\n\n"
                + "Best regards,\n\n"
                + "John Smith\n"
                + "Human Resources Manager\n"
                + "GB Manufacturing\n"
                + "(915) 555-0123";

        sendEmail(empEmail, subject, body);  // Email notification
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

    public static void sendEmail(String to, String subject, String body) {
        // Setup mail server
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

    }
}
