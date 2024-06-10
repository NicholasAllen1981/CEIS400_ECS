/**
 *
 * @author Nicholas Allen, Daniel F Diaz Santiago
 */
package ECS.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class MaintEmp extends Employee {

    // --- Variables ---
    public int maintID;
    static public boolean warningGiven;
    private String lastLostDate;
    private String lastDamagedDate;

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
    public static void setSkillset(int maintID, String empSkills) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Check if the maintID exists
            String checkSql = "SELECT maintID FROM MaintEmp WHERE maintID = ?";
            preparedStatement = connection.prepareStatement(checkSql);
            preparedStatement.setInt(1, maintID);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                // maintID not found, display a warning dialog
                System.out.println("Maintenance Employee ID not found: " + maintID);
                JOptionPane.showMessageDialog(null, "Maintenance Employee ID not found: " + maintID, "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                // maintID found, update the skills
                String updateSql = "UPDATE MaintEmp SET empSkills = ? WHERE maintID = ?";
                preparedStatement = connection.prepareStatement(updateSql);
                preparedStatement.setString(1, empSkills);
                preparedStatement.setInt(2, maintID);

                int rowsUpdated = preparedStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Maintenance employee skills updated successfully.");
                } else {
                    System.out.println("Error updating maintenance employee skills.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error accessing the database.");
            e.printStackTrace();
        } finally {
            // Close the resources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            // Close the connection
            closeConnection();
        }
    }

    // Set checked out items
    // Get all checked out items?
    // use lastLostDate & lastDamagedDate if the item is lost to send a warning?
    public static void setOutItems(int maintID) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Get the status of items for the specified maintenance employee (maintID)
            String sql = "SELECT lastLostDate, lastDamagedDate FROM MaintEmp WHERE maintID = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, maintID);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String lastLostDate = resultSet.getString("lastLostDate");
                String lastDamagedDate = resultSet.getString("lastDamagedDate");

                // Check if the item is lost or damaged
                if (lastLostDate != null || lastDamagedDate != null) {
                    // Send a warning if not already given
                    if (!warningGiven) {
                        System.out.println("Warning: Maintenance employee with ID " + maintID + " has lost or damaged items.");
                        JOptionPane.showMessageDialog(null, "Warning: Maintenance employee with ID " + maintID
                                + " has lost or damaged items.", "Warning", JOptionPane.WARNING_MESSAGE);
                        warningGiven = true;

                        // Update warningGiven status in the database
                        String updateSql = "UPDATE MaintEmp SET warningGiven = 1 WHERE maintID = ?";
                        PreparedStatement updateStatement = connection.prepareStatement(updateSql);
                        updateStatement.setInt(1, maintID);
                        updateStatement.executeUpdate();
                        updateStatement.close();
                    }
                } else {
                    System.out.println("Maintenance employee with ID " + maintID + " has no lost or damaged items.");
                }
            } else {
                System.out.println("Maintenance employee ID not found: " + maintID);
            }

        } catch (SQLException e) {
            System.out.println("Error accessing the database.");
            e.printStackTrace();
        } finally {
            // Close the resources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeConnection();
        }
    }

    // Get count of damaged equipment
    public static int getEquipDamagedCount(int maintID) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int damagedCount = 0;

        try {
            // Check if the maintID exists
            String checkSql = "SELECT maintID FROM MaintEmp WHERE maintID = ?";
            preparedStatement = connection.prepareStatement(checkSql);
            preparedStatement.setInt(1, maintID);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                // If maintID doesn't exist, return 0
                System.out.println("Maintenance Employee ID not found: " + maintID);
                JOptionPane.showMessageDialog(null, "Warning: Maintenance ID not found: "
                        + maintID, "Warning", JOptionPane.WARNING_MESSAGE);
                return 0;
            }

            // Get the count of damaged equipment for specified maintID
            String sql = "SELECT equipDamagedCount FROM MaintEmp WHERE maintID = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, maintID);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                damagedCount = resultSet.getInt("equipDamagedCount");
            }

        } catch (SQLException e) {
            System.out.println("Error accessing the database.");
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeConnection();
        }
        return damagedCount;
    }

    // Get count of lost equipment
    public static int getEquipLostCount(int maintID) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int lostCount = 0;

        try {
            // Check if the maintID exists
            String checkSql = "SELECT maintID FROM MaintEmp WHERE maintID = ?";
            preparedStatement = connection.prepareStatement(checkSql);
            preparedStatement.setInt(1, maintID);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                // If maintID doesn't exist, return 0
                System.out.println("Maintenance Employee ID not found: " + maintID);
                JOptionPane.showMessageDialog(null, "Warning: Maintenance ID not found: "
                        + maintID, "Warning", JOptionPane.WARNING_MESSAGE);
                return 0;
            }

            // Get the count of lost equipment for specified maintID
            String sql = "SELECT equipLostCount FROM MaintEmp WHERE maintID = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, maintID);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                lostCount = resultSet.getInt("equipLostCount");
            }

        } catch (SQLException e) {
            System.out.println("Error accessing the database.");
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            closeConnection();
        }
        return lostCount;
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
