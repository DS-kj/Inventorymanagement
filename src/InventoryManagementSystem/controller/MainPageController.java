package InventoryManagementSystem.controller;

import InventoryManagementSystem.view.Category;
import InventoryManagementSystem.view.CustomerPanel;
import InventoryManagementSystem.view.Customerchooser;
import InventoryManagementSystem.view.Dashboard;
import InventoryManagementSystem.view.MainPage;
import InventoryManagementSystem.view.ProductPanel;
import InventoryManagementSystem.view.ViewOrders;
//import InventoryManagementSystem.view.HoverPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainPageController {

    private MainPage view;

    public MainPageController(MainPage view) {
        this.view = view;

        // Attach click listener only
        this.view.setDashboardMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Dashboard().setVisible(true);
                System.out.println("Dashboard clicked!");
                
            }
        });
        this.view.addProductListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 ProductPanel view = new ProductPanel();
        ProductPanelController controller = new ProductPanelController(view);
        controller.show();
                System.out.println("Product clicked!");
            }
        });

        // Category click
        this.view.addCategoryListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Category viewCategory = new Category();
CategoryController controllerCategory = new CategoryController(viewCategory);
controllerCategory.open();
                System.out.println("Category clicked!");
            }
        });

        // Customer click
        this.view.addCustomerListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                new Customer().setVisible(true);
   CustomerPanel viewCustomerP=new CustomerPanel();
                 CustomerPanelController customerP=new CustomerPanelController(viewCustomerP);
                 
                 customerP.open();
                System.out.println("Customer clicked!");
            }
        });

        // Order click
        this.view.addOrderListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 Customerchooser viewCustomer = new Customerchooser();
         CustomerchooserController controllerCustomer= new CustomerchooserController(viewCustomer);
         controllerCustomer.open();
                System.out.println("Order clicked!");
            }
        });

        // History click
        this.view.addHistoryListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 ViewOrders viewOrder = new ViewOrders();
         ViewOrdersController controllerOrder= new ViewOrdersController(viewOrder);
         controllerOrder.open();
                System.out.println("History clicked!");
            }
        });
          this.view.addLogoutListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                  int response = javax.swing.JOptionPane.showConfirmDialog(
            view,
            "Are you sure you want to log out?",
            "Confirm Logout",
            javax.swing.JOptionPane.YES_NO_OPTION,
            javax.swing.JOptionPane.QUESTION_MESSAGE
        );

        if (response == javax.swing.JOptionPane.YES_OPTION) {
            view.dispose();
            System.out.println("User logged out.");
        } else {
            System.out.println("Logout cancelled.");
        }
    
            }
        });
    
    }
    public void open(){
    view.setVisible(true);
}
}
