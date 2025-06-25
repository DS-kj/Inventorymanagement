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
         
   case "Category" ->{Category viewCategory=new Category();
                CategoryController CategoryOpener= new CategoryController(viewCategory);
                 CategoryOpener.open();
                view.dispose();}
 case "Product" -> {
            ProductPanel viewProduct = new ProductPanel();
        ProductPanelController controller = new ProductPanelController(viewProduct);
        controller.show();
        view.dispose();
                System.out.println("Product clicked!");
        }
        case "Customer" -> {
            CustomerPanel viewCustomer = new CustomerPanel();
            CustomerPanelController controller = new CustomerPanelController(viewCustomer);
            controller.open();
            view.dispose();
        }
        case "Order" -> {
            Customerchooser viewCustomer = new Customerchooser();
         CustomerchooserController controllerCustomer= new CustomerchooserController(viewCustomer);
         controllerCustomer.open();
                System.out.println("Order clicked!");
        }
        case "View Order" -> {
            ViewOrders viewOrder = new ViewOrders();
            ViewOrdersController controller = new ViewOrdersController(viewOrder);
            controller.open();
            view.dispose();
        }
            case "Log Out" -> {
                int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
            default -> JOptionPane.showMessageDialog(null,
                    "Already in Dashboard '" + action + "' found.",
                    "Information",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}