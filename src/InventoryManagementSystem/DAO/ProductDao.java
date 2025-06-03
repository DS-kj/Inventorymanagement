package InventoryManagementSystem.DAO;

import InventoryManagementSystem.model.Product;
import InventoryManagementSystem.database.MySqlConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductDao {
    MySqlConnection db = new MySqlConnection();

    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products";

        Connection conn = db.openConnection();

        if (conn == null) {
            System.out.println("Failed to connect to the database!");
            return products;
        } else {
            System.out.println("Connected to database in ProductDao.");
        }

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                System.out.println("Loading product: " + rs.getString("product_name")); // Debug print
                Product p = new Product(
                        rs.getInt("id"),
                        rs.getString("product_name"),
                        rs.getString("category"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getDouble("rate")
                );
                products.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.closeConnection(conn);
        }

        return products;
    }
}
