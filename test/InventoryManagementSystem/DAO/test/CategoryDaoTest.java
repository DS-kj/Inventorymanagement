package InventoryManagementSystem.DAO.test;

import InventoryManagementSystem.DAO.CategoryDao;
import InventoryManagementSystem.model.CategoryModel;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CategoryDaoTest {

    String testCategoryName = "Test Category55";

    @Test
    public void addNewCategory() {
        CategoryModel category = new CategoryModel(0, testCategoryName);
        CategoryDao dao = new CategoryDao();
        boolean result = dao.addCategory(category);
        Assert.assertTrue("Category should be added successfully", result);
    }

    @Test
    public void addDuplicateCategory() {
        CategoryModel category = new CategoryModel(0, testCategoryName);
        CategoryDao dao = new CategoryDao();
        boolean result = dao.addCategory(category);
        Assert.assertFalse("Duplicate category should not be added", result);
    }

    @Test
    public void getAllCategories() {
        CategoryDao dao = new CategoryDao();
        List<CategoryModel> categories = dao.getAllCategories();
        Assert.assertNotNull("Category list should not be null", categories);
        Assert.assertTrue("There should be at least one category", categories.size() > 0);
    }

    @Test
    public void updateCategoryName() {
        CategoryDao dao = new CategoryDao();
        List<CategoryModel> categories = dao.getAllCategories();
        int idToUpdate = categories.get(0).getId();
        String newName = "Updated Category";
        boolean result = dao.updateCategoryName(idToUpdate, newName);
        Assert.assertTrue("Category name should be updated", result);
    }

    @Test
    public void deleteCategoryById() {
        CategoryDao dao = new CategoryDao();
        List<CategoryModel> categories = dao.getAllCategories();
        int lastId = categories.get(categories.size() - 1).getId();
        boolean result = dao.deleteCategory(lastId);
        Assert.assertTrue("Category should be deleted", result);
    }
}
