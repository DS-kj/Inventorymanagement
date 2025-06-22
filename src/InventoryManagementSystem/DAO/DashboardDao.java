package InventoryManagementSystem.DAO;

import InventoryManagementSystem.model.DashboardModel;
import InventoryManagementSystem.database.MySqlConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DashboardDao {

    private final MySqlConnection db = new MySqlConnection();

    public List<DashboardModel> getAllProducts() {
    List<DashboardModel> products = new ArrayList<>();
String query = """
    SELECT p.id, p.name, c.name AS category, p.quantity, p.price
    FROM products p
    JOIN category c ON p.category_id = c.id
""";


    try (Connection conn = db.openConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {

        while (rs.next()) {
            DashboardModel product = new DashboardModel(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("category"), // category name
                rs.getInt("quantity"),
                rs.getDouble("price")
            );
            products.add(product);
        }

    } catch (SQLException e) {
        System.err.println("Error while fetching products: " + e.getMessage());
    }

    return products;
}


    public List<DashboardModel> searchProducts(String keyword) {
        List<DashboardModel> products = new ArrayList<>();
        String sql = """
            SELECT p.id, p.name, c.name AS category, p.quantity, p.price
                FROM products p
                JOIN category c ON p.category_id = c.id
            WHERE p.name LIKE ? OR c.name LIKE ?
        """;

        try (Connection conn = db.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            String pattern = "%" + keyword + "%";
            ps.setString(1, pattern);
            ps.setString(2, pattern);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DashboardModel p = new DashboardModel(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("category"),
                            rs.getInt("quantity"),
                            rs.getDouble("price")
                    );
                    products.add(p);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error while searching products: " + e.getMessage());
        }

        return products;
    }
}
