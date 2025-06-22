package InventoryManagementSystem.view;

import InventoryManagementSystem.controller.DashboardController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Dashboard extends JFrame {

    private final JTable table;
    private final DefaultTableModel tableModel;
    private final JPanel menuPanel;
    private final DashboardController controller;

    private final JTextField searchField;

    public Dashboard() {
        setTitle("Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        controller = new DashboardController(this);

        // Top panel with search
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(10, 50, 80));
        topPanel.setPreferredSize(new Dimension(800, 40));

        JLabel dashboardLabel = new JLabel("  Dashboard");
        dashboardLabel.setForeground(Color.WHITE);
        dashboardLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        topPanel.add(dashboardLabel, BorderLayout.WEST);

        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setPreferredSize(new Dimension(150, 25));
        searchField = new JTextField();
        searchPanel.add(searchField, BorderLayout.CENTER);
        topPanel.add(searchPanel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        // Menu panel
        menuPanel = new JPanel();
        menuPanel.setBackground(new Color(0, 45, 70));
        menuPanel.setLayout(new GridLayout(7, 1, 5, 5));
        menuPanel.setPreferredSize(new Dimension(150, 0));

        String[] buttons = {"Dashboard", "Category", "Product", "Customer", "Order", "View Order", "Log Out"};

        for (String btn : buttons) {
            JButton button = new JButton(btn);
            button.addActionListener(e -> controller.handleNavigation(button.getText()));
            menuPanel.add(button);
        }

        add(menuPanel, BorderLayout.WEST);

        // Table setup
       String[] columnNames = {"ID", "Product Name", "Category", "Quantity", "Price (per)"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override public boolean isCellEditable(int row, int col) { return false; }
        };
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Load products initially
        controller.loadProducts();

        // Search-as-you-type
        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override public void insertUpdate(javax.swing.event.DocumentEvent e) { search(); }
            @Override public void removeUpdate(javax.swing.event.DocumentEvent e) { search(); }
            @Override public void changedUpdate(javax.swing.event.DocumentEvent e) { search(); }

            private void search() {
                String keyword = searchField.getText().trim();
                if (keyword.isEmpty()) {
                    controller.loadProducts();
                } else {
                    controller.loadProducts(keyword);
                }
            }
        });
    }

    // Methods to update table from controller

    public void clearTable() {
        tableModel.setRowCount(0);
    }

    public void addRowToTable(Object[] row) {
        tableModel.addRow(row);
    }
}
