package InventoryManagementSystem.controller;

import InventoryManagementSystem.DAO.ProductPanelDao;
import InventoryManagementSystem.model.ProductPanelModel;
import InventoryManagementSystem.view.ProductPanel;

import java.util.List;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Optional; // For Optional return type from DAO search
import java.math.BigDecimal; // For price handling

public class ProductPanelController {
    private ProductPanel view;
    private ProductPanelDao dao; // Single DAO instance

    public ProductPanelController(ProductPanel view) {
        this.view = view;
        this.dao = new ProductPanelDao(); // Initialize DAO here

        // Attach listeners from the view to controller's inner classes
        this.view.addProductListener(new AddProductListener());
        this.view.addDeleteListener(new DeleteProductListener());
        this.view.addProductNameSearchListener(new ProductNameSearchListener()); // NEW: Listener for search field
    }

    public void open() {
        refreshTable(); // Populate table when view opens
        view.setVisible(true);
    }

    /**
     * Refreshes the product table data from the database.
     */
    private void refreshTable() {
        List<ProductPanelModel> list = dao.getAllProducts();
        view.setProductTableData(list);
    }

    /**
     * ActionListener for the "Add" button.
     * Handles adding a new product to the database.
     */
    class AddProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String name = view.getProductName().trim();
                String category = view.getCategory().trim();
                int quantity = Integer.parseInt(view.getQuantity().trim());
                double price = Double.parseDouble(view.getPrice().trim());

                // Basic validation
                if (name.isEmpty() || name.equals("Product Name") ||
                    category.isEmpty() || category.equals("Category") ||
                    quantity <= 0 || price <= 0) {
                    JOptionPane.showMessageDialog(view, "Please fill all fields with valid data.", "Input Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                ProductPanelModel product = new ProductPanelModel(name, category, quantity, price);
                
                if (dao.addProduct(product)) {
                    JOptionPane.showMessageDialog(view, "Product added successfully.");
                    refreshTable(); // Update table after adding
                    view.clearFields(); // Clear input fields
                } else {
                    JOptionPane.showMessageDialog(view, "Failed to add product. Make sure category exists.", "Database Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Invalid number format for quantity or price. Please enter numeric values.", "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "An unexpected error occurred while adding product: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }

    /**
     * ActionListener for the "Delete" button.
     * Handles deleting a selected product from the database.
     */
    class DeleteProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int row = view.getSelectedRow();
            if (row >= 0) {
                int id = view.getProductIdAt(row);
                int confirm = JOptionPane.showConfirmDialog(view, "Are you sure you want to delete the selected product?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (dao.deleteProduct(id)) {
                        JOptionPane.showMessageDialog(view, "Product deleted successfully.");
                        refreshTable(); // Update table after deletion
                        view.clearFields(); // Clear input fields as the product is gone
                    } else {
                        JOptionPane.showMessageDialog(view, "Failed to delete product.", "Database Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(view, "Please select a product from the table to delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    /**
     * ActionListener for the Product Name text field.
     * Triggers a search for a product by name when Enter is pressed.
     */
    class ProductNameSearchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String productName = view.getProductName().trim(); // Get product name from the view

            if (productName.isEmpty() || productName.equals("Product Name")) { // Check for empty or placeholder
                // If the field is empty or just the placeholder, clear other fields and return
                view.clearFields(); 
                return;
            }

            // Use SwingWorker to perform DB operation in a background thread
            // to keep the UI responsive.
            new SwingWorker<Optional<ProductPanelModel>, Void>() {
                @Override
                protected Optional<ProductPanelModel> doInBackground() throws Exception {
                    // This code runs on a separate background thread
                    return dao.getProductByName(productName);
                }

                @Override
                protected void done() {
                    // This code runs on the Event Dispatch Thread (EDT) after doInBackground completes
                    try {
                        Optional<ProductPanelModel> optionalProduct = get(); // Get the result from doInBackground
                        if (optionalProduct.isPresent()) {
                            ProductPanelModel product = optionalProduct.get();
                            // Update the view's fields with the found product's details
                            view.setCategoryTextField(product.getCategory());
                            view.setQuantityTextField(product.getQuantity());
                            view.setPriceTextField(product.getPrice());
                        } else {
                            // Product not found, clear relevant fields and inform the user
                            view.setCategoryTextField("Not Found");
                            view.setQuantityTextField(0); // Set to 0 or clear for numerical fields
                            view.setPriceTextField(BigDecimal.ZERO); // Set to 0 for numerical fields
                            JOptionPane.showMessageDialog(view,
                                    "Product '" + productName + "' not found in the database.",
                                    "Product Not Found",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (Exception ex) {
                        // Handle exceptions that might occur during the SwingWorker's execution
                        JOptionPane.showMessageDialog(view,
                                "An error occurred during product search: " + ex.getMessage(),
                                "Search Error",
                                JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                        // Clear fields in case of an error to indicate an invalid state
                        view.setCategoryTextField("Error");
                        view.setQuantityTextField(0);
                        view.setPriceTextField(BigDecimal.ZERO);
                    }
                }
            }.execute(); // Execute the SwingWorker
        }
    }
}