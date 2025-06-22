package InventoryManagementSystem.controller;

import InventoryManagementSystem.dao.CustomerDao;
import InventoryManagementSystem.model.CustomerModel;
import InventoryManagementSystem.view.*;
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
     private class DashboardListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           new Dashboard().setVisible(true);
                System.out.println("Dashboard clicked!");
                view.dispose();
        }
    }
     private class CategoryListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Category viewCategory = new Category();
            CategoryController controllerCategory = new CategoryController(viewCategory);
            controllerCategory.open();
                System.out.println("Category clicked!");
                view.dispose();
        }
    }
    private class ProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ProductPanel prodView = new ProductPanel();
        ProductPanelController controller = new ProductPanelController(prodView);
        controller.show();
                System.out.println("Product clicked!");
                view.dispose();
        }
    }
     private class CustomerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CustomerPanel viewCustomerP=new CustomerPanel();
                 CustomerPanelController customerP=new CustomerPanelController(viewCustomerP);
                 customerP.open();
                System.out.println("Customer clicked!");
                view.dispose();
        }
    }
     private class OrderListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Customerchooser chooser = new Customerchooser();
            new CustomerchooserController(chooser).open();
            view.dispose();
        }
    }
     private class ViewOrderListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           ViewOrders viewOrder = new ViewOrders();
         ViewOrdersController controllerOrder= new ViewOrdersController(viewOrder);
         controllerOrder.open();
                System.out.println("History clicked!");
                view.dispose();
        }
    }
     private class MainMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            MainPage mainView=new MainPage();
         MainPageController mainPageOpener= new MainPageController(mainView);
         mainPageOpener.open();
                System.out.println("Main Menu clicked!");
                view.dispose();
        }
    }
}
