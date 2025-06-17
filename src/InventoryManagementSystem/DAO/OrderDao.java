package InventoryManagementSystem.DAO;
 
import InventoryManagementSystem.database.MySqlConnection;
import InventoryManagementSystem.model.OrderModel;
 
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
 
public class OrderDao {
    MySqlConnection db = new MySqlConnection();
 
    public List<OrderModel> getOrdersByCustomerId(int customerId) {
        List<OrderModel> orders = new ArrayList<>();
        String query = """
            SELECT o.id AS order_id, p.name AS product_name, oi.quantity, oi.price, o.order_date
            FROM orders o
            JOIN order_items oi ON o.id = oi.order_id
            JOIN products p ON oi.product_id = p.id
            WHERE o.customer_id = ?
            ORDER BY o.order_date DESC
        """;
 
        try (Connection conn = db.openConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
 
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
 
            while (rs.next()) {
                OrderModel order = new OrderModel(
                        rs.getInt("order_id"),
                        rs.getString("product_name"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getString("order_date")
                );
                orders.add(order);
            }
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
        return orders;
    }
}