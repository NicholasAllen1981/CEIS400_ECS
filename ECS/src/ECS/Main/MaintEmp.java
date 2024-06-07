/**
 *
 * @author Nicholas Allen, Daniel F Diaz Santiago
 */

package ECS.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.HashMap;

public class MaintEmp extends Employee{
    // --- Variables ---
    public int maintID;
    public Map<String, Integer> empSkills = new HashMap<>();    
    public int currentItem;
    public int equipLostCount;      // Waiting on GUI to use this
    static public boolean warningGiven;    // Waiting on GUI to use this
    private String lastLostDate;    // Waiting on GUI to use this
    private String lastDamagedDate; // Waiting on GUI to use this
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/CEIS400_group_project";
    private static final String DB_USER = "groupc";
    private static final String DB_PASSWORD = "oI209[^X`XHF";
    private static Connection connection = null;
    
    
    // Constructor
    MaintEmp() {
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

    // Set maintanence employee skills
    public static void setSkillset() {
        
    }
    
    // Set checked out items
    public static void setOutItems(){
        
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
        
}
