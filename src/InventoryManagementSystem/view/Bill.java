package InventoryManagementSystem.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Bill {
    public Bill(ProductandCart view, int orderId) {
        JTable cartTable = view.getCartTable();
        DefaultTableModel model = (DefaultTableModel) cartTable.getModel();
        final int rowCount = model.getRowCount();

        JDialog billDialog = new JDialog(view, "Bill", true);
        billDialog.setSize(600, 400);
        billDialog.setLocationRelativeTo(view);
        billDialog.setLayout(new BorderLayout(10, 10));

        // Top Panel for title and info
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Inventory Management System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        topPanel.add(titleLabel, BorderLayout.NORTH);

        // Order info panel (OrderId, Date, Total Paid)
        JPanel infoPanel = new JPanel(new GridLayout(3, 1));
        final String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        double totalPaidTemp = 0.0;
        for (int i = 0; i < rowCount; i++) {
            int qty = Integer.parseInt(model.getValueAt(i, 1).toString());
            double price = Double.parseDouble(model.getValueAt(i, 2).toString());
            totalPaidTemp += qty * price;
        }
        final double totalPaid = totalPaidTemp;

        infoPanel.add(new JLabel("Order ID: " + orderId));
        infoPanel.add(new JLabel("Date: " + currentDate));
        infoPanel.add(new JLabel("Total Paid: Rs. " + String.format("%.2f", totalPaid)));

        topPanel.add(infoPanel, BorderLayout.SOUTH);
        billDialog.add(topPanel, BorderLayout.NORTH);

        // Table for items
        String[] columns = {"Name", "Price", "Quantity", "Sub Total"};
        final Object[][] data = new Object[rowCount][4];

        for (int i = 0; i < rowCount; i++) {
            String name = model.getValueAt(i, 0).toString();
            int qty = Integer.parseInt(model.getValueAt(i, 1).toString());
            double totalPrice = Double.parseDouble(model.getValueAt(i, 2).toString());
            double unitPrice = totalPrice / qty;
            double subTotal = unitPrice * qty;

            data[i][0] = name;
            data[i][1] = String.format("%.2f", unitPrice);
            data[i][2] = qty;
            data[i][3] = String.format("%.2f", subTotal);
        }

        JTable billTable = new JTable(data, columns);
        billTable.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(billTable);
        billDialog.add(scrollPane, BorderLayout.CENTER);

        // Bottom thank you label and download button
        JPanel bottomPanel = new JPanel(new BorderLayout());
        JLabel thankYouLabel = new JLabel("Thank you, Please visit again!", SwingConstants.CENTER);
        thankYouLabel.setFont(new Font("Serif", Font.ITALIC, 16));
        bottomPanel.add(thankYouLabel, BorderLayout.CENTER);

        JButton downloadButton = new JButton("Download");
        bottomPanel.add(downloadButton, BorderLayout.EAST);

        downloadButton.addActionListener((var e) -> {
            try (FileWriter writer = new FileWriter("C:\\Users\\ASUS\\Desktop\\Invent\\Inventorymanagement\\src\\InventoryManagementSystem\\bill.txt")) {
                writer.write("Inventory Management System\n\n");
                writer.write("Order ID: " + orderId + "\n");
                writer.write("Date: " + currentDate + "\n");
                writer.write("Total Paid: Rs. " + String.format("%.2f", totalPaid) + "\n\n");

                writer.write(String.format("%-20s %-10s %-10s %-10s\n", "Name", "Price", "Quantity", "Sub Total"));
                writer.write("--------------------------------------------------------\n");

                for (int i = 0; i < rowCount; i++) {
                    String name = data[i][0].toString();
                    String price = data[i][1].toString();
                    String qty = data[i][2].toString();
                    String subTotal = data[i][3].toString();
                    writer.write(String.format("%-20s %-10s %-10s %-10s\n", name, price, qty, subTotal));
                }

                writer.write("\nThank you, Please visit again!\n---------------------------------\n");
                JOptionPane.showMessageDialog(view, "Bill downloaded as bill.txt");
                billDialog.dispose();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(view, "Error saving bill: " + ex.getMessage());
            }
        });

        billDialog.add(bottomPanel, BorderLayout.SOUTH);
        billDialog.setVisible(true);
    }
}
