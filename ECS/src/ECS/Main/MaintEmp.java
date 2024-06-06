/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ECS.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class MaintEmp extends Employee{
    // --- Variables ---
    public int maintID;
    public int[][] empSkills;
    public int equipLostCount;
    public boolean warningGiven;
    private String lastLostDate;
    private String lastDamagedDate;
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/CEIS400_group_project";
    private static final String DB_USER = "groupc";
    private static final String DB_PASSWORD = "oI209[^X`XHF";
    private static Connection connection = null;
    
    // Constructor
    MaintEmp(final int _maintID, int[][] _empSkills) {
        this.empID = _maintID;
        this.empSkills = _empSkills;
    }
    
    // --- Functions ---
    static {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Set maintanence employee skills
    public static void setSkillset(MaintEmp emp){
        String sql = "INSERT INTO MaintEmp (maintID, empSkills) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, emp.maintID);
            pstmt.setInt(2, emp.empSkills[0][0]); // Temporary, gotta work in this
            pstmt.executeUpdate();
            System.out.println("Employee skill set added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Set checked out items
    public static void setOutItems(){
        
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
        
}
