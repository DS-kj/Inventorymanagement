package InventoryManagementSystem.controller;

import InventoryManagementSystem.DAO.ProductAndCartDao;
import InventoryManagementSystem.model.ProductAndCartModel;
import InventoryManagementSystem.view.ProductandCart;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ProductAndCartController {
    private ProductandCart view;
    private List<ProductAndCartModel> cartList = new ArrayList<>();
private int customerId;
    public ProductAndCartController(ProductandCart view) {
        this.view = view;
        loadProducts();
//        view.addAddToCartListener(new AddToCartListener());
//        view.addSaveOrderListener(new SaveOrderListener());
attachListeners();

    }
     public ProductAndCartController(ProductandCart view, int customerId) {
        this.view = view;
        this.customerId = customerId;
                attachListeners();

        
    }
       public void open(){
   loadProducts();
    view.setVisible(true);
}
   private void attachListeners() {
        view.addAddToCartListener(new AddToCartListener());
        view.addSaveOrderListener(new SaveOrderListener());
        view.addBackButtonListener(new BackButtonListener());

    }
    private void loadProducts() {
        ProductAndCartDao dao = new ProductAndCartDao();
        List<ProductAndCartModel> products = dao.getAllProducts();
        DefaultTableModel model = (DefaultTableModel) view.getProductTable().getModel();
        model.setRowCount(0);

        for (ProductAndCartModel p : products) {
            model.addRow(new Object[]{p.getId(), p.getName(), p.getCategory(), p.getQuantity(), p.getPrice()});
        }
    }

    private class AddToCartListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = view.getProductTable().getSelectedRow();
            if (selectedRow != -1) {
                DefaultTableModel model = (DefaultTableModel) view.getProductTable().getModel();
                int id = (int) model.getValueAt(selectedRow, 0);
                String name = (String) model.getValueAt(selectedRow, 1);
                String category = (String) model.getValueAt(selectedRow, 2);
                int availableQuantity = (int) model.getValueAt(selectedRow, 3);
                double price = (double) model.getValueAt(selectedRow, 4);

                String quantityStr = JOptionPane.showInputDialog(view, "Enter quantity:");
                if (quantityStr != null && !quantityStr.isEmpty()) {
                    int quantity = Integer.parseInt(quantityStr);
                    if (quantity <= 0 || quantity > availableQuantity) {
                        JOptionPane.showMessageDialog(view, "Invalid quantity");
                        return;
                    }
                    ProductAndCartModel cartItem = new ProductAndCartModel(id, name, category, quantity, price);
                    cartList.add(cartItem);

                    DefaultTableModel cartModel = (DefaultTableModel) view.getCartTable().getModel();
                    cartModel.addRow(new Object[]{name, quantity, quantity * price});
                }
            } else {
                JOptionPane.showMessageDialog(view, "Please select a product to add to cart");
            }
        }
    }

    // private class SaveOrderListener implements ActionListener {
    //     @Override
    //     public void actionPerformed(ActionEvent e) {
    //         if (cartList.isEmpty()) {
    //             JOptionPane.showMessageDialog(view, "Cart is empty");
    //             return;
    //         }
    //         ProductAndCartDao dao = new ProductAndCartDao();
    //         if (dao.saveOrder(cartList)) {
    //             JOptionPane.showMessageDialog(view, "Order saved successfully");
    //             cartList.clear();
    //             ((DefaultTableModel) view.getCartTable().getModel()).setRowCount(0);
    //         } else {
    //             JOptionPane.showMessageDialog(view, "Failed to save order");
    //         }
    //     }
    // }
//    private class SaveOrderListener implements ActionListener {
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (cartList.isEmpty()) {
//            JOptionPane.showMessageDialog(view, "Cart is empty");
//            return;
//        }
//        ProductAndCartDao dao = new ProductAndCartDao();
//        if (dao.saveOrder(cartList, customerId)) { 
//            JOptionPane.showMessageDialog(view, "Order saved successfully");
//            cartList.clear();
//            ((DefaultTableModel) view.getCartTable().getModel()).setRowCount(0);
//        } else {
//            JOptionPane.showMessageDialog(view, "Failed to save order");
//        }
//    }
//}

     private class SaveOrderListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (cartList.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Cart is empty. Add products before saving.");
                return;
            }
            ProductAndCartDao dao = new ProductAndCartDao();
            boolean saved = dao.saveOrder(cartList, customerId);  // i added this to pass the selected customerId for database reference

            if (saved) {
                JOptionPane.showMessageDialog(view, "Order saved successfully!");
                cartList.clear();
                ((DefaultTableModel) view.getCartTable().getModel()).setRowCount(0);
                loadProducts();
            } else {
                JOptionPane.showMessageDialog(view, "Failed to save order. Please try again.");
            }
        }
    }
     private class BackButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        InventoryManagementSystem.view.Customerchooser customerView = new InventoryManagementSystem.view.Customerchooser();
        InventoryManagementSystem.controller.CustomerchooserController controller =
                new InventoryManagementSystem.controller.CustomerchooserController(customerView);
        controller.open(); 
        view.dispose();
    }
}

}