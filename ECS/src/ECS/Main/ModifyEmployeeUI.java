/**
 *
 * @author Nicholas Allen, Daniel F Diaz Santiago
 */
package ECS.Main;

import static ECS.Main.AddEmployeeUI.populate_states_hashmap;
import static ECS.Main.AddEmployeeUI.states_map;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Pattern;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class ModifyEmployeeUI extends javax.swing.JFrame {
    /**
     * Creates new form ModifyEmployeeUI
     */
    public ModifyEmployeeUI() {
        initComponents();
        populate_states_hashmap();
        notes_box.setLineWrap(true);
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        okay_btn = new javax.swing.JButton();
        javax.swing.JLabel jLabel8 = new javax.swing.JLabel();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        skillset_list = new javax.swing.JList<>();
        javax.swing.JLabel jLabel11 = new javax.swing.JLabel();
        javax.swing.JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
        notes_box = new javax.swing.JTextArea();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        address_box1 = new javax.swing.JTextField();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel10 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        city_box = new javax.swing.JTextField();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        zip_box = new javax.swing.JTextField();
        javax.swing.JButton cancel_btn = new javax.swing.JButton();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel9 = new javax.swing.JLabel();
        state_dropdown = new javax.swing.JComboBox<>();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        first_name_box = new javax.swing.JTextField();
        last_name_box = new javax.swing.JTextField();
        address_box2 = new javax.swing.JTextField();
        email_box = new javax.swing.JTextField();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        phone_box = new javax.swing.JTextField();
        employee_id_box = new javax.swing.JTextField();
        javax.swing.JLabel jLabel12 = new javax.swing.JLabel();
        javax.swing.JButton get_btn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        okay_btn.setText("Okay");
        okay_btn.setEnabled(false);
        okay_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okay_btnActionPerformed(evt);
            }
        });

        jLabel8.setText("Skillset:");

        skillset_list.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        skillset_list.setEnabled(false);
        jScrollPane1.setViewportView(skillset_list);

        jLabel11.setText("Notes:");

        notes_box.setColumns(20);
        notes_box.setRows(5);
        notes_box.setEnabled(false);
        jScrollPane2.setViewportView(notes_box);

        jLabel2.setText("First Name:");

        address_box1.setEnabled(false);

        jLabel3.setText("Address:");

        jLabel10.setText("Email:");

        jLabel4.setText("Address(Cont.):");

        city_box.setEnabled(false);

        jLabel5.setText("City:");

        zip_box.setEnabled(false);
        zip_box.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                zip_boxKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                zip_boxKeyTyped(evt);
            }
        });

        cancel_btn.setText("Cancel");
        cancel_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancel_btnActionPerformed(evt);
            }
        });

        jLabel7.setText("Phone #:");

        jLabel9.setText("State:");

        state_dropdown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming" }));
        state_dropdown.setEnabled(false);

        jLabel6.setText("Zip:");

        first_name_box.setEnabled(false);

        last_name_box.setEnabled(false);

        address_box2.setEnabled(false);

        email_box.setEnabled(false);

        jLabel1.setText("Last Name:");

        phone_box.setEnabled(false);
        phone_box.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                phone_boxKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                phone_boxKeyTyped(evt);
            }
        });

        jLabel12.setText("Employee ID:");

        get_btn.setActionCommand("Get");
        get_btn.setLabel("Get");
        get_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                get_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(phone_box, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addGap(8, 8, 8)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                                    .addComponent(email_box, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(okay_btn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cancel_btn))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(address_box2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
                            .addComponent(address_box1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(city_box, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(state_dropdown, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(zip_box, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(employee_id_box, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(first_name_box, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(last_name_box, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(get_btn)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(employee_id_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(get_btn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(first_name_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(last_name_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(address_box1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(address_box2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(zip_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(state_dropdown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(city_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(email_box, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(phone_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(jLabel10)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel11)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancel_btn)
                    .addComponent(okay_btn))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancel_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancel_btnActionPerformed
        setVisible(false);
        dispose();
    }//GEN-LAST:event_cancel_btnActionPerformed

    private void get_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_get_btnActionPerformed
        final String DB_URL = "jdbc:mysql://localhost:3306/CEIS400_group_project";
        final String DB_USER = "groupc";
        final String DB_PASSWORD = "oI209[^X`XHF";

        String empID = employee_id_box.getText();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Establish connection
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String sql = "SELECT FirstName, LastName, empAddress, empCity, empState, empZip, empPhone, "
                    + "empEmail, empSkills FROM Employee WHERE empID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(empID));

            // Execute query
            rs = pstmt.executeQuery();

            // Process results
            if (rs.next()) {
                String first_name = rs.getString("FirstName");
                String last_name = rs.getString("LastName");
                String emp_address = rs.getString("empAddress");
                String emp_city = rs.getString("empCity");
                String emp_state = rs.getString("empState");
                String emp_zip = rs.getString("empZip");
                String emp_phone = rs.getString("empPhone");
                String emp_email = rs.getString("empEmail");
                String emp_skills = rs.getString("empSkills");

                enable_input();
                first_name_box.setText(first_name);
                last_name_box.setText(last_name);
                address_box1.setText(emp_address);
                city_box.setText(emp_city);
                state_dropdown.setSelectedItem(emp_state);
                zip_box.setText(emp_zip);
                phone_box.setText(emp_phone);
                email_box.setText(emp_email);
                DefaultListModel<String> listModel = new DefaultListModel<>();
                for (String skill : emp_skills.split(",")) {
                    listModel.addElement(skill.trim());
                }
                skillset_list.setModel(listModel);
            } else {
                JOptionPane.showMessageDialog(this, "Employee ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching data.", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_get_btnActionPerformed

    private void okay_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okay_btnActionPerformed
        // Okay button function
        String state = states_map.get(state_dropdown.getSelectedItem().toString());

        if (validate_inputs()) {
            Employee newEmp = new Employee(first_name_box.getText(), last_name_box.getText(),
                    address_box1.getText() + " " + address_box2.getText(), city_box.getText(),
                    state, zip_box.getText(), Long.parseLong(phone_box.getText()), email_box.getText(),
                    skillset_list.getSelectedValue(), "Good");
            Employee.addEmp(newEmp);
        }

        /*
        Employee(String _empFirstName, String _empLastName, String _empAddress, 
            String _empCity, String _empState, String _empZip, Long _empPhone, 
            String _empEmail, String _empSkills, String _empStatus)
         */
    }//GEN-LAST:event_okay_btnActionPerformed

    private void zip_boxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_zip_boxKeyPressed
        if (zip_box.getText().length() >= 5) {
            // Allow backspace and delete
            if (evt.getKeyCode() == KeyEvent.VK_BACK_SPACE || evt.getKeyCode() == KeyEvent.VK_DELETE) {
                zip_box.setEditable(true);
            } else {
                zip_box.setEditable(false);
            }
        } else {
            zip_box.setEditable(true);
        }
    }//GEN-LAST:event_zip_boxKeyPressed

    private void phone_boxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phone_boxKeyPressed
        // Limit input to 10
        if (phone_box.getText().length() >= 10) {
            // Allow backspace and delete
            if (evt.getKeyCode() == KeyEvent.VK_BACK_SPACE || evt.getKeyCode() == KeyEvent.VK_DELETE) {
                phone_box.setEditable(true);
            } else {
                phone_box.setEditable(false);
            }
        } else {
            phone_box.setEditable(true);
        }
    }//GEN-LAST:event_phone_boxKeyPressed

    private void phone_boxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phone_boxKeyTyped
        // Allow only numbers
        char keys = evt.getKeyChar();

        if (!Character.isDigit(keys)) {
            evt.consume();
        }
    }//GEN-LAST:event_phone_boxKeyTyped

    private void zip_boxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_zip_boxKeyTyped
        // Allow only numbers
        char keys = evt.getKeyChar();

        if (!Character.isDigit(keys)) {
            evt.consume();
        }
    }//GEN-LAST:event_zip_boxKeyTyped

    void enable_input() {
        first_name_box.setEnabled(true);
        last_name_box.setEnabled(true);
        address_box1.setEnabled(true);
        address_box2.setEnabled(true);
        city_box.setEnabled(true);
        state_dropdown.setEnabled(true);
        zip_box.setEnabled(true);
        phone_box.setEnabled(true);
        email_box.setEnabled(true);
        skillset_list.setEnabled(true);
        notes_box.setEnabled(true);
        okay_btn.setEnabled(true);
    }

    private boolean validate_inputs() {
        // Validate first name
        if (first_name_box.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "First name is required", "Input Error", JOptionPane.ERROR_MESSAGE);
            first_name_box.requestFocus();
            return false;
        }

        // Validate last name
        if (last_name_box.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Last name is required", "Input Error", JOptionPane.ERROR_MESSAGE);
            last_name_box.requestFocus();
            return false;
        }

        // Validate address
        if (address_box1.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Address is required", "Input Error", JOptionPane.ERROR_MESSAGE);
            address_box1.requestFocus();
            return false;
        }

        // Validate city
        if (city_box.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "City is required", "Input Error", JOptionPane.ERROR_MESSAGE);
            city_box.requestFocus();
            return false;
        }

        // Validate state
        String state = state_dropdown.getSelectedItem().toString();
        if (state.equals("Select")) {
            JOptionPane.showMessageDialog(this, "State is required", "Input Error", JOptionPane.ERROR_MESSAGE);
            state_dropdown.requestFocus();
            return false;
        }

        // Validate ZIP code
        if (zip_box.getText().trim().isEmpty() || zip_box.getText().length() != 5) {
            JOptionPane.showMessageDialog(this, "Valid 5-digit ZIP code is required", "Input Error", JOptionPane.ERROR_MESSAGE);
            zip_box.requestFocus();
            return false;
        }

        // Validate phone number
        String phone = phone_box.getText().trim();
        if (phone.isEmpty() || !phone.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(this, "Valid 10-digit phone number is required", "Input Error", JOptionPane.ERROR_MESSAGE);
            phone_box.requestFocus();
            return false;
        }

        // Validate email
        String email = email_box.getText().trim();
        if (email.isEmpty() || !is_valid_email(email)) {
            JOptionPane.showMessageDialog(this, "Valid email is required", "Input Error", JOptionPane.ERROR_MESSAGE);
            email_box.requestFocus();
            return false;
        }

        // Validate skillset
        if (skillset_list.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(this, "A skillset selection is required", "Input Error", JOptionPane.ERROR_MESSAGE);
            skillset_list.requestFocus();
            return false;
        }

        return true;
    }

    private boolean is_valid_email(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
    }

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
            java.util.logging.Logger.getLogger(ModifyEmployeeUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModifyEmployeeUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModifyEmployeeUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModifyEmployeeUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModifyEmployeeUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JTextField address_box1;
    javax.swing.JTextField address_box2;
    javax.swing.JTextField city_box;
    javax.swing.JTextField email_box;
    javax.swing.JTextField employee_id_box;
    javax.swing.JTextField first_name_box;
    javax.swing.JTextField last_name_box;
    javax.swing.JTextArea notes_box;
    javax.swing.JButton okay_btn;
    javax.swing.JTextField phone_box;
    javax.swing.JList<String> skillset_list;
    javax.swing.JComboBox<String> state_dropdown;
    javax.swing.JTextField zip_box;
    // End of variables declaration//GEN-END:variables
}
