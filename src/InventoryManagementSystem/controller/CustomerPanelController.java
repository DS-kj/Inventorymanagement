package InventoryManagementSystem.controller;


import InventoryManagementSystem.dao.CustomerPanelDao;
import InventoryManagementSystem.model.CustomerPanelModel;
import InventoryManagementSystem.view.Category;
import InventoryManagementSystem.view.CustomerPanel;
import InventoryManagementSystem.view.Customerchooser;
import InventoryManagementSystem.view.Dashboard;
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
        view.dashboard(new CustomerchooserController.DashboardListener());
        view.category(new CustomerchooserController.CategoryListener());
        view.customer(new CustomerchooserController.CustomerListener());
        view.order(new CustomerchooserController.OrderListener());
        view.viewOrder(new CustomerchooserController.ViewOrderListener());
        view.product(new CustomerchooserController.ProductListener());
        view.goBackMainMenu(new CustomerchooserController.MainMenuListener());
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
            new CustomerchooserController(chooser).open();
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
}
