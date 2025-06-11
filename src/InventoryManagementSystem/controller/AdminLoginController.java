package InventoryManagementSystem.Controller;

import InventoryManagementSystem.view.AdminLogin;
import InventoryManagementSystem.view.AdminPanel;
import InventoryManagementSystem.controller.AdminPanelController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class AdminLoginController {
    AdminLogin view=new AdminLogin();

    public AdminLoginController(AdminLogin view) {
        this.view = view;

        // Add action listeners
        this.view.addLoginButtonListener(new LoginUser());
        this.view.showPasswordButtonListener(new ShowPasswordToggle());
    }

    public void open() {
        view.setVisible(true);
    }

    public void close() {
        view.dispose();
    }

    // Handles login logic
    class LoginUser implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = view.getEmailText().trim();
            String password = view.getPasswordText().trim();

            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please fill in all the fields.");
                return;
            }

            // Hardcoded admin credentials
            if (email.equals("admin") && password.equals("admin123")) {
                JOptionPane.showMessageDialog(view, "Login successful!");
                    AdminPanel view = new AdminPanel();
                    AdminPanelController controller= new AdminPanelController(view);
                    controller.open();
                close();
            } else {
                JOptionPane.showMessageDialog(view, "Login failed. Invalid admin credentials.");
            }
        }
    }

    // Toggles show/hide password
    class ShowPasswordToggle implements ActionListener {
        private boolean isPasswordVisible = false;
        @Override
        public void actionPerformed(ActionEvent e) {
            JPasswordField passwordField = view.getPasswordField();
            if (isPasswordVisible) {
                passwordField.setEchoChar('*');
                isPasswordVisible = false;
            } else {
                passwordField.setEchoChar((char) 0);
                isPasswordVisible = true;
            }
        }
    }
}
