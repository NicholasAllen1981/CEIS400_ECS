/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ECS.Main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class ViewEmployeeUI extends javax.swing.JFrame {

    // Database credentials
    String url = "jdbc:mysql://localhost:3306/ceis400_group_project";
    String username = "user";
    String password = "devry123";

    // Declare jTable1 as an instance variable
    private javax.swing.JTable jTable1;

    /**
     * Creates new form ViewEmployeeUI
     */
    public ViewEmployeeUI() {
        initComponents();
        initMyComponents();
        // Populate the existing JTable jTable1
        populateTable();
    }

    private void populateTable() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

        // Define custom column names
        String[] columnNames = {"Employee ID", "Name", "Address", "City", "State", "Zip", "Phone", "Email", "Skills", "Termination Status", "Depot Employee ID"};
        model.setColumnIdentifiers(columnNames);

        // Connect to database and fetch data
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT empID, CONCAT(FirstName, ' ', LastName) as Name, empAddress, empCity, empState, empZip, empPhone, empEmail, empSkills, TerminationStatus, DepotEmpID FROM employee";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {

                // Clear existing rows
                model.setRowCount(0);

                // Add rows to table model
                while (resultSet.next()) {
                    Vector<Object> row = new Vector<>();
                    row.add(resultSet.getInt("empID"));
                    row.add(resultSet.getString("Name"));
                    row.add(resultSet.getString("empAddress"));
                    row.add(resultSet.getString("empCity"));
                    row.add(resultSet.getString("empState"));
                    row.add(resultSet.getString("empZip"));
                    row.add(resultSet.getString("empPhone"));
                    row.add(resultSet.getString("empEmail"));
                    row.add(resultSet.getString("empSkills"));
                    row.add(resultSet.getString("TerminationStatus"));
                    row.add(resultSet.getInt("DepotEmpID"));
                    model.addRow(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initMyComponents() {
        jTable1 = new javax.swing.JTable(new DefaultTableModel()); // Initialize jTable1 with a default model
        JScrollPane scrollPane = new JScrollPane(jTable1);

       // Set JTable to auto resize
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        
        // Ensure JTable auto-sizes in both directions
        jTable1.setPreferredScrollableViewportSize(new Dimension(880, 470));
        jTable1.setFillsViewportHeight(true);

        // Create buttons
        JButton okayButton = new JButton("Okay");
        JButton cancelButton = new JButton("Cancel");

        // Add action listeners to the buttons
        okayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle Okay button action
                System.out.println("Okay button clicked");
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle Cancel button action
                System.out.println("Cancel button clicked");
            }
        });

        // Create a panel to hold the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okayButton);
        buttonPanel.add(cancelButton);

        // Add components to the frame
        getContentPane().add(scrollPane, "Center");
        getContentPane().add(buttonPanel, "South");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        // Set layout
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 883, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(buttonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 883, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 527, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewEmployeeUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewEmployeeUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewEmployeeUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewEmployeeUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewEmployeeUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
