/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ECS.Main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;
import java.util.Date;


/**
 *
 * @author Nicholas Allen
 * @author Vaanitya Sangwan 06/04/20224
 */
public class Equipment {
    
    // --- Database Connection Variables ---
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ceis400_group_project";
    private static final String DB_USER = "username";
    private static final String DB_PASSWORD = "password";
    private static Connection connection = null;
    
    // --- Variables ---
    public int depotID;
    public int itemID;
    public String itemName;
    public int itemQuantity;
    public boolean itemAvailable;
    public int itemPrice;
    public int numOut;
    public boolean isConsumable;
    public String skillRequired;
    
    // Constructor
    public Equipment(int itemID, String itemName, int itemPrice, boolean isConsumable, int itemQuantity, int depotID, String skillRequired, int numOut, boolean itemAvailable ) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.numOut = numOut;
        this.isConsumable = isConsumable;
        this.itemQuantity = itemQuantity;
        this.depotID = depotID;
        this.skillRequired = skillRequired;
        this.itemAvailable = itemQuantity > 0;
        
        // Initialize database connection when the first instance is created
        connectToDatabase();
    }
    
    // Initialize database connection
    private static Connection connectToDatabase() {
    try {
        // Explicitly load the MySQL JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        if (connection == null) {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Database connection successfully established.");
        }
    } catch (ClassNotFoundException e) {
        System.err.println("JDBC Driver not found: " + e.getMessage());
    } catch (SQLException e) {
        System.err.println("Database connection failed: " + e.getMessage());
        System.err.println("SQL State: " + e.getSQLState());
        System.err.println("Error Code: " + e.getErrorCode());
    }
    return connection;
    }
    
    // --- Functions ---
    
    
    
    // Check Out Equipment
    public static void checkOut(int itemID, int empID, String itemName, int itemPrice, boolean isConsumable, int itemQuantity, int depotID, String skillRequired, Date checkoutDate, Date returnDate) {
        if (connection == null) {
            // Assume a method getConnection() that handles the connection setup
            connection = connectToDatabase();
        }

        try {
            // Begin transaction
            connection.setAutoCommit(false);

            // SQL to update the equipment table
            String updateSql = "UPDATE equipment SET itemQuantity = itemQuantity - 1, numOut = numOut + 1, itemAvailable = (itemQuantity > 0) WHERE itemID = ?";
            try (PreparedStatement updateStmt = connection.prepareStatement(updateSql)) {
                updateStmt.setInt(1, itemID);
                int count = updateStmt.executeUpdate();
                if (count == 0) {
                    System.out.println("Item not available!");
                    connection.rollback();
                    return;
                }
            }

            // SQL to insert into checkout table
            String insertSql = "INSERT INTO Checkout (TransactionID, empID, EquipmentID, CheckoutDate, ReturnDate) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement insertStmt = connection.prepareStatement(insertSql)) {
                // Assume a method to generate a unique transaction ID
                String transactionID = generateTransactionID();

                insertStmt.setString(1, transactionID);
                insertStmt.setInt(2, empID);
                insertStmt.setInt(3, itemID);
                insertStmt.setDate(4, new java.sql.Date(checkoutDate.getTime()));
                insertStmt.setDate(5, new java.sql.Date(returnDate.getTime()));

                insertStmt.executeUpdate();
            }

            // Commit transaction
            connection.commit();
            System.out.println("Item checked out successfully: " + itemName);
        } catch (SQLException e) {
            System.out.println("Error processing checkout: " + e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println("Error rolling back: " + ex.getMessage());
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Error resetting auto-commit: " + e.getMessage());
            }
        }
    }

    
    // Check In Equipment
    public static void checkIn(int itemID, int depotID) {
        if (connection == null) {
            // Assume a method getConnection() that handles the connection setup
            connection = connectToDatabase();
        }

        try {
            // Begin transaction
            connection.setAutoCommit(false);

            // SQL to update the equipment table
            String updateSql = "UPDATE equipment SET itemQuantity = itemQuantity + 1, numOut = numOut - 1 WHERE itemID = ?";
            try (PreparedStatement updateStmt = connection.prepareStatement(updateSql)) {
                updateStmt.setInt(1, itemID);
                int count = updateStmt.executeUpdate();
                if (count == 0) {
                    System.out.println("Item not found in the system.");
                    connection.rollback();
                    return;
                }
            }

            // Optionally update the checkout table if necessary
            String checkoutUpdateSql = "UPDATE Checkout SET ReturnDate = NOW() WHERE EquipmentID = ? AND ReturnDate IS NULL";
            try (PreparedStatement checkoutUpdateStmt = connection.prepareStatement(checkoutUpdateSql)) {
                checkoutUpdateStmt.setInt(1, itemID);
                checkoutUpdateStmt.executeUpdate();
            }

            // Commit transaction
            connection.commit();
            System.out.println("Item checked in successfully.");
        } catch (SQLException e) {
            System.out.println("Error processing check-in: " + e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println("Error rolling back: " + ex.getMessage());
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Error resetting auto-commit: " + e.getMessage());
            }
        }
    }

   
    /*
    // Add to Queue
    public static void addQueue(Equipment item){
        // Add an item to the checkout queue
        checkoutQueue.add(item);
        System.out.println("Item added to queue: " + item.itemName);
    }
    
    // Remove from Queue
    public static void removeQueue(int itemID){
        // Remove an item from the checkout queue
        checkoutQueue.removeIf(item -> item.itemID == itemID);
        System.out.println("Item removed from queue: Item ID " + itemID);
    }
    
    // Display Queue from database
    public static void displayQueue(){
        // Display all items in the queue
        System.out.println("Current checkout queue:");
        for (Equipment item : checkoutQueue) {
            System.out.println("Item ID: " + item.itemID + ", Name: " + item.itemName + ", Available: " + item.itemAvailable);
        }
    }
*/
    
    public static void addEquipment(int itemID, String itemName, int itemPrice, boolean isConsumable, int itemQuantity, int depotID, String skillRequired) {
    connectToDatabase(); // Ensure connection is established
    String sql = "INSERT INTO equipment (itemID, depotID, itemName, itemQuantity, itemPrice, isConsumable, skillRequired) VALUES (?, ?, ?, ?, ?, ?, ?)";
    
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setInt(1, itemID);
        stmt.setInt(2, depotID);
        stmt.setString(3, itemName);
        stmt.setInt(4, itemQuantity);
        stmt.setInt(5, itemPrice);
        stmt.setBoolean(6, isConsumable);
        stmt.setString(7, skillRequired);

        int rowsAffected = stmt.executeUpdate();
        System.out.println(rowsAffected + " rows inserted.");
    } catch (SQLException e) {
        System.err.println("SQL Error: " + e.getMessage());
        System.err.println("SQL State: " + e.getSQLState());
        System.err.println("Error Code: " + e.getErrorCode());
        e.printStackTrace();
    } catch (Exception e) {
        System.err.println("Non-SQL Error: " + e.getMessage());
        e.printStackTrace();
    }
    
    }
    
    // View Inventory (display records from database)
    public static String viewInv() {
        StringBuilder inventoryText = new StringBuilder();
        inventoryText.append("Item ID\tItem Name\tItem Quantity\tSkill Required\n"); // Header for the text area

        System.out.println("Displaying all available equipment...");
        if (connection == null) {
        connectToDatabase();
        }
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT itemID, itemName, itemQuantity, skillRequired FROM equipment")) {
            
            while (rs.next()) {
                int itemID = rs.getInt("itemID");
                String itemName = rs.getString("itemName");
                int itemQuantity = rs.getInt("itemQuantity");
                String skillRequired = rs.getString("skillRequired");

                // Format each row as a line in the text area
                inventoryText.append(String.format("%d\t%s\t%d\t%s\n", itemID, itemName, itemQuantity, skillRequired));
            }
        } 
        catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            e.printStackTrace();
            return "Failed to load inventory. Please check the console for more information.";
        } 
        catch (Exception e) {
            System.err.println("General Error: " + e.getMessage());
            e.printStackTrace();
            return "An unexpected error occurred. Please check the console for more information.";
        }
        return inventoryText.toString();
    }
    
    private static String generateTransactionID() {
    UUID uuid = UUID.randomUUID();
    return uuid.toString();  // Converts UUID to string for easier storage and handling
    }

}
