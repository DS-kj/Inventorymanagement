package InventoryManagementSystem.controller;

import InventoryManagementSystem.dao.ProductandCartDao;
import InventoryManagementSystem.model.ProductandCartModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ProductandCartController {
    private ProductandCartDao dao;

    public ProductandCartController() {
        try {
            // Example connection string; replace with your DB info
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourdb", "root", "password");
            dao = new ProductandCartDao(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveOrder(ProductandCartModel model) {
        try {
            // Validate data (optional)
            if (model.getCustomerId() == 0) {
                JOptionPane.showMessageDialog(null, "Select a customer before saving the order.");
                return;
            }
            boolean saved = dao.saveOrder(model);
            if (saved) {
                JOptionPane.showMessageDialog(null, "Order saved successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to save order.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage());
        }
    }

    // Optionally, you can add a method to fetch customer details
    public ProductandCartModel getCustomerById(int customerId) {
        try {
            return dao.getCustomerById(customerId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
