package InventoryManagementSystem.DAO;

import InventoryManagementSystem.model.ProductPanelModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductPanelDao {

    // Reusable connection function
    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/inventory?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String user = "root";
        String pass = "12345";
        return DriverManager.getConnection(url, user, pass);
    }

    // Add product with validation
    public boolean addProduct(ProductPanelModel product) {
        // Check for null or empty fields
        if (product.getName() == null || product.getName().trim().isEmpty() ||
            product.getCategory() == null || product.getCategory().trim().isEmpty()) {
            System.err.println("Product name or category cannot be empty.");
            return false;
        }

        // Check for invalid quantity/price
        if (product.getQuantity() < 0 || product.getPrice() < 0) {
            System.err.println("Quantity or price cannot be negative.");
            return false;
        }

        String sql = "INSERT INTO products (name, category, quantity, price) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, product.getName().trim());
            stmt.setString(2, product.getCategory().trim());
            stmt.setInt(3, product.getQuantity());
            stmt.setDouble(4, product.getPrice());
            int rows = stmt.executeUpdate();
            System.out.println("Product added. Rows affected: " + rows);
            return rows > 0;
        } catch (SQLException e) {
            System.err.println("Error inserting product: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Delete product by ID
    public boolean deleteProduct(int id) {
        String sql = "DELETE FROM products WHERE id=?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            System.out.println("Product deleted. Rows affected: " + rows);
            return rows > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting product: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Retrieve all products
    public List<ProductPanelModel> getAllProducts() {
        List<ProductPanelModel> products = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                products.add(new ProductPanelModel(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("category"),
                    rs.getInt("quantity"),
                    rs.getDouble("price")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving products: " + e.getMessage());
            e.printStackTrace();
        }
        return products;
    }

    // Get category by product name
    public String getCategoryByProductName(String productName) {
        String sql = "SELECT category FROM products WHERE name LIKE ? LIMIT 1"; // Corrected table name
        try (Connection conn = getConnection(); // Use the reusable connection method
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + productName + "%");
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("category");
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving category: " + e.getMessage());
            e.printStackTrace();
        }
        return null; // Return null if not found
    }
}
