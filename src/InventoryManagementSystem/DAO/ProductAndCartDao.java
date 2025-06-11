package InventoryManagementSystem.DAO;

import InventoryManagementSystem.database.MySqlConnection;
import InventoryManagementSystem.model.ProductAndCartModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductAndCartDao {
    private final MySqlConnection connection = new MySqlConnection();

    public List<ProductAndCartModel> getAllProducts() {
        List<ProductAndCartModel> products = new ArrayList<>();
        String query = "SELECT * FROM products";

        try (Connection conn = connection.openConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String category = rs.getString("category");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");

                products.add(new ProductAndCartModel(id, name, category, quantity, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    public boolean saveOrder(List<ProductAndCartModel> cartItems) {
        String insertOrderQuery = "INSERT INTO orders (product_id, quantity, price) VALUES (?, ?, ?)";

        try (Connection conn = connection.openConnection();
             PreparedStatement stmt = conn.prepareStatement(insertOrderQuery)) {

            for (ProductAndCartModel item : cartItems) {
                stmt.setInt(1, item.getId());
                stmt.setInt(2, item.getQuantity());
                stmt.setDouble(3, item.getQuantity() * item.getPrice());
                stmt.addBatch();
            }
            stmt.executeBatch();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}