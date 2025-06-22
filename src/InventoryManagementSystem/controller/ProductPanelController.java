package InventoryManagementSystem.controller;

//import InventoryManagementSystem.dao.ProductDao;
import InventoryManagementSystem.DAO.CategoryDao;
import InventoryManagementSystem.dao.ProductPanelDao;
import InventoryManagementSystem.model.ProductModel;
import InventoryManagementSystem.model.CategoryModel;
import InventoryManagementSystem.view.Category;
import InventoryManagementSystem.view.CustomerPanel;
import InventoryManagementSystem.view.Customerchooser;
import InventoryManagementSystem.view.Dashboard;
import InventoryManagementSystem.view.MainPage;
import InventoryManagementSystem.view.ProductPanel;
import InventoryManagementSystem.view.ViewOrders;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ProductPanelController {
    private ProductPanel view;
    private ProductPanelDao dao;
    private CategoryDao categoryDao;


    public ProductPanelController(ProductPanel view) {
        this.view = view;
        this.dao = new ProductPanelDao();
        this.categoryDao = new CategoryDao();
        populateCategoryDropdown();
        initController();
        loadProductsToTable();
        view.dashboard(new DashboardListener());
        view.category(new CategoryListener());
        view.customer(new CustomerListener());
        view.order(new OrderListener());
        view.viewOrder(new ViewOrderListener());
        view.product(new ProductListener());
        view.goBackMainMenu(new MainMenuListener());
        view.logOut(new LogOutListener());
    }
     public void show() {
        view.setVisible(true);
    }

    private void initController() {
        view.getAddButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertProduct();
            }
        });
    }
     private void populateCategoryDropdown() {
        List<CategoryModel> categories = categoryDao.getAllCategories();
        JComboBox comboBox = view.getCategoryChooser();
        comboBox.removeAllItems();

        for (CategoryModel category : categories) {
            comboBox.addItem(category); 
        }
    }

    private void insertProduct() {
        try {
            String name = view.getProductNameField().getText();
            int quantity = Integer.parseInt(view.getQuantityField().getText());
            double price = Double.parseDouble(view.getPriceField().getText());
            CategoryModel selectedCategory = (CategoryModel) view.getCategoryChooser().getSelectedItem();

            if (selectedCategory == null) {
                System.out.println("No category selected.");
                return;
            }

            ProductModel product = new ProductModel(name, selectedCategory, quantity, price);
            boolean success = dao.insertProduct(product);

            if (success) {
                JOptionPane.showMessageDialog(view, "Category added successfully.");
                loadProductsToTable();
                
            } else {
                System.out.println("Failed to insert product.");
            }

        } catch (NumberFormatException ex) {
            System.out.println("Invalid number format.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void loadProductsToTable() {
        List<ProductModel> products = dao.getAllProducts();
        DefaultTableModel model = (DefaultTableModel) view.getProductTable().getModel();
        model.setRowCount(0); // Clear table first

        for (ProductModel product : products) {
            Object[] row = new Object[]{
                    product.getId(),
                    product.getName(),
                    product.getCategory().getName(),
                    product.getQuantity(),
                    product.getPrice()
            };
            model.addRow(row);
        }
    }

    void open() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
     private class LogOutListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int response = javax.swing.JOptionPane.showConfirmDialog(
            view,
            "Are you sure you want to log out?",
            "Confirm Logout",
            javax.swing.JOptionPane.YES_NO_OPTION,
            javax.swing.JOptionPane.QUESTION_MESSAGE
        );

        if (response == javax.swing.JOptionPane.YES_OPTION) {
            view.dispose();
            System.out.println("User logged out.");
        } else {
            System.out.println("Logout cancelled.");
        }
    
            }
        }
}
