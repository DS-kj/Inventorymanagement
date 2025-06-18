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
        String query = "SELECT p.id, p.name, p.quantity, p.price, c.id AS category_id, c.name AS category_name " +
                        "FROM products p JOIN category c ON p.category_id = c.id";

        try (Connection conn = connection.openConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String category = rs.getString("category_name");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");

                products.add(new ProductAndCartModel(id, name, category, quantity, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    // public boolean saveOrder(List<ProductAndCartModel> cartItems) {
    //     String insertOrderQuery = "INSERT INTO orders (product_id, quantity, price) VALUES (?, ?, ?)";

    //     try (Connection conn = connection.openConnection();
    //          PreparedStatement stmt = conn.prepareStatement(insertOrderQuery)) {

    //         for (ProductAndCartModel item : cartItems) {
    //             stmt.setInt(1, item.getId());
    //             stmt.setInt(2, item.getQuantity());
    //             stmt.setDouble(3, item.getQuantity() * item.getPrice());
    //             stmt.addBatch();
    //         }
    //         stmt.executeBatch();
    //         return true;

    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //         return false;
    //     }
    // }

 public boolean saveOrder(List<ProductAndCartModel> cartItems, int customerId) {
    String createOrdersTable = """
        CREATE TABLE IF NOT EXISTS orders (
            id INT AUTO_INCREMENT PRIMARY KEY,
            customer_id INT,
            order_date DATETIME DEFAULT CURRENT_TIMESTAMP,
            FOREIGN KEY (customer_id) REFERENCES customers(id)
        )
    """;
    String createOrderItemsTable = """
        CREATE TABLE IF NOT EXISTS order_items (
            id INT AUTO_INCREMENT PRIMARY KEY,
            order_id INT,
            product_id INT,
            quantity INT,
            price DOUBLE,
            FOREIGN KEY (order_id) REFERENCES orders(id),
            FOREIGN KEY (product_id) REFERENCES products(id)
        )
    """;

    String insertOrderQuery = "INSERT INTO orders (customer_id) VALUES (?)";
    String insertOrderItemQuery = "INSERT INTO order_items (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
    String updateQuantityQuery = "UPDATE products SET quantity = quantity - ? WHERE id = ?";

    try (Connection conn = connection.openConnection();
         Statement stmt = conn.createStatement()) {

        stmt.execute(createOrdersTable);
        stmt.execute(createOrderItemsTable);

        conn.setAutoCommit(false);

        // Insert order
        try (PreparedStatement orderStmt = conn.prepareStatement(insertOrderQuery, Statement.RETURN_GENERATED_KEYS)) {
            orderStmt.setInt(1, customerId);
            orderStmt.executeUpdate();

            try (ResultSet generatedKeys = orderStmt.getGeneratedKeys()) {
                if (!generatedKeys.next()) {
                    conn.rollback();
                    return false;
                }

                int orderId = generatedKeys.getInt(1);

                // Insert order items
                try (PreparedStatement itemStmt = conn.prepareStatement(insertOrderItemQuery)) {
                    for (ProductAndCartModel item : cartItems) {
                        itemStmt.setInt(1, orderId);
                        itemStmt.setInt(2, item.getId());
                        itemStmt.setInt(3, item.getQuantity());
                        itemStmt.setDouble(4, item.getPrice());
                        itemStmt.addBatch();
                    }
                    itemStmt.executeBatch();
                }

                // Update product quantities
                try (PreparedStatement updateStmt = conn.prepareStatement(updateQuantityQuery)) {
                    for (ProductAndCartModel item : cartItems) {
                        updateStmt.setInt(1, item.getQuantity());
                        updateStmt.setInt(2, item.getId());
                        updateStmt.addBatch();
                    }
                    updateStmt.executeBatch();
                }

                // Commit transaction
                conn.commit();
                return true;
            }
        } catch (SQLException ex) {
            conn.rollback();
            ex.printStackTrace();
            return false;
        } finally {
            conn.setAutoCommit(true);
        }

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


    

}