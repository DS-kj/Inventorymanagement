package InventoryManagementSystem.dao;

import InventoryManagementSystem.model.CustomerPanelModel;
import InventoryManagementSystem.database.MySqlConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerPanelDao {

    private MySqlConnection connection = new MySqlConnection();

   
    public boolean addCustomer(CustomerPanelModel customer) {
        if (customer.getName() == null || customer.getName().trim().isEmpty() ||
            customer.getMobileNumber() == null || customer.getMobileNumber().trim().isEmpty() ||
            customer.getEmail() == null || customer.getEmail().trim().isEmpty()) {
            System.err.println("Customer name, MobileNumber, or email cannot be empty.");
            return false;
        }

        String sql = "INSERT INTO customers (name, MobileNumber, email) VALUES (?, ?, ?)";
        try (Connection conn = connection.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customer.getName().trim());
            stmt.setString(2, customer.getMobileNumber().trim());
            stmt.setString(3, customer.getEmail().trim());

            int rows = stmt.executeUpdate();
            System.out.println("Customer added. Rows affected: " + rows);
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("Error inserting customer: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCustomer(int id) {
        String sql = "DELETE FROM customers WHERE id = ?";
        try (Connection conn = connection.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            System.out.println("Customer deleted. Rows affected: " + rows);
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting customer: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<CustomerPanelModel> getAllCustomers() {
        List<CustomerPanelModel> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers";

        try (Connection conn = connection.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                customers.add(new CustomerPanelModel(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("mobile")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving customers: " + e.getMessage());
            e.printStackTrace();
        }

        return customers;
    }
    public boolean updateCustomer(CustomerPanelModel customer) {
    if (customer.getId() <= 0 ||
        customer.getName() == null || customer.getName().trim().isEmpty() ||
        customer.getMobileNumber() == null || customer.getMobileNumber().trim().isEmpty() ||
        customer.getEmail() == null || customer.getEmail().trim().isEmpty()) {
        System.err.println("Invalid customer data.");
        return false;
    }

    String sql = "UPDATE customers SET name = ?, mobile = ?, email = ? WHERE id = ?";
    try (Connection conn = connection.openConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, customer.getName().trim());
        stmt.setString(2, customer.getMobileNumber().trim());
        stmt.setString(3, customer.getEmail().trim());
        stmt.setInt(4, customer.getId());

        int rows = stmt.executeUpdate();
        System.out.println("Customer updated. Rows affected: " + rows);
        return rows > 0;

    } catch (SQLException e) {
        System.err.println("Error updating customer: " + e.getMessage());
        e.printStackTrace();
        return false;
    }
}
}
