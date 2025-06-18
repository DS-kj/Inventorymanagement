package InventoryManagementSystem.DAO;

import InventoryManagementSystem.database.DbConnection;
import InventoryManagementSystem.database.MySqlConnection; // Assuming you have this
import InventoryManagementSystem.model.ProductPanelModel;
import java.math.BigDecimal; // Import BigDecimal
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional; // Import Optional for search result

public class ProductPanelDao {

    private DbConnection dbConnection;

    public ProductPanelDao() {
        this.dbConnection = new MySqlConnection(); // Or whatever your concrete implementation is
    }

    /**
     * Adds a new product to the database.
     * @param product The ProductPanelModel object containing product details.
     * @return true if the product was added successfully, false otherwise.
     */
    public boolean addProduct(ProductPanelModel product) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbConnection.openConnection();
            if (conn == null) {
                System.err.println("DB connection failed for addProduct.");
                return false;
            }

            int categoryId = getCategoryIdByName(product.getCategory()); // Get ID for the category name
            if (categoryId == -1) {
                System.err.println("Category not found for name: " + product.getCategory() + ". Product not added.");
                // Optionally, could offer to add the category here or throw an exception.
                return false;
            }

            String sql = "INSERT INTO product (name, category_id, quantity, price) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, product.getName());
            pstmt.setInt(2, categoryId);
            pstmt.setInt(3, product.getQuantity());
            pstmt.setBigDecimal(4, product.getPrice()); // Set BigDecimal

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("SQL Error in addProduct: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) { /* ignore */ }
            dbConnection.closeConnection(conn);
        }
    }

    /**
     * Deletes a product from the database by its ID.
     * @param productId The ID of the product to delete.
     * @return true if the product was deleted successfully, false otherwise.
     */
    public boolean deleteProduct(int productId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = dbConnection.openConnection();
            if (conn == null) {
                System.err.println("DB connection failed for deleteProduct.");
                return false;
            }
            String sql = "DELETE FROM product WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, productId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("SQL Error in deleteProduct: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) { /* ignore */ }
            dbConnection.closeConnection(conn);
        }
    }

    /**
     * Retrieves all products from the database.
     * @return A list of ProductPanelModel objects.
     */
    public List<ProductPanelModel> getAllProducts() {
        List<ProductPanelModel> products = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbConnection.openConnection();
            if (conn == null) {
                System.err.println("DB connection failed for getAllProducts.");
                return products;
            }
            String sql = "SELECT p.id, p.name, c.name AS category_name, p.quantity, p.price " +
                         "FROM product p JOIN category c ON p.category_id = c.id";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String category = rs.getString("category_name");
                int quantity = rs.getInt("quantity");
                BigDecimal price = rs.getBigDecimal("price"); // Get BigDecimal
                products.add(new ProductPanelModel(id, name, category, quantity, price));
            }
        } catch (SQLException e) {
            System.err.println("SQL Error in getAllProducts: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) { /* ignore */ }
            dbConnection.closeConnection(conn);
        }
        return products;
    }

    /**
     * Retrieves a single product by its name.
     * @param productName The name of the product to search for.
     * @return An Optional containing the ProductPanelModel if found, or empty if not found.
     */
    public Optional<ProductPanelModel> getProductByName(String productName) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ProductPanelModel product = null;

        String sql = "SELECT p.id, p.name AS product_name, c.name AS category_name, p.quantity, p.price " +
                     "FROM product p " +
                     "JOIN category c ON p.category_id = c.id " +
                     "WHERE p.name = ?"; // Using exact match for search

        try {
            conn = dbConnection.openConnection();
            if (conn == null) {
                System.err.println("Database connection failed for getProductByName.");
                return Optional.empty();
            }

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, productName);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String prodName = rs.getString("product_name");
                String categoryName = rs.getString("category_name");
                int quantity = rs.getInt("quantity");
                BigDecimal price = rs.getBigDecimal("price"); // Get BigDecimal

                product = new ProductPanelModel(id, prodName, categoryName, quantity, price);
            }
        } catch (SQLException e) {
            System.err.println("SQL error fetching product by name: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) { /* ignore */ }
            dbConnection.closeConnection(conn);
        }
        return Optional.ofNullable(product);
    }

    /**
     * Helper method to get category ID by category name.
     * @param categoryName The name of the category.
     * @return The ID of the category, or -1 if not found.
     * @throws SQLException If a database access error occurs.
     */
    private int getCategoryIdByName(String categoryName) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int categoryId = -1;
        try {
            conn = dbConnection.openConnection();
            if (conn == null) {
                System.err.println("DB connection failed for getCategoryIdByName.");
                return -1;
            }
            String sql = "SELECT id FROM category WHERE name = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, categoryName);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                categoryId = rs.getInt("id");
            }
        } finally {
            // It's crucial to close resources in a finally block
            // However, if dbConnection.openConnection() returns a connection that is
            // managed externally (e.g., connection pool), closing here might be wrong.
            // Assuming for now it's a simple connection that needs closing.
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) { /* ignore */ }
            dbConnection.closeConnection(conn); // Close the connection opened *within* this method
        }
        return categoryId;
    }
}