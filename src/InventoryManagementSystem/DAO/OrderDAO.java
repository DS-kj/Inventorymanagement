package InventoryManagementSystem.dao;

import InventoryManagementSystem.model.OrderModel;
import InventoryManagementSystem.model.CartItemModel;
import java.sql.*;

public class OrderDAO {
    private final Connection conn;

    public OrderDAO(Connection conn) {
        this.conn = conn;
    }

    public void saveOrder(OrderModel order) throws SQLException {
        String insertOrderSQL = "INSERT INTO orders (customer_id) VALUES (?)";
        String insertItemSQL = "INSERT INTO order_items (order_id, product_name, quantity, price) VALUES (?, ?, ?, ?)";

        try (PreparedStatement psOrder = conn.prepareStatement(insertOrderSQL, Statement.RETURN_GENERATED_KEYS)) {
            psOrder.setInt(1, order.getCustomerId());
            psOrder.executeUpdate();

            ResultSet rs = psOrder.getGeneratedKeys();
            if (rs.next()) {
                int orderId = rs.getInt(1);

                try (PreparedStatement psItem = conn.prepareStatement(insertItemSQL)) {
                    for (CartItemModel item : order.getItems()) {
                        psItem.setInt(1, orderId);
                        psItem.setString(2, item.getProductName());
                        psItem.setInt(3, item.getQuantity());
                        psItem.setDouble(4, item.getPrice());
                        psItem.addBatch();
                    }
                    psItem.executeBatch();
                }
            }
        }
    }
}
