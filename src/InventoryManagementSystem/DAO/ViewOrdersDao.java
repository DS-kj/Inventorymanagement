package InventoryManagementSystem.DAO;

import InventoryManagementSystem.database.MySqlConnection;
import InventoryManagementSystem.model.ViewOrdersModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ViewOrdersDao {
    MySqlConnection mySql = new MySqlConnection();

    // Only fetch all customers; no add/delete/update
    public List<ViewOrdersModel> getAllCustomers() {
        List<ViewOrdersModel> customer_list = new ArrayList<>();
        String query = "SELECT * FROM customers_list";

        try (Connection conn = mySql.openConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("Name");
                String mobile = rs.getString("Mobile_number");
                String email = rs.getString("Email");
                customer_list.add(new ViewOrdersModel(id, name, mobile, email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer_list;
    }
}
