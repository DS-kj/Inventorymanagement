package InventoryManagementSystem.controller;

import InventoryManagementSystem.DAO.ProductPanelDao;

import InventoryManagementSystem.model.ProductPanelModel;

import InventoryManagementSystem.view.ProductPanel;
import java.util.List;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ProductPanelController {
    private ProductPanel view;

    public ProductPanelController(ProductPanel view) {
        this.view = view;
        this.view.addProductListener(new AddProductListener());
        this.view.addDeleteListener(new DeleteProductListener());
        
    }

    public void open() {
        refreshTable();
        view.setVisible(true);
    }

    private void refreshTable() {
        ProductPanelDao dao = new ProductPanelDao();
        List<ProductPanelModel> list = dao.getAllProducts();
        view.setProductTableData(list);
    }

    class AddProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String name = view.getProductName().trim();
                String category = view.getCategory().trim();
                int quantity = Integer.parseInt(view.getQuantity().trim());
                double price = Double.parseDouble(view.getPrice().trim());

                ProductPanelModel product = new ProductPanelModel(name, category, quantity, price);
                ProductPanelDao dao = new ProductPanelDao();

                if (dao.addProduct(product)) {
                    JOptionPane.showMessageDialog(view, "Product added successfully.");
                    refreshTable();
                    view.clearFields();
                } else {
                    JOptionPane.showMessageDialog(view, "Failed to add product.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Invalid input.");
            }
        }
    }

    class DeleteProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int row = view.getSelectedRow();
            if (row >= 0) {
                int id = view.getProductIdAt(row);
                int confirm = JOptionPane.showConfirmDialog(view, "Are you sure to delete?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    ProductPanelDao dao = new ProductPanelDao();
                    if (dao.deleteProduct(id)) {
                        JOptionPane.showMessageDialog(view, "Product deleted.");
                        refreshTable();
                    } else {
                        JOptionPane.showMessageDialog(view, "Failed to delete product.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(view, "Please select a product.");
            }
        }}
        
        
   
        }
    
        
    



    
    




            
        
    

