package InventoryManagementSystem.dao;

import InventoryManagementSystem.model.CustomerModel;
import InventoryManagementSystem.database.MySqlConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class CustomerDao {
    MySqlConnection connection = new MySqlConnection();

    // Method to create table if not exists
    public void createCustomersTableIfNotExists() {
        String sql = """
            CREATE TABLE IF NOT EXISTS customers (
                id INT AUTO_INCREMENT PRIMARY KEY,
                name VARCHAR(100) NOT NULL,
                mobile VARCHAR(20),
                email VARCHAR(100)
            )
        """;

        try (Connection conn = connection.openConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Error creating customers table: " + e.getMessage());
        }
    }

    public List<CustomerModel> getAllCustomers() {
        createCustomersTableIfNotExists(); // ensure table exists before fetching

        List<CustomerModel> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers";

        try (Connection conn = connection.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String mobile = rs.getString("mobile");
                String email = rs.getString("email");

                customers.add(new CustomerModel(id, name, mobile, email));
            }

        } catch (SQLException e) {
            System.err.println("Error fetching customers: " + e.getMessage());
        }

        return customers;
    }
}
