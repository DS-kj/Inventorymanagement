/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package InventoryManagementSystem.view;

import InventoryManagementSystem.model.AdminPanelModel;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ACER
 */
public class AdminPanel extends javax.swing.JFrame {

    /**
     * Creates new form AdminPanel
     */
    public AdminPanel() {
        initComponents();
        PasswordAdminPanelEntry.setEchoChar((char) 0);
    }
//public class GradientBackgroundPanel extends javax.swing.JPanel {
//    private final Color colorStart;
//    private final Color colorEnd;
//    private final boolean diagonal;
//
//    /**
//     * Creates a panel with a gradient background.
//     * @param colorStart The start color of the gradient.
//     * @param colorEnd The end color of the gradient.
//     * @param diagonal True for diagonal gradient, false for vertical.
//     */
//    public GradientBackgroundPanel(Color colorStart, Color colorEnd, boolean diagonal) {
//        this.colorStart = colorStart;
//        this.colorEnd = colorEnd;
//        this.diagonal = diagonal;
//        setOpaque(false); // Important for custom painting
//    }
//
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        Graphics2D g2d = (Graphics2D) g.create();
//
//        GradientPaint gp;
//        if (diagonal) {
//            gp = new GradientPaint(0, 0, colorStart, getWidth(), getHeight(), colorEnd);
//        } else {
//            gp = new GradientPaint(0, 0, colorStart, 0, getHeight(), colorEnd);
//        }
//
//        g2d.setPaint(gp);
//        g2d.fillRect(0, 0, getWidth(), getHeight());
//
//        g2d.dispose();
//    }
//}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new GradientBackgroundPanel(
            //    new java.awt.Color(50, 120, 165),  // Brighter Left Side
            //    new java.awt.Color(9, 47, 70),     // Darker Right Side
            //    true
            new java.awt.Color(9, 47, 70),  // start color
            new java.awt.Color(32, 95, 178), // end color
            true
        );
        jPanel3 = new InventoryManagementSystem.view.GradientBackgroundPanel(
            new Color(9, 47, 70),  // start color
            new Color(32, 95, 178), // end color
            true  // diagonal gradient, use false for vertical
        );
        PhoneNumberEntry = new javax.swing.JTextField();
        PasswordAdminPanelEntry = new javax.swing.JPasswordField();
        UsernameAdminPanelEntry = new javax.swing.JTextField();
        CreateAccount = new javax.swing.JButton();
        showandhide = new javax.swing.JCheckBox();
        Delete = new javax.swing.JButton();
        EditButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(29, 86, 121));

        jPanel3.setBackground(new java.awt.Color(23, 69, 97));
        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(0, 153, 153), null, null));

        PhoneNumberEntry.setForeground(new java.awt.Color(204, 204, 204));
        PhoneNumberEntry.setText("Phone no.");
        PhoneNumberEntry.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                PhoneNumberEntryFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                PhoneNumberEntryFocusLost(evt);
            }
        });

        PasswordAdminPanelEntry.setForeground(new java.awt.Color(204, 204, 204));
        PasswordAdminPanelEntry.setText("Password");
        PasswordAdminPanelEntry.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                PasswordAdminPanelEntryFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                PasswordAdminPanelEntryFocusLost(evt);
            }
        });
        PasswordAdminPanelEntry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PasswordAdminPanelEntryActionPerformed(evt);
            }
        });

        UsernameAdminPanelEntry.setForeground(new java.awt.Color(204, 204, 204));
        UsernameAdminPanelEntry.setText("Enter username");
        UsernameAdminPanelEntry.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                UsernameAdminPanelEntryFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                UsernameAdminPanelEntryFocusLost(evt);
            }
        });
        UsernameAdminPanelEntry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsernameAdminPanelEntryActionPerformed(evt);
            }
        });

        CreateAccount.setText("Create Account");
        CreateAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateAccountActionPerformed(evt);
            }
        });

        showandhide.setForeground(new java.awt.Color(202, 221, 222));
        showandhide.setText("Show Password");

        Delete.setBackground(new java.awt.Color(255, 0, 0));
        Delete.setText("DELETE USER");
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });

        EditButton.setBackground(new java.awt.Color(153, 153, 153));
        EditButton.setText("EDIT USER DETAILS");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PhoneNumberEntry, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                    .addComponent(UsernameAdminPanelEntry, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                    .addComponent(PasswordAdminPanelEntry, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                    .addComponent(showandhide, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CreateAccount, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                    .addComponent(Delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(EditButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PhoneNumberEntry, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UsernameAdminPanelEntry, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PasswordAdminPanelEntry, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showandhide, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CreateAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                .addComponent(EditButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Delete)
                .addContainerGap())
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S.No", "Name", "Contact"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.setBackground(new java.awt.Color(9, 47, 70));
        jPanel1.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(202, 221, 222));
        jLabel2.setText("ADMIN PANEL");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(17, 6, 130, 25);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
                .addGap(6, 6, 6)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                        .addGap(3, 3, 3))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PasswordAdminPanelEntryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PasswordAdminPanelEntryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PasswordAdminPanelEntryActionPerformed

    private void UsernameAdminPanelEntryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsernameAdminPanelEntryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UsernameAdminPanelEntryActionPerformed

    private void CreateAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateAccountActionPerformed
        // TODO add your handling code here:
//                        new Dashboard().setVisible(true);

        
    }//GEN-LAST:event_CreateAccountActionPerformed

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DeleteActionPerformed

    private void PhoneNumberEntryFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PhoneNumberEntryFocusGained
        // TODO add your handling code here:
          if (PhoneNumberEntry.getText().equals("Phone no.")) {
            PhoneNumberEntry.setText("");
            PhoneNumberEntry.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_PhoneNumberEntryFocusGained

    private void PhoneNumberEntryFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PhoneNumberEntryFocusLost
        // TODO add your handling code here:
          if (PhoneNumberEntry.getText().isEmpty()) {
            PhoneNumberEntry.setText("Phone no.");
            PhoneNumberEntry.setForeground(Color.GRAY);
        }
    }//GEN-LAST:event_PhoneNumberEntryFocusLost

    private void UsernameAdminPanelEntryFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_UsernameAdminPanelEntryFocusGained
        // TODO add your handling code here:
         if (UsernameAdminPanelEntry.getText().equals("Enter username")) {
            UsernameAdminPanelEntry.setText("");
            UsernameAdminPanelEntry.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_UsernameAdminPanelEntryFocusGained

    private void UsernameAdminPanelEntryFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_UsernameAdminPanelEntryFocusLost
        // TODO add your handling code here:
         if (UsernameAdminPanelEntry.getText().isEmpty()) {
            UsernameAdminPanelEntry.setText("Enter username");
            UsernameAdminPanelEntry.setForeground(Color.GRAY);
        }
    }//GEN-LAST:event_UsernameAdminPanelEntryFocusLost

    private void PasswordAdminPanelEntryFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PasswordAdminPanelEntryFocusGained
        // TODO add your handling code here:
        if (String.valueOf(PasswordAdminPanelEntry.getPassword()).equals("Password")) {
            PasswordAdminPanelEntry.setText("");
            PasswordAdminPanelEntry.setEchoChar('*'); 
            PasswordAdminPanelEntry.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_PasswordAdminPanelEntryFocusGained

    private void PasswordAdminPanelEntryFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PasswordAdminPanelEntryFocusLost
        // TODO add your handling code here:
        if (String.valueOf(PasswordAdminPanelEntry.getPassword()).isEmpty()) {
            PasswordAdminPanelEntry.setText("Password");
            PasswordAdminPanelEntry.setEchoChar((char) 0); 
            PasswordAdminPanelEntry.setForeground(Color.GRAY);
        }
    }//GEN-LAST:event_PasswordAdminPanelEntryFocusLost

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
            java.util.logging.Logger.getLogger(AdminPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminPanel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CreateAccount;
    private javax.swing.JButton Delete;
    private javax.swing.JButton EditButton;
    private javax.swing.JPasswordField PasswordAdminPanelEntry;
    private javax.swing.JTextField PhoneNumberEntry;
    private javax.swing.JTextField UsernameAdminPanelEntry;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JCheckBox showandhide;
    // End of variables declaration//GEN-END:variables

public javax.swing.JTextField getPhoneNumberEntry() {
    return PhoneNumberEntry;
}

public javax.swing.JTextField getUsernameAdminPanelEntry() {
    return UsernameAdminPanelEntry;
}

public javax.swing.JPasswordField getPasswordAdminPanelEntry() {
    return PasswordAdminPanelEntry;
}

public void createAccount(ActionListener listener) {
    CreateAccount.addActionListener(listener);
}
public void showandhide(ActionListener listener){
showandhide.addActionListener(listener);

}
public void togglePasswordField(boolean visible) {
PasswordAdminPanelEntry.setEchoChar(visible ? (char) 0 : '*');
//showandhide.setText(visible ? "Hide" : "Show");

}
public javax.swing.JButton getDelete() {
    return Delete;
}
public void addDeleteUserListener(ActionListener listener) {
    Delete.addActionListener(listener);
}
public javax.swing.JTable getUserTable() {
    return jTable1;
}
public void addEditUserListener(ActionListener listener) {
    EditButton.addActionListener(listener);
}



//public void reloadUserTable() {
//    
//    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
//    model.addRow(new Object[] {
//        model.getRowCount() + 1,  // S.No
//        UsernameAdminPanelEntry.getText(),
//        PhoneNumberEntry.getText()
//    });
//}
//public void setUserTableData(java.util.List<AdminPanelModel> users) {
//    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
//    model.setRowCount(0); // Clear existing rows
//    int serial = 1;
//    for (AdminPanelModel user : users) {
//        model.addRow(new Object[]{serial++, user.getUsernameAdminPanelEntry(), user.getPhoneNumberEntry()});
//    }
//}

}



