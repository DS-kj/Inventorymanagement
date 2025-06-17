/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package InventoryManagementSystem.controller;

import InventoryManagementSystem.dao.OrderListDao;
import InventoryManagementSystem.model.OrderListModel;
import InventoryManagementSystem.view.OrderList;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.util.ArrayList;

public class OrderListController {
    private OrderListDao orderListDao;
    private OrderList orderListView;

    public OrderListController(Connection con, OrderList orderListView) {
        this.orderListDao = new OrderListDao(con);
        this.orderListView = orderListView;
    }

    public void loadOrdersByCustomerId(int customerId) {
        ArrayList<OrderListModel> orders = orderListDao.getOrdersByCustomerId(customerId);
        DefaultTableModel model = (DefaultTableModel) orderListView.getOrdertable().getModel();
        model.setRowCount(0); // Clear existing data
        
        for (OrderListModel order : orders) {
            model.addRow(new Object[]{
                order.getOrderId(),
                order.getOrderDate(),
                order.getTotalPaid()
            });
        }
    }
}