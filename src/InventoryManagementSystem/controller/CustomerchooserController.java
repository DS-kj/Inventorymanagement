package InventoryManagementSystem.controller;

import InventoryManagementSystem.dao.CustomerDao;
import InventoryManagementSystem.model.CustomerModel;
import InventoryManagementSystem.view.Customerchooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class CustomerchooserController {
    private final Customerchooser view;
    private final CustomerDao customerDao;

    public CustomerchooserController(Customerchooser view) {
        this.view = view;
        this.customerDao = new CustomerDao();
        loadCustomersToTable();
    }
           public void open(){
   
    view.setVisible(true);
}

    private void loadCustomersToTable() {
        List<CustomerModel> customers = customerDao.getAllCustomers();
        DefaultTableModel model = (DefaultTableModel) view.getCustomerTable().getModel();
        model.setRowCount(0); // Clear existing rows

        for (CustomerModel customer : customers) {
            model.addRow(new Object[] {
                customer.getId(),
                customer.getName(),
                customer.getMobile(),
                customer.getEmail()
            });
        }
    }
}
