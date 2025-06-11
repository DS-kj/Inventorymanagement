package InventoryManagementSystem.controller;

import InventoryManagementSystem.DAO.UserDataDao;
import InventoryManagementSystem.model.AdminPanelModel;
import InventoryManagementSystem.view.UserEditor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserEditorController {

    private AdminPanelController parentController;  // <-- Paste this line here, right below class declaration

    public UserEditorController(AdminPanelModel originalUser, AdminPanelController parentController) {
        this.parentController = parentController;  // store the reference

        UserEditor view = new UserEditor();
        view.setUser(originalUser);

        view.addSaveListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newPhone = view.getPhoneNumberField().getText();
                String newName = view.getUsernameField().getText();
                String newPassword = view.getPasswordField().getText();

                if (newPhone.isEmpty() || newName.isEmpty() || newPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Please fill in all fields.");
                    return;
                }

                AdminPanelModel updatedUser = new AdminPanelModel(newPhone, newName, newPassword);
                UserDataDao dao = new UserDataDao();

                boolean success = dao.updateUser(
                        originalUser.getPhoneNumberEntry(),
                        updatedUser.getPhoneNumberEntry(),
                        updatedUser.getUsernameAdminPanelEntry(),
                        updatedUser.getPasswordAdminPanelEntry()
                );

                if (success) {
                    JOptionPane.showMessageDialog(view, "User updated successfully.");
                    view.dispose();
                    parentController.refreshUserTable();  // Refresh admin panel table here
                } else {
                    JOptionPane.showMessageDialog(view, "Failed to update user.");
                }
            }
        });

        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }
}
