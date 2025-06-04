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
    private AdminPanel view;

    public AdminPanelController(AdminPanel view) {
        this.view = view;
        this.view.createAccount(new CreateAccountListener());

    }
     public void open(){
        view.setVisible(true);
    }
    public void close(){
        view.dispose();
    }
    

class CreateAccountListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String phone = view.getPhoneNumberEntry().getText();
            String name = view.getUsernameAdminPanelEntry().getText();
            String password = view.getPasswordAdminPanelEntry().getText(); // Optional: Convert to char[] if security is important


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
}

