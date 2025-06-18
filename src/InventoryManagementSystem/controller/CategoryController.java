package InventoryManagementSystem.controller;
 
import InventoryManagementSystem.DAO.CategoryDao;
import InventoryManagementSystem.model.CategoryModel;
import InventoryManagementSystem.view.Category;
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
}
