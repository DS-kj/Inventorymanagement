package InventoryManagementSystem.dao;

import InventoryManagementSystem.model.ProductModel;
import InventoryManagementSystem.database.MySqlConnection;
import InventoryManagementSystem.model.CategoryModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductPanelDao {
    MySqlConnection connection = new MySqlConnection();

    public ProductPanelDao() {
        createProductTableIfNotExists();
    }

    private void createProductTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS products ("
                + "id INT PRIMARY KEY AUTO_INCREMENT, "
                + "name VARCHAR(100) NOT NULL, "
                + "category_id INT NOT NULL, "
                + "quantity INT NOT NULL, "
                + "price DOUBLE NOT NULL, "
                + "FOREIGN KEY (category_id) REFERENCES category(id)"
                + ")";

        try (Connection conn = connection.openConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean insertProduct(ProductModel product) {
        String sql = "INSERT INTO products (name, category_id, quantity, price) VALUES (?, ?, ?, ?)";

        try (Connection conn = connection.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, product.getName());
            ps.setInt(2, product.getCategory().getId());
            ps.setInt(3, product.getQuantity());
            ps.setDouble(4, product.getPrice());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<ProductModel> getAllProducts() {
        List<ProductModel> productList = new ArrayList<>();

        String sql = "SELECT p.id, p.name, p.quantity, p.price, c.id AS category_id, c.name AS category_name " +
                     "FROM products p JOIN category c ON p.category_id = c.id";

        try (Connection conn = connection.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");

                int categoryId = rs.getInt("category_id");
                String categoryName = rs.getString("category_name");
                CategoryModel category = new CategoryModel(categoryId, categoryName);

                ProductModel product = new ProductModel(id, name, category, quantity, price);
                productList.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }
    public boolean deleteProduct(int id) {
        String sql = "DELETE FROM products WHERE id = ?";

        try (Connection conn = connection.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateProduct(ProductModel product) {
    String sql = "UPDATE products SET name = ?, quantity = ?, price = ? WHERE id = ?";

    try (Connection conn = connection.openConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, product.getName());
        ps.setInt(2, product.getQuantity());
        ps.setDouble(3, product.getPrice());
        ps.setInt(4, product.getId());

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

}
