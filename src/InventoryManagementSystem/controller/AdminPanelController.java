/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package InventoryManagementSystem.controller;
//
//import InventoryManagementSystem.DAO.UserDataDao;

import InventoryManagementSystem.DAO.UserDataDao;
import InventoryManagementSystem.model.AdminPanelModel;
import InventoryManagementSystem.view.AdminPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

//import InventoryManagementSystem.model.AdminPanelModel;
//import InventoryManagementSystem.view.AdminPanel;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;






/**
 *
 * @author ACER
 */
public class AdminPanelController {
    AdminPanel view = new AdminPanel();

    public AdminPanelController(AdminPanel view) {
        this.view = view;
        this.view.createAccount(new CreateAccountListener());
        this.view.showandhide(new ShowPasswordToggleListener()); 
        view.addEditUserListener(new EditUserListener());

        this.view.addDeleteUserListener(new DeleteUserListener());
    

    }
   public void open(){
    refreshUserTable();
    view.setVisible(true);
}

public void refreshUserTable() {
    UserDataDao dao = new UserDataDao();
    List<AdminPanelModel> users = dao.getAllUsers();
    view.setUserTableData(users);
}

    public void close(){
        view.dispose();
    }
    
class ShowPasswordToggleListener implements ActionListener {
    private boolean passwordVisible = false;

    @Override
    public void actionPerformed(ActionEvent e) {
        passwordVisible = !passwordVisible;
        view.togglePasswordField(passwordVisible);
    }
}
class EditUserListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRow = view.getUserTable().getSelectedRow();
        if (selectedRow != -1) {
            String phone = (String) view.getUserTable().getValueAt(selectedRow, 2); // Phone number column
            String name = (String) view.getUserTable().getValueAt(selectedRow, 1);  // Name column

            AdminPanelModel selectedUser = new AdminPanelModel(phone, name, "");
            new UserEditorController(selectedUser, AdminPanelController.this);  // <-- pass 'this' here
        } else {
            JOptionPane.showMessageDialog(view, "Please select a user to edit.");
        }
    }
}



class CreateAccountListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String phone = view.getPhoneNumberEntry().getText();
            String name = view.getUsernameAdminPanelEntry().getText();
            String password = view.getPasswordAdminPanelEntry().getText(); 


            if (phone.isEmpty() || name.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please fill in all fields.");
                return;
            }

            AdminPanelModel user = new AdminPanelModel(phone, name, password);
            UserDataDao dao = new UserDataDao();

            if (dao.register(user)) {
                JOptionPane.showMessageDialog(view, "Account created successfully.");
                view.reloadUserTable(); 
            } else {
                JOptionPane.showMessageDialog(view, "Failed to create account.");
            }
        }
    } 
class DeleteUserListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRow = view.getUserTable().getSelectedRow();

        if (selectedRow != -1) {
            String phoneNumber = (String) view.getUserTable().getValueAt(selectedRow, 2); // column index for phone number

            int confirm = JOptionPane.showConfirmDialog(view,
                    "Are you sure you want to delete this user?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                UserDataDao dao = new UserDataDao();
                if (dao.deleteUser(phoneNumber)) {
                    refreshUserTable(); // Refresh table after deletion
                    JOptionPane.showMessageDialog(view, "User deleted successfully.");
                } else {
                    JOptionPane.showMessageDialog(view, "Failed to delete user.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(view, "Please select a user to delete.");
        }
    }
}

}

