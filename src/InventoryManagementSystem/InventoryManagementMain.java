/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package InventoryManagementSystem;

import InventoryManagementSystem.DAO.CategoryDao;
import InventoryManagementSystem.controller.AdminPanelController;
import InventoryManagementSystem.controller.CategoryController;
import InventoryManagementSystem.controller.CustomerPanelController;
import InventoryManagementSystem.controller.CustomerchooserController;
import InventoryManagementSystem.controller.LoginController;
import InventoryManagementSystem.controller.MainPageController;
import InventoryManagementSystem.controller.ProductAndCartController;
import InventoryManagementSystem.controller.ProductPanelController;
import InventoryManagementSystem.controller.ViewOrdersController;
import InventoryManagementSystem.dao.DatabaseSetupDao;
import InventoryManagementSystem.database.MySqlConnection;
import InventoryManagementSystem.view.AdminPanel;
//import InventoryManagementSystem.view.AdminPanel;
import InventoryManagementSystem.view.Category;
import InventoryManagementSystem.view.CustomerPanel;
import InventoryManagementSystem.view.Customerchooser;
import InventoryManagementSystem.view.LoginPanel;
import InventoryManagementSystem.view.MainPage;
import InventoryManagementSystem.view.ProductPanel;
import InventoryManagementSystem.view.ProductandCart;
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
          MySqlConnection dbConnection = new MySqlConnection();

        // Create database if it doesn't exist before opening tables etc.
        dbConnection.createDatabaseIfNotExists();
        
        DatabaseSetupDao setupDao = new DatabaseSetupDao();
    setupDao.createAllTablesIfNotExist();
//        AdminPanel view = new AdminPanel();
//        AdminPanelController controller= new AdminPanelController(view);
//        controller.open();
                LoginPanel view=new LoginPanel();
                LoginController LoginOpener= new LoginController(view);
                 LoginOpener.open();
//                 CustomerPanel view=new CustomerPanel();
//                 CustomerPanelController customerP=new CustomerPanelController(view);
//                 
//                 customerP.open();
//         Customerchooser view = new Customerchooser();
//         CustomerchooserController controller= new CustomerchooserController(view);
//         controller.open();

//         MainPage view=new MainPage();
//         MainPageController mainPageOpener= new MainPageController(view);
//         mainPageOpener.open();

// ProductPanel view = new ProductPanel();
//        ProductPanelController controller = new ProductPanelController(view);
//        controller.show();

//         ViewOrders view = new ViewOrders();
//         ViewOrdersController controller= new ViewOrdersController(view);
//         controller.open();
//         
//        ProductandCart view = new ProductandCart();
//        ProductAndCartController controller= new ProductAndCartController(view);
//        controller.open();
        
//           Category view = new Category();
//CategoryController controller = new CategoryController(view);
//controller.open();
    }
    
}
