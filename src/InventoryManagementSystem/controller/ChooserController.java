package InventoryManagementSystem.controller;

import InventoryManagementSystem.view.Category;
import InventoryManagementSystem.view.CustomerPanel;
import javax.swing.*;
//import InventoryManagementSystem.view.CategoryView;

import InventoryManagementSystem.view.OrderList;
import InventoryManagementSystem.view.ProductPanel;
import InventoryManagementSystem.view.ViewOrders;

public class ChooserController {

    public void handleNavigation(String action) {
        switch (action) {
case "Category" -> {
            Category view = new Category();
            CategoryController controller = new CategoryController(view);
            controller.open();
        }
        case "Product" -> {
            ProductPanel view = new ProductPanel();
            ProductPanelController controller = new ProductPanelController(view);
            controller.open();
        }
        case "Customer" -> {
            CustomerPanel view = new CustomerPanel();
            CustomerPanelController controller = new CustomerPanelController(view);
            controller.open();
        }
        case "Order" -> {
           int customerId = 1;
        OrderList view = new OrderList();
        OrderListController controller = new OrderListController(view, customerId);

        }
        case "View Order" -> {
            ViewOrders view = new ViewOrders();
            ViewOrdersController controller = new ViewOrdersController(view);
            controller.open();
        }
            case "Log Out" -> {
                int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
            default -> JOptionPane.showMessageDialog(null,
                    "Already on the table or No table named '" + action + "' found.",
                    "Information",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void showNoTableMessage(String tableName) {
        JOptionPane.showMessageDialog(null,
                "No table named '" + tableName + "' found.",
                "Information",
                JOptionPane.INFORMATION_MESSAGE);
    }
    
    
    
}
