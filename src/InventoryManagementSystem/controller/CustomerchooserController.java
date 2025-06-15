package InventoryManagementSystem.controller;

import InventoryManagementSystem.dao.CustomerDao;
import InventoryManagementSystem.model.CustomerModel;
import InventoryManagementSystem.view.Customerchooser;
import InventoryManagementSystem.view.ProductandCart;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        view.addSelectCustomerListener(new SelectCustomerListener());
    }
           public void open(){
   
    view.setVisible(true);
}

    private void loadCustomersToTable() {
        List<CustomerModel> customers = customerDao.getAllCustomers();
        DefaultTableModel model = (DefaultTableModel) view.getCustomerTable().getModel();
        model.setRowCount(0); 

        for (CustomerModel customer : customers) {
            model.addRow(new Object[] {
                customer.getId(),
                customer.getName(),
                customer.getMobile(),
                customer.getEmail()
            });
        }
    }
    private class SelectCustomerListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRow = view.getCustomerTable().getSelectedRow();
        if (selectedRow >= 0) {
            int customerId = Integer.parseInt(view.getCustomerTable().getValueAt(selectedRow, 0).toString());
            ProductandCart productAndCartView = new ProductandCart();
        ProductAndCartController controller= new ProductAndCartController(productAndCartView, customerId);
        controller.open();
        
            view.dispose();
        } else {
            JOptionPane.showMessageDialog(view, "Please select a customer.");
        }
    }
}

}
