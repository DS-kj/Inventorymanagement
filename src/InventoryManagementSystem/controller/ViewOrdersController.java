package InventoryManagementSystem.controller;

import InventoryManagementSystem.dao.CustomerDao;
import InventoryManagementSystem.model.CustomerModel;
import InventoryManagementSystem.view.Category;
import InventoryManagementSystem.view.OrderList;
import InventoryManagementSystem.view.ViewOrders;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;
import java.util.List;
import javax.swing.JOptionPane;

public class ViewOrdersController {
    private final ViewOrders view;
    private final CustomerDao customerDao;

    public ViewOrdersController(ViewOrders view) {
        this.view = view;
        this.customerDao = new CustomerDao();  
        view.addCategoryButtonListener(e -> handleNavigation("Category"));
        loadCustomersToTable();
        view.addSelectCustomerListener(new SelectCustomerListener());

    }
 public void open(){
    loadCustomersToTable();
    view.setVisible(true);
}
     private void loadCustomersToTable() {
        List<CustomerModel> customers = customerDao.getAllCustomers();
        DefaultTableModel model = (DefaultTableModel) view.getCustomertable().getModel();
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
        int selectedRow = view.getCustomertable().getSelectedRow();
        if (selectedRow >= 0) {
            int customerId = Integer.parseInt(view.getCustomertable().getValueAt(selectedRow, 0).toString());
            OrderList orderListView = new OrderList();
        OrderListController controller= new OrderListController(orderListView, customerId);
        controller.open();
                controller.loadOrders(customerId);

        
            view.dispose();
        } else {
            JOptionPane.showMessageDialog(view, "Please select a customer.");
        }
    }
}

     public void handleNavigation(String action) {
    switch (action) {
        case "Category" -> {
            Category view = new Category();
            new CategoryController(view).open(); 
            this.view.dispose(); 
        }
        default -> JOptionPane.showMessageDialog(null, 
            "Action not supported: " + action, 
            "Error", 
            JOptionPane.ERROR_MESSAGE
        );
    }
}
}
