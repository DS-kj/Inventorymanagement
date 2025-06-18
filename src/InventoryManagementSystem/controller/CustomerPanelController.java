package InventoryManagementSystem.controller;


import InventoryManagementSystem.dao.CustomerPanelDao;
import InventoryManagementSystem.model.CustomerPanelModel;
import InventoryManagementSystem.view.CustomerPanel;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

public class CustomerPanelController {
    private CustomerPanel view;

    public CustomerPanelController(CustomerPanel view) {
        this.view = view;
        this.view.addCustomerListener(new AddCustomerListener());
        this.view.addDeleteListener(new DeleteCustomerListener());
    }

    public void open() {
        refreshTable();
        view.setVisible(true);
    }

    private void refreshTable() {
        CustomerPanelDao dao = new CustomerPanelDao();
        List<CustomerPanelModel> list = dao.getAllCustomers();
        view.setCustomerTableData(list);
    }

    class AddCustomerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String name = view.getCustomerName().trim();
                String Email = view.getEmail().trim();
                String MobileNumber = view.getMobileNumber().trim();

                CustomerPanelModel customer = new CustomerPanelModel(name, Email, MobileNumber);
                CustomerPanelDao dao = new CustomerPanelDao();

                if (dao.addCustomer(customer)) {
                    JOptionPane.showMessageDialog(view, "Customer added successfully.");
                    refreshTable();
                    view.clearFields();
                } else {
                    JOptionPane.showMessageDialog(view, "Failed to add customer.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Invalid input.");
            }
        }
    }

    class DeleteCustomerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int row = view.getSelectedRow();
            if (row >= 0) {
                int id = view.getCustomerIdAt(row);
                int confirm = JOptionPane.showConfirmDialog(view, "Are you sure to delete?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    CustomerPanelDao dao = new CustomerPanelDao();
                    if (dao.deleteCustomer(id)) {
                        JOptionPane.showMessageDialog(view, "Customer deleted.");
                        refreshTable();
                    } else {
                        JOptionPane.showMessageDialog(view, "Failed to delete customer.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(view, "Please select a customer.");
            }
        }
    }
}
