package InventoryManagementSystem.dao;

import InventoryManagementSystem.model.ProductandCartModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductandCartDao {
    private Connection connection;

    public ProductandCartDao(Connection connection) {
        this.connection = connection;
    }

    // Get customer data by customer ID
    public ProductandCartModel getCustomerById(int customerId) throws SQLException {
        String query = "SELECT id, name, mobilenumber, email FROM Customerchooser WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, customerId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ProductandCartModel model = new ProductandCartModel();
                    model.setCustomerId(rs.getInt("id"));
                    model.setCustomerName(rs.getString("name"));
                    model.setMobileNumber(rs.getString("mobilenumber"));
                    model.setEmail(rs.getString("email"));
                    return model;
                }
            }
        }
        return null;
    }

    // Save an order (product + cart info) linked to a customer
    public boolean saveOrder(ProductandCartModel model) throws SQLException {
        String query = "INSERT INTO ProductandCart (productName, quantity, price, customerId) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, model.getProductName());
            ps.setInt(2, model.getQuantity());
            ps.setDouble(3, model.getPrice());
            ps.setInt(4, model.getCustomerId());

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        }
    }
}
