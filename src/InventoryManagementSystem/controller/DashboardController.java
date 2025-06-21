package InventoryManagementSystem.controller;

import InventoryManagementSystem.DAO.DashboardDao;
import InventoryManagementSystem.model.DashboardModel;
import InventoryManagementSystem.view.Dashboard;

import java.util.List;

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
                p.getRate()
            };
            view.addRowToTable(row);
        }
    }

    public void handleNavigation(String action) {
        // Here you can call a NavigationManager or switch like before to open other modules
        switch (action) {
            case "Dashboard" -> {
                // Already in dashboard, maybe refresh or do nothing
                loadProducts();
            }
            case "Category" -> {
                // Open Category view/controller
                // ...
            }
            case "Product" -> {
                // Open Product view/controller
                // ...
            }
            case "Customer" -> {
                // Open Customer view/controller
                // ...
            }
            case "Order" -> {
                // Open Order view/controller
                // ...
            }
            case "View Order" -> {
                // Open View Order view/controller
                // ...
            }
            case "Log Out" -> {
                System.exit(0);
            }
            default -> System.out.println("No handler for " + action);
        }
    }
}
