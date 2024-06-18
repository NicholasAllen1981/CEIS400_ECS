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
    private int itemID;
    private String itemName;
    public int itemQuantity;
    private boolean itemAvailable;
    private int itemPrice;
    private int numOut;
    private boolean isConsumable;
    
    // Constructor
    public Equipment(int itemID, String itemName, int itemPrice, boolean isConsumable, int itemQuantity) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.isConsumable = isConsumable;
        this.itemQuantity = itemQuantity;
        this.itemAvailable = itemQuantity > 0;
        
        // Initialize database connection when the first instance is created
        connectToDatabase();
    }
    
    // Initialize database connection
    private static void connectToDatabase() {
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                System.out.println("Database connection successfully established.");
            }
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }
    }
    
    // --- Functions ---
    
    // View Inventory (display records from database)
    private static void viewInv(){
        System.out.println("Displaying all available equipment...");
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT itemID, itemName, itemQuantity, itemAvailable FROM equipment")) {
            
            while (rs.next()) {
                int itemID = rs.getInt("itemID");
                String itemName = rs.getString("itemName");
                int itemQuantity = rs.getInt("itemQuantity");
                boolean itemAvailable = rs.getBoolean("itemAvailable");

                System.out.println("Item ID: " + itemID + ", Name: " + itemName + ", Quantity: " + itemQuantity + ", Available: " + itemAvailable);
            }
        } catch (Exception e) {
            System.out.println("Error accessing the database: " + e.getMessage());
        }
    }
    
    // Check Out Equipment
    private static void checkOut(int itemID, int depotID){
        // Simulate checking if the employee is authorized and the item is available
         System.out.println("Checking out item ID: " + itemID + " for employee ID: " + depotID);
        for (Equipment item : checkoutQueue) {
            if (item.itemID == itemID && item.itemAvailable && item.itemQuantity > 0) {
                item.itemQuantity--; // Decrement the quantity
                item.numOut++; // Increment the count of items checked out
                item.itemAvailable = item.itemQuantity > 0; // Update availability
                System.out.println("Item checked out successfully: " + item.itemName);
                return;
            }
        }
        System.out.println("Item not available or not in queue.");
    }
    
    // Check In Equipment
    private static void checkIn(int itemID, int depotID){
        // Simulate checking in an item
         System.out.println("Checking in item ID: " + itemID + " by employee ID: " + depotID);
        for (Equipment item : checkoutQueue) {
            if (item.itemID == itemID) {
                item.itemQuantity++; // Increment the quantity
                item.numOut--; // Decrement the count of items checked out
                item.itemAvailable = true; // Mark as available
                System.out.println("Item checked in successfully: " + item.itemName);
                return;
            }
        }
        System.out.println("Item not found in the system.");
    }
    
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
    
    public static void addEquipment(int itemID, String itemName, int itemPrice, boolean isConsumable, int itemQuantity) {
    connectToDatabase(); // Ensure connection is established
    String sql = "INSERT INTO equipment (itemID, itemName, itemPrice, isConsumable, itemQuantity, itemAvailable) VALUES (?, ?, ?, ?, ?, ?)";
    
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setInt(1, itemID);
        stmt.setString(2, itemName);
        stmt.setInt(3, itemPrice);
        stmt.setBoolean(4, isConsumable);
        stmt.setInt(5, itemQuantity);
        stmt.setBoolean(6, itemQuantity > 0); // Set itemAvailable based on itemQuantity

        int rowsAffected = stmt.executeUpdate();
        System.out.println(rowsAffected + " rows inserted.");
    } catch (SQLException e) {
        System.out.println("Error adding equipment: " + e.getMessage());
    }
}

}
