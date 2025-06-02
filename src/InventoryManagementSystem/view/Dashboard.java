package InventoryManagementSystem.view;

import Inventory.DAO.ProductDao;
import Inventory.model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class Dashboard extends JFrame {

    private final JTable table;
    private final DefaultTableModel tableModel;

    public Dashboard() {
        setTitle("Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(10, 50, 80));
        topPanel.setPreferredSize(new Dimension(800, 40));

        JLabel dashboardLabel = new JLabel("  Dashboard");
        dashboardLabel.setForeground(Color.WHITE);
        dashboardLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        topPanel.add(dashboardLabel, BorderLayout.WEST);

        JTextField searchField = new JTextField("Search");
        searchField.setPreferredSize(new Dimension(150, 25));
        topPanel.add(searchField, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(new Color(0, 45, 70));
        menuPanel.setLayout(new GridLayout(7, 1, 5, 5));
        menuPanel.setPreferredSize(new Dimension(150, 0));

        String[] buttons = {
            "Dashboard", "Category", "Product",
            "Customer", "Order", "View Order", "Log Out"
        };

        for (String btn : buttons) {
            JButton button = new JButton(btn);
            menuPanel.add(button);
        }

        add(menuPanel, BorderLayout.WEST);

        // Table setup
        String[] columnNames = {"ID", "Product Name", "Category", "Quantity", "Price (per)", "Rate"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(table);
        add(tableScroll, BorderLayout.CENTER);

        loadProducts(); // Load data from DB into table
    }

    private void loadProducts() {
        ProductDao productDao = new ProductDao();
        List<Product> products = productDao.getAllProducts();

        // Clear existing rows
        tableModel.setRowCount(0);

        // Add products to table
        for (Product p : products) {
            Object[] row = {
                p.getId(),
                p.getName(),
                p.getCategory(),
                p.getQuantity(),
                p.getPrice(),
                p.getRate()
            };
            tableModel.addRow(row);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Dashboard().setVisible(true);
        });
    }
}
