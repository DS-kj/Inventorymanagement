package InventoryManagementSystem.controller;

//import InventoryManagementSystem.dao.ProductDao;
import InventoryManagementSystem.DAO.CategoryDao;
import InventoryManagementSystem.dao.ProductPanelDao;
import InventoryManagementSystem.model.ProductModel;
import InventoryManagementSystem.model.CategoryModel;
import InventoryManagementSystem.view.ProductPanel;

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
}
