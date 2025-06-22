package InventoryManagementSystem.controller;


import InventoryManagementSystem.dao.CustomerPanelDao;
import InventoryManagementSystem.model.CustomerPanelModel;
import InventoryManagementSystem.view.Category;
import InventoryManagementSystem.view.CustomerPanel;
import InventoryManagementSystem.view.Customerchooser;
import InventoryManagementSystem.view.Dashboard;
import InventoryManagementSystem.view.LoginPanel;
import InventoryManagementSystem.view.MainPage;
import InventoryManagementSystem.view.ProductPanel;
import InventoryManagementSystem.view.ViewOrders;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

public class CustomerPanelController {
    private CustomerPanel view;

    public CustomerPanelController(CustomerPanel view) {
        this.view = view;
        this.view.addCustomerListener(new AddCustomerListener());
        this.view.addDeleteListener(new DeleteCustomerListener());
        view.dashboard(new DashboardListener());
        view.category(new CategoryListener());
        view.customer(new CustomerListener());
        view.order(new OrderListener());
        view.viewOrder(new ViewOrderListener());
        view.product(new ProductListener());
        view.goBackMainMenu(new MainMenuListener());
        view.logOut(new LogOutListener());
        
    }

    public void open() {
        refreshTable();
        view.setVisible(true);
    }

    private void refreshTable() {
        CustomerPanelDao dao = new CustomerPanelDao();
        List<CustomerPanelModel> list = dao.getAllCustomers();
        view.setCustomerTableData(list);
    }

    class AddCustomerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String name = view.getCustomerName().trim();
                String Email = view.getEmail().trim();
                String MobileNumber = view.getMobileNumber().trim();

                CustomerPanelModel customer = new CustomerPanelModel(name, Email, MobileNumber);
                CustomerPanelDao dao = new CustomerPanelDao();

                if (dao.addCustomer(customer)) {
                    JOptionPane.showMessageDialog(view, "Customer added successfully.");
                    refreshTable();
                    view.clearFields();
                } else {
                    JOptionPane.showMessageDialog(view, "Failed to add customer.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Invalid input.");
            }
        }
    }

    class DeleteCustomerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int row = view.getSelectedRow();
            if (row >= 0) {
                int id = view.getCustomerIdAt(row);
                int confirm = JOptionPane.showConfirmDialog(view, "Are you sure to delete?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    CustomerPanelDao dao = new CustomerPanelDao();
                    if (dao.deleteCustomer(id)) {
                        JOptionPane.showMessageDialog(view, "Customer deleted.");
                        refreshTable();
                    } else {
                        JOptionPane.showMessageDialog(view, "Failed to delete customer.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(view, "Please select a customer.");
            }
        }
    }
        private class DashboardListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           new Dashboard().setVisible(true);
                System.out.println("Dashboard clicked!");
                view.dispose();
        }
    }

    private class CategoryListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Category viewCategory = new Category();
CategoryController controllerCategory = new CategoryController(viewCategory);
controllerCategory.open();
                System.out.println("Category clicked!");
                view.dispose();
        }
    }

    private class CustomerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CustomerPanel viewCustomerP=new CustomerPanel();
                 CustomerPanelController customerP=new CustomerPanelController(viewCustomerP);
                 
                 customerP.open();
                System.out.println("Customer clicked!");
                view.dispose();
        }
    }

    private class OrderListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Customerchooser chooser = new Customerchooser();
            CustomerchooserController chooserCustomer=new CustomerchooserController(chooser);
                    chooserCustomer.open();
            view.dispose();
        }
    }

    private class ViewOrderListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           ViewOrders viewOrder = new ViewOrders();
         ViewOrdersController controllerOrder= new ViewOrdersController(viewOrder);
         controllerOrder.open();
                System.out.println("History clicked!");
                view.dispose();
        }
    }
    private class ProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ProductPanel prodView = new ProductPanel();
        ProductPanelController controller = new ProductPanelController(prodView);
        controller.show();
                System.out.println("Product clicked!");
                view.dispose();
        }
    }
    private class MainMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            MainPage mainView=new MainPage();
         MainPageController mainPageOpener= new MainPageController(mainView);
         mainPageOpener.open();
                System.out.println("Main Menu clicked!");
                view.dispose();
        }
    }
    private class LogOutListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int response = javax.swing.JOptionPane.showConfirmDialog(
            view,
            "Are you sure you want to log out?",
            "Confirm Logout",
            javax.swing.JOptionPane.YES_NO_OPTION,
            javax.swing.JOptionPane.QUESTION_MESSAGE
        );

        if (response == javax.swing.JOptionPane.YES_OPTION) {
            view.dispose();
            LoginPanel viewLogin=new LoginPanel();
                LoginController LoginOpener= new LoginController(viewLogin);
                 LoginOpener.open();
            System.out.println("User logged out.");
        } else {
            System.out.println("Logout cancelled.");
        }
    
            }
        }
}

