package InventoryManagementSystem.DAO.test;

import InventoryManagementSystem.DAO.CategoryDao;
import InventoryManagementSystem.model.CategoryModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CategoryDaoTest {

    private CategoryDao dao;

    @Before
    public void setUp() {
        dao = new CategoryDao();
    }

    @Test
     public void testAddCategory() {
        CategoryModel category = new CategoryModel(0, "TestCategory");
        boolean added = dao.addCategory(category);
        Assert.assertTrue("Category should be added successfully", added);
    }

//    @Test
//    public void testGetAllCategories() {
//        List<CategoryModel> categories = dao.getAllCategories();
//        Assert.assertNotNull("Category list should not be null", categories);
//        Assert.assertTrue("Category list size should be >= 0", categories.size() >= 0);
//    }
//
//    @Test
//    public void testUpdateCategoryName() {
//        // Add a category first
//        CategoryModel category = new CategoryModel(0, "OldName");
//        boolean added = dao.addCategory(category);
//        Assert.assertTrue("Category should be added before update", added);
//
//        // Fetch the category to get its ID
//        List<CategoryModel> categories = dao.getAllCategories();
//        int id = categories.stream()
//                .filter(c -> "OldName".equals(c.getName()))
//                .findFirst()
//                .map(CategoryModel::getId)
//                .orElse(-1);
//
//        Assert.assertTrue("Category ID should be valid", id != -1);
//
//        // Update the name
//        boolean updated = dao.updateCategoryName(id, "NewName");
//        Assert.assertTrue("Category name should be updated successfully", updated);
//
//        // Verify update
//        categories = dao.getAllCategories();
//        boolean foundNewName = categories.stream()
//                .anyMatch(c -> "NewName".equals(c.getName()));
//        Assert.assertTrue("Updated category name should be found", foundNewName);
//    }
//
//    @Test
//    public void testDeleteCategory() {
//        // Add a category first
//        CategoryModel category = new CategoryModel(0, "ToBeDeleted");
//        boolean added = dao.addCategory(category);
//        Assert.assertTrue("Category should be added before delete", added);
//
//        // Fetch the category to get its ID
//        List<CategoryModel> categories = dao.getAllCategories();
//        int id = categories.stream()
//                .filter(c -> "ToBeDeleted".equals(c.getName()))
//                .findFirst()
//                .map(CategoryModel::getId)
//                .orElse(-1);
//
//        Assert.assertTrue("Category ID should be valid", id != -1);
//
//        // Delete the category
//        boolean deleted = dao.deleteCategory(id);
//        Assert.assertTrue("Category should be deleted successfully", deleted);
//
//        // Verify deletion
//        categories = dao.getAllCategories();
//        boolean foundDeleted = categories.stream()
//                .anyMatch(c -> "ToBeDeleted".equals(c.getName()));
//        Assert.assertFalse("Deleted category should not be found", foundDeleted);
//    }
//}
