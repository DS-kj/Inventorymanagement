package InventoryManagementSystem.controller;

import javax.swing.*;
//import InventoryManagementSystem.view.CategoryView;

public class ChooserController {

    public void handleNavigation(String action) {
        switch (action) {
//            case "Category" -> new CategoryView().setVisible(true);

            case "Category" -> showNoTableMessage(action);
            case "Product" -> showNoTableMessage(action);
            case "Customer" -> showNoTableMessage(action);
            case "Order" -> showNoTableMessage(action);
            case "View Order" -> showNoTableMessage(action);
            case "Log Out" -> {
                int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
            default -> JOptionPane.showMessageDialog(null,
                    "No table named '" + action + "' found.",
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
