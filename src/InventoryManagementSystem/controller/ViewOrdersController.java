package InventoryManagementSystem.controller;

import InventoryManagementSystem.DAO.ViewOrdersDao;
import InventoryManagementSystem.model.ViewOrdersModel;
import InventoryManagementSystem.view.ViewOrders; // Your Swing UI class
import java.util.List;

public class ViewOrdersController {
    private ViewOrdersDao viewOrdersDao;
    private ViewOrders viewOrders;

    public ViewOrdersController(ViewOrdersDao dao, ViewOrders view) {
        this.viewOrdersDao = dao;
        this.viewOrders = view;
        loadCustomerData();
    }

    // Fetch data from DAO and pass to view
    public void loadCustomerData() {
    List<ViewOrdersModel> customers = viewOrdersDao.getAllCustomers();
    viewOrders.setCustomerTableData(customers); // use instance, not class name
}  

    public void open() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}