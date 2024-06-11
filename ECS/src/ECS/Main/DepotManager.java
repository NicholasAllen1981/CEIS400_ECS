/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ECS.Main;

/*
This is the part of the Database you're working with, adjust your work with this:

CREATE TABLE IF NOT EXISTS `ceis400_group_project`.`DepotManager` (
  `mgmtID` INT NOT NULL,
  `authCount` INT NULL,
  PRIMARY KEY (`mgmtID`))
ENGINE = InnoDB;

*/


/**
 *
 * @author Mircale Holman
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DepotManager extends DepotEmp { // *** DepotEmp(int empID, String name, int overrideRequest) ***
    
    // --- Variables ---
    private int mgmtID;
    private int authCount;
    private static final String DB_USER = "username";
    private static final String DB_PASSWORD = "password";
    
    // --- Functions ---
    
    // Method to verify if an employee has the skills required to use a tool
    private static boolean verifySkills(int employeeID, String toolName) {
        try {
            // Establish database connection
            
            // *** Make sure to close the connection after the function is done ***
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database_name", DB_USER, DB_PASSWORD);
            
            // Prepare SQL query
            String sql = "SELECT * FROM Employee WHERE empID = ? AND empSkills = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, employeeID);
            stmt.setString(2, toolName);
            
            // Execute query
            ResultSet rs = stmt.executeQuery();
            
            // Check if employee has the required skill for the tool
            if (rs.next()) {
                return true; // Employee has the required skill
            } else {
                return false; // Employee does not have the required skill
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Error occurred during database operation
        }
    }
    
    // Method to override skills for a tool
    private static void overrideSkills(int employeeID, String toolName) {
        try {
            // Establish database connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database_name", DB_USER, DB_PASSWORD);
            
            // Prepare SQL query
            String sql = "INSERT INTO employee_skills (empID, empSkills) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, employeeID);
            stmt.setString(2, toolName);
            
            // Execute query
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Override successful: Employee now has skills for the tool.");
            } else {
                System.out.println("Override failed: Unable to add skills for the tool.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Method to authorize a tool purchase

         private static void authPurchase(int employeeID, int toolID) {
        try {
            // Establish database connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database_name", DB_USER, DB_PASSWORD);
            
            // Check if the tool is needed for the employee
            String checkNeedSQL = "SELECT * FROM Employee WHERE empID = ? AND empSkills = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkNeedSQL);
            checkStmt.setInt(1, employeeID);
            checkStmt.setInt(2, toolID);
            ResultSet rs = checkStmt.executeQuery();
            
            if (rs.next()) {
                // Tool is needed for the employee, authorize purchase
                // Assuming the purchase is authorized by adding an entry to a purchase table
                
                // *** change "purchase_requests", it doesn't exist ***
                String authorizePurchaseSQL = "INSERT INTO purchase_requests (empID, empSkills) VALUES (?, ?)"; 
                PreparedStatement authorizeStmt = conn.prepareStatement(authorizePurchaseSQL);
                authorizeStmt.setInt(1, employeeID);
                authorizeStmt.setInt(2, toolID);
                int rowsInserted = authorizeStmt.executeUpdate();
                
                if (rowsInserted > 0) {
                    System.out.println("Purchase of tool ID " + toolID + " authorized for employee ID " + employeeID);
                } else {
                    System.out.println("Failed to authorize purchase of tool ID " + toolID + " for employee ID " + employeeID);
                }
            } else {
                System.out.println("Tool ID " + toolID + " is not needed for employee ID " + employeeID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Method to authorize a tool transfer
    private static void authTransfer(int toolID, String fromFacility, String toFacility) {
        // Implementation to authorize transferprivate static void authTransfer(int toolID, String fromFacility, String toFacility) {
        try {
            // Establish database connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database_name", DB_USER, DB_PASSWORD);
            
            // Check if the tool is available at the 'from' facility
            
            
            // *** "facility_inventory" "tool_id" "facility_name" don't exist. ***
            String checkAvailabilitySQL = "SELECT * FROM facility_inventory WHERE tool_id = ? AND facility_name = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkAvailabilitySQL);
            checkStmt.setInt(1, toolID);
            checkStmt.setString(2, fromFacility);
            ResultSet rs = checkStmt.executeQuery();
            
            if (rs.next()) {
                // Tool is available at the 'from' facility, authorize transfer
                // Assuming the transfer is authorized by updating the inventory record
                
                
                // *** "facility_inventory" "facility_name" and "tool_id" don't exist. ***
                String updateInventorySQL = "UPDATE facility_inventory SET facility_name = ? WHERE tool_id = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateInventorySQL);
                updateStmt.setString(1, toFacility);
                updateStmt.setInt(2, toolID);
                int rowsUpdated = updateStmt.executeUpdate();
                
                if (rowsUpdated > 0) {
                    System.out.println("Transfer of tool ID " + toolID + " authorized from " + fromFacility + " to " + toFacility);
                } else {
                    System.out.println("Failed to authorize transfer of tool ID " + toolID);
                }
            } else {
                System.out.println("Tool ID " + toolID + " is not available at " + fromFacility);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Method to return equipment for a terminated employee
    private static void returnEmpEquip(int employeeID) {
        try {
            // Establish database connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database_name", DB_USER, DB_PASSWORD);
            
            // Prepare SQL query to retrieve equipment assigned to the terminated employee
            String retrieveEquipmentSQL = "SELECT * FROM Employee WHERE empID = ?";
            PreparedStatement retrieveStmt = conn.prepareStatement(retrieveEquipmentSQL);
            retrieveStmt.setInt(1, employeeID);
            ResultSet rs = retrieveStmt.executeQuery();
            
            // Loop through the retrieved equipment
            while (rs.next()) {
                int equipmentID = rs.getInt("itemID");
                
                // Update the status of the equipment to indicate it's returned
                
                // *** itemID is an INT ***
                String updateStatusSQL = "UPDATE Equipment SET status = 'Returned' WHERE itemID = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateStatusSQL);
                updateStmt.setInt(1, equipmentID);
                int rowsUpdated = updateStmt.executeUpdate();
                
                if (rowsUpdated > 0) {
                    System.out.println("Equipment with ID " + equipmentID + " returned successfully.");
                } else {
                    System.out.println("Failed to update status for equipment with ID " + equipmentID);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DepotManager(int empID, String empName, int overrideRequest) {
        super(empID, empName, overrideRequest);
    }
}

