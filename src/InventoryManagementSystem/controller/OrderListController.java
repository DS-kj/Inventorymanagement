package InventoryManagementSystem.controller;

import InventoryManagementSystem.DAO.OrderDao;
import InventoryManagementSystem.model.OrderModel;
import InventoryManagementSystem.view.OrderList;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class OrderListController {
    private OrderList view;
    private OrderDao orderDao;

    public OrderListController(OrderList view,int customerId) {
        this.view = view;
        this.orderDao = new OrderDao();
    }
   public void open(){
    view.setVisible(true);
}
    public void loadOrders(int customerId) {
        List<OrderModel> orders = orderDao.getOrdersByCustomerId(customerId);
        DefaultTableModel tableModel = (DefaultTableModel) view.getOrderTable().getModel();
        tableModel.setRowCount(0); // clear existing data

        for (OrderModel o : orders) {
            Object[] row = {
                o.getOrderId(),
                o.getProductName(),
                o.getQuantity(),
                o.getPrice(),
                o.getOrderDate()
            };
            tableModel.addRow(row);
        }
    }
}
