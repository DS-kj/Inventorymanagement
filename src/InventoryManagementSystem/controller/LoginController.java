package InventoryManagementSystem.controller;

import InventoryManagementSystem.Controller.AdminLoginController;
import InventoryManagementSystem.DAO.UserDao;
import InventoryManagementSystem.model.LoginRequest;
import InventoryManagementSystem.DAO.UserData;
import InventoryManagementSystem.controller.AdminPanelController;
import InventoryManagementSystem.controller.MainPageController;
import InventoryManagementSystem.view.AdminLogin;
import InventoryManagementSystem.view.AdminPanel;
import InventoryManagementSystem.view.Dashboard; 
import InventoryManagementSystem.view.LoginPanel;
import InventoryManagementSystem.view.MainPage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class LoginController {
    private final LoginPanel view;

    public LoginController(LoginPanel view) {
        this.view = view;
        this.view.addLoginButtonListener(new LoginUser());
                this.view.addAdminSwitch(new GoToAdmin());

    }

    public void open() {
        view.setVisible(true);
    }

    public void close() {
        view.dispose();
    }

    class LoginUser implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = view.getEmailText();
            String password = view.getPasswordText();

            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please fill in all the fields.");
                return;
            }

            LoginRequest loginData = new LoginRequest(email, password);
            UserDao userDao = new UserDao();
            UserData user = userDao.login(loginData);

            if (user == null) {
                JOptionPane.showMessageDialog(view, "Login failed. Invalid email or password.");
            } else {
                JOptionPane.showMessageDialog(view, "Login successful!");

                
                MainPage view=new MainPage();
         MainPageController mainPageOpener= new MainPageController(view);
         mainPageOpener.open();

                close();  
            }
        }
    }
    class GoToAdmin implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            AdminLogin view= new AdminLogin();
            AdminLoginController controller= new AdminLoginController(view);
            controller.open();
//        AdminPanel view = new AdminPanel();
//        AdminPanelController controller= new AdminPanelController(view);
//        controller.open();
        }}
}
 
