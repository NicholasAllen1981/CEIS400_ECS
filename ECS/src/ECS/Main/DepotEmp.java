/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//author Freeman King
package ECS.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 * Author: Freeman King
 */

/*
This is the part of the Database you're working with, adjust your work with this:

CREATE TABLE IF NOT EXISTS `ceis400_group_project`.`DepotEmp` (
  `DepotEmpID` INT NOT NULL,
  `overrideRequest` INT UNSIGNED NULL,
  `mgmtID` INT NULL,
  PRIMARY KEY (`DepotEmpID`),
  INDEX `mgmtID_idx` (`mgmtID` ASC) VISIBLE,
  CONSTRAINT `mgmtID`
    FOREIGN KEY (`mgmtID`)
    REFERENCES `group_project`.`DepotManager` (`mgmtID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
*/


public class DepotEmp extends Employee {
    // --- Variables ---
    private int overrideRequest;
    private int empID;
    private String empName;
    // --- Database Connection Variables ---
    final String DB_URL = Settings.getJdbcUrl();
    final String DB_USER = Settings.getDbUsername();
    final String DB_PASSWORD = Settings.getDbPassword();
    private static Connection connection = null;

    // Constructor
    public DepotEmp(int empID, String empName, int overrideRequest) {
        this.empID = empID;
        this.empName = empName;
                
        //super(empID, empName); // *** Employee class doesn't have this constructor, if you need one with just these two variables, let me know ***
        this.overrideRequest = overrideRequest;
        
        // Initialize database connection when the first instance is created
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                System.out.println("Database connection successfully established.");
            } catch (Exception e) {
                System.out.println("Database connection failed: " + e.getMessage());
            }
        }
    }

    // --- Functions ---

    // Add Depot Employee to Database
    public void addDepotEmpToDB() {
        String sql = "INSERT INTO depot_employees (empID, name, overrideRequest) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            /** BELOW NEEDS FIXED**
            
            pstmt.setInt(1, this.getEmpID()); // Assuming Employee class has getEmpID()
            pstmt.setString(2, this.getName()); // Assuming Employee class has getName()
            
            ABOVE NEEDS FIXED  **/
            pstmt.setInt(3, this.overrideRequest);
            pstmt.executeUpdate();
            System.out.println("Depot employee added to database successfully.");
        } catch (Exception e) {
            System.out.println("Error adding depot employee to database: " + e.getMessage());
        }
    }

    // Request Equipment Transfer
    public static void requestTransfer(int itemID, int fromDepotID, int toDepotID) {
        System.out.println("Requesting transfer for item ID: " + itemID + " from depot " + fromDepotID + " to depot " + toDepotID);
        // Simulate transfer logic
        String sql = "UPDATE inventory SET depotID = ? WHERE itemID = ? AND depotID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, toDepotID);
            pstmt.setInt(2, itemID);
            pstmt.setInt(3, fromDepotID);
            pstmt.executeUpdate();
            System.out.println("Transfer request for item ID: " + itemID + " submitted.");
        } catch (Exception e) {
            System.out.println("Error requesting transfer: " + e.getMessage());
        }
    }

    // Request Equipment Purchase
    public static void requestPurchase(String itemName, int quantity, int itemPrice) {
        System.out.println("Requesting purchase for item: " + itemName + ", Quantity: " + quantity + ", Price per item: $" + itemPrice);
        // Simulate purchase logic
        String sql = "INSERT INTO inventory (itemName, itemQuantity, itemPrice, itemAvailable) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, itemName);
            pstmt.setInt(2, quantity);
            pstmt.setInt(3, itemPrice);
            pstmt.setBoolean(4, true);
            pstmt.executeUpdate();
            System.out.println("Purchase request for " + quantity + " units of " + itemName + " submitted.");
        } catch (Exception e) {
            System.out.println("Error requesting purchase: " + e.getMessage());
        }
    }

    // Report Lost
    public static void reportLost(int itemID, int depotID) {
        System.out.println("Reporting lost item ID: " + itemID + " at depot ID: " + depotID);
        // Simulate lost item logic
        String sql = "UPDATE inventory SET itemQuantity = itemQuantity - 1 WHERE itemID = ? AND depotID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, itemID);
            pstmt.setInt(2, depotID);
            pstmt.executeUpdate();
            System.out.println("Lost report for item ID: " + itemID + " submitted.");
        } catch (Exception e) {
            System.out.println("Error reporting lost item: " + e.getMessage());
        }
    }

    // Report Damaged
    public static void reportDamaged(int itemID, int depotID) {
        System.out.println("Reporting damaged item ID: " + itemID + " at depot ID: " + depotID);
        // Simulate damaged item logic
        String sql = "UPDATE inventory SET itemAvailable = false WHERE itemID = ? AND depotID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, itemID);
            pstmt.setInt(2, depotID);
            pstmt.executeUpdate();
            System.out.println("Damaged report for item ID: " + itemID + " submitted.");
        } catch (Exception e) {
            System.out.println("Error reporting damaged item: " + e.getMessage());
        }
    }

    // Report Found
    public static void reportFound(int itemID, int depotID) {
        System.out.println("Reporting found item ID: " + itemID + " at depot ID: " + depotID);
        // Simulate found item logic
        String sql = "UPDATE inventory SET itemQuantity = itemQuantity + 1 WHERE itemID = ? AND depotID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, itemID);
            pstmt.setInt(2, depotID);
            pstmt.executeUpdate();
            System.out.println("Found report for item ID: " + itemID + " submitted.");
        } catch (Exception e) {
            System.out.println("Error reporting found item: " + e.getMessage());
        }
    }

    // Retrieve Depot Employee from Database
    public static DepotEmp getDepotEmpFromDB(int empID) {
        String sql = "SELECT * FROM depot_employees WHERE empID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, empID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    int overrideRequest = rs.getInt("overrideRequest");
                    System.out.println("Depot employee retrieved from database successfully.");
                    return new DepotEmp(empID, name, overrideRequest);
                }
            }
        } catch (Exception e) {
            System.out.println("Error retrieving depot employee from database: " + e.getMessage());
        }
        return null;
    }
}
