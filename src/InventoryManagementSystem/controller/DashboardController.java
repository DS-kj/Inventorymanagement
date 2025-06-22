package InventoryManagementSystem.controller;

import InventoryManagementSystem.DAO.DashboardDao;
import InventoryManagementSystem.model.DashboardModel;
import InventoryManagementSystem.view.Category;
import InventoryManagementSystem.view.CustomerPanel;
import InventoryManagementSystem.view.Customerchooser;
import InventoryManagementSystem.view.Dashboard;
import InventoryManagementSystem.view.OrderList;
import InventoryManagementSystem.view.ProductPanel;
import InventoryManagementSystem.view.ViewOrders;

import java.util.List;
import javax.swing.JOptionPane;

public class DashboardController {

    private final Dashboard view;
    private final DashboardDao dao;

    public DashboardController(Dashboard view) {
        this.view = view;
        this.dao = new DashboardDao();
    }

    public void loadProducts() {
        List<DashboardModel> products = dao.getAllProducts();
        refreshTable(products);
    }

    public void loadProducts(String keyword) {
        List<DashboardModel> products = dao.searchProducts(keyword);
        refreshTable(products);
    }

    private void refreshTable(List<DashboardModel> products) {
        view.clearTable();
        for (DashboardModel p : products) {
            Object[] row = {
                p.getId(),
                p.getProductName(),
                p.getCategory(),
                p.getQuantity(),
                p.getPrice(),
            };
            view.addRowToTable(row);
        }
    }
    public void handleNavigation(String action) {
    switch (action) {
        case "Dashboard" -> {
            loadProducts();
            System.out.println("Dashboard refreshed!");
        }
        case "Category" -> {
            view.dispose(); 
            Category categoryView = new Category();
            CategoryController categoryOpener = new CategoryController(categoryView);
            categoryOpener.open();
        }
        case "Product" -> {
            view.dispose(); 
            ProductPanel productView = new ProductPanel();
            ProductPanelController controller = new ProductPanelController(productView);
            controller.show();
            System.out.println("Product clicked!");
        }
        case "Customer" -> {
            view.dispose();
            CustomerPanel customerView = new CustomerPanel();
            CustomerPanelController controller = new CustomerPanelController(customerView);
            controller.open();
        }
        case "Order" -> {
            view.dispose(); 
            Customerchooser viewCustomer = new Customerchooser();
            CustomerchooserController controllerCustomer = new CustomerchooserController(viewCustomer);
            controllerCustomer.open();
            System.out.println("Order clicked!");
        }
        case "View Order" -> {
            view.dispose();
            ViewOrders viewOrders = new ViewOrders();
            ViewOrdersController controller = new ViewOrdersController(viewOrders);
            controller.open();
        }
        case "Log Out" -> {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
        default -> JOptionPane.showMessageDialog(null,
                "No table named '" + action + "' found.",
                "Information",
                JOptionPane.INFORMATION_MESSAGE);
    }
    
}

}