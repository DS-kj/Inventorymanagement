package InventoryManagementSystem.DAO;

import InventoryManagementSystem.model.CustomerPanelModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerPanelDao {

    // Database connection setup
    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/inventory?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String user = "root";
        String pass = "databasesem2";
        return DriverManager.getConnection(url, user, pass);
    }

    // Add customer with validation
    public boolean addCustomer(CustomerPanelModel customer) {
        // Validate input
        if (customer.getName() == null ||customer.getName().trim().isEmpty()||
                customer.getMobileNumber() ==null ||  customer.getMobileNumber().trim().isEmpty()||
                customer.getEmail() ==null ||  customer.getEmail().trim().isEmpty())  {
            System.err.println("Customer name or mobile number cannot be empty.");
            return false;
        }

        String sql = "INSERT INTO Customers (Name, MobileNumber, Email) VALUES (?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
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

    // Delete customer by ID
    public boolean deleteCustomer(int id) {
        String sql = "DELETE FROM Customers WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
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

    // Retrieve all customers
    public List<CustomerPanelModel> getAllCustomers() {
        List<CustomerPanelModel> customers = new ArrayList<>();
        String sql = "SELECT * FROM Customers";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                customers.add(new CustomerPanelModel(
                    rs.getInt("id"),
                    rs.getString("Name"),
                    rs.getString("Email"),
                    rs.getString("MobileNumber")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving customers: " + e.getMessage());
            e.printStackTrace();
        }
        return customers;
    }
}
