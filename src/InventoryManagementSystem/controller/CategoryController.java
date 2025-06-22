package InventoryManagementSystem.controller;
 
import InventoryManagementSystem.DAO.CategoryDao;
import InventoryManagementSystem.model.CategoryModel;
import InventoryManagementSystem.view.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
 
public class CategoryController {
    Category view;
 
    public CategoryController(Category view) {
        this.view = view;
        this.view.createCategory(new CreateCategoryListener());
        this.view.addDeleteUserListener(new DeleteUserListener());
        this.view.addEditNameListener(new EditCategoryListener());
        view.dashboard(new DashboardListener());
        view.category(new CategoryListener());
        view.customer(new CustomerListener());
        view.order(new OrderListener());
        view.viewOrder(new ViewOrderListener());
        view.product(new ProductListener());
        view.goBackMainMenu(new MainMenuListener());
    }
 
    public void open() {
        refreshCategoryTable();
        view.setVisible(true);
    }
 
    private void refreshCategoryTable() {
        CategoryDao dao = new CategoryDao();
        List<CategoryModel> categories = dao.getAllCategories();
        view.setCategoryTableData(categories);
    }
 
    public void close() {
        view.dispose();
    }
 
    class CreateCategoryListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = view.getInsertField().getText();
 
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please enter a category name.");
                return;
            }
 
            CategoryModel category = new CategoryModel(name);
            CategoryDao dao = new CategoryDao();
 
            if (dao.addCategory(category)) {
                JOptionPane.showMessageDialog(view, "Category added successfully.");
                refreshCategoryTable();
                view.getInsertField().setText("");
            } else {
                JOptionPane.showMessageDialog(view, "Failed to add category.");
            }
        }
    }
    class DeleteUserListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
        int selectedRow = view.getUserTable().getSelectedRow();
        if (selectedRow != -1) {
            int categoryID = (int) view.getUserTable().getValueAt(selectedRow, 0); // assuming ID is in column 0
            int confirm = JOptionPane.showConfirmDialog(view,
                    "Are you sure you want to delete this category?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                CategoryDao dao = new CategoryDao();
                if (dao.deleteCategory(categoryID)) {
                    refreshCategoryTable(); // Refresh table after deletion
                    JOptionPane.showMessageDialog(view, "Category deleted successfully.");
                } else {
                    JOptionPane.showMessageDialog(view, "Failed to delete category.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(view, "Please select a category to delete.");
        }
    }        
    }
    class EditCategoryListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRow = view.getUserTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Please select a category to edit.");
            return;
        }
        int categoryId = (int) view.getUserTable().getValueAt(selectedRow, 0);
        String oldName = (String) view.getUserTable().getValueAt(selectedRow, 1);

        String newName = JOptionPane.showInputDialog(view, "Edit category name:", oldName);
        if (newName == null) return; // User cancelled
        newName = newName.trim();
        if (newName.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Category name cannot be empty.");
            return;
        }
        CategoryDao dao = new CategoryDao();
        boolean updated = dao.updateCategoryName(categoryId, newName);
        if (updated) {
            JOptionPane.showMessageDialog(view, "Category name updated successfully.");
            refreshCategoryTable();
        } else {
            JOptionPane.showMessageDialog(view, "Failed to update category name.");
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
}
