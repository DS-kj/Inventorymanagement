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
}