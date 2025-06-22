package InventoryManagementSystem.controller;

import InventoryManagementSystem.DAO.OrderDao;
import InventoryManagementSystem.model.OrderModel;
import InventoryManagementSystem.view.OrderList;
import InventoryManagementSystem.view.ViewOrders;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class OrderListController {
    private OrderList view;
    private OrderDao orderDao;

    public OrderListController(OrderList view,int customerId) {
        this.view = view;
        this.orderDao = new OrderDao();
        view.addBackButtonListener(new BackButtonListener());
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
    private class BackButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
    ViewOrders viewOrd = new ViewOrders();
         ViewOrdersController controller= new ViewOrdersController(viewOrd);
         controller.open();
         view.dispose();
    }
    }
}
