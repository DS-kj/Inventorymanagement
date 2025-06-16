/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package InventoryManagementSystem;

import InventoryManagementSystem.DAO.CategoryDao;
import InventoryManagementSystem.DAO.ViewOrdersDao;
import InventoryManagementSystem.controller.AdminPanelController;
import InventoryManagementSystem.controller.CategoryController;
import InventoryManagementSystem.controller.ViewOrdersController;
import InventoryManagementSystem.view.AdminPanel;
//import InventoryManagementSystem.view.AdminPanel;
import InventoryManagementSystem.view.Category;
import InventoryManagementSystem.view.ViewOrders;


/**
 *
 * @author ACER
 */
public class InventoryManagementMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        AdminPanel view = new AdminPanel();
//        AdminPanelController controller= new AdminPanelController(view);
//        controller.open();
        
//           Category view = new Category();
//CategoryController controller = new CategoryController(view);
//controller.open();
            ViewOrders view = new ViewOrders();
ViewOrdersDao dao = new ViewOrdersDao();
ViewOrdersController controller = new ViewOrdersController(dao, view);
view.setVisible(true);
    }
}
