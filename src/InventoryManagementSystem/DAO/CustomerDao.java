package InventoryManagementSystem.dao;

import InventoryManagementSystem.model.CustomerModel;
import InventoryManagementSystem.database.MySqlConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {
    MySqlConnection connection = new MySqlConnection();

    public List<CustomerModel> getAllCustomers() {
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
            e.printStackTrace();
        }

        return customers;
    }
}
