/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package InventoryManagementSystem.dao;

import InventoryManagementSystem.model.OrderListModel;
import java.sql.*;
import java.util.ArrayList;

public class OrderListDao {
    private Connection mySql;

    public OrderListDao(Connection con) {
        this.mySql = con;
    }

    public ArrayList<OrderListModel> getOrdersByCustomerId(int customerId) {
        ArrayList<OrderListModel> list = new ArrayList<>();
        String sql = "SELECT Order_ID, Order_date, Total_Paid FROM order_list WHERE ID = ?";
        
        try (PreparedStatement stmt = mySql.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                list.add(new OrderListModel(
                    rs.getInt("Order_ID"),
                    rs.getString("Order_date"),
                    rs.getLong("Total_Paid")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

