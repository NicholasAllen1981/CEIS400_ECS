/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ECS.Main;

import java.util.prefs.Preferences;

/**
 *
 * @author nicho
 */
public class Settings {
    // Default values
    private static final String DEFAULT_DB_ADDRESS = "localhost";
    private static final String DEFAULT_DB_NAME = "ceis400_group_project";
    private static final String DEFAULT_DB_PORT = "3306";
    private static final String DEFAULT_DB_USERNAME = "user";
    private static final String DEFAULT_DB_PASSWORD = "devry123";

    // Preferences node name
    private static final String PREFS_NODE_NAME = "/com/ECS/dbsettings";

    // Method to store settings
    public static void storeDatabaseSettings(String dbAddress, String dbName, String dbPort, String dbUsername, String dbPassword) {
        Preferences prefs = Preferences.userRoot().node(PREFS_NODE_NAME);

        prefs.put("dbAddress", dbAddress);
        prefs.put("dbName", dbName);
        prefs.put("dbPort", dbPort);
        prefs.put("dbUsername", dbUsername);
        prefs.put("dbPassword", dbPassword);
    }

    // Method to retrieve settings
    public static String getDbAddress() {
        Preferences prefs = Preferences.userRoot().node(PREFS_NODE_NAME);
        return prefs.get("dbAddress", DEFAULT_DB_ADDRESS);
    }

    public static String getDbName() {
        Preferences prefs = Preferences.userRoot().node(PREFS_NODE_NAME);
        return prefs.get("dbName", DEFAULT_DB_NAME);
    }

    public static String getDbPort() {
        Preferences prefs = Preferences.userRoot().node(PREFS_NODE_NAME);
        return prefs.get("dbPort", DEFAULT_DB_PORT);
    }

    public static String getDbUsername() {
        Preferences prefs = Preferences.userRoot().node(PREFS_NODE_NAME);
        return prefs.get("dbUsername", DEFAULT_DB_USERNAME);
    }

    public static String getDbPassword() {
        Preferences prefs = Preferences.userRoot().node(PREFS_NODE_NAME);
        return prefs.get("dbPassword", DEFAULT_DB_PASSWORD);
    }

    // Method to get full JDBC URL
    public static String getJdbcUrl() {
        return "jdbc:mysql://" + getDbAddress() + ":" + getDbPort() + "/" + getDbName();
    }
}
    
    
    

