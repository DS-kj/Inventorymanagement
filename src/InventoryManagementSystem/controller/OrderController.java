package InventoryManagementSystem.controller;

import InventoryManagementSystem.dao.OrderDAO;
import InventoryManagementSystem.model.OrderModel;

import java.sql.Connection;

public class OrderController {
    private final OrderDAO orderDAO;

    public OrderController(Connection conn) {
        this.orderDAO = new OrderDAO(conn);
    }

    public void saveOrder(OrderModel model) {
        try {
            orderDAO.saveOrder(model);
            System.out.println("Order saved!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
