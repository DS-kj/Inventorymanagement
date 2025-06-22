package InventoryManagementSystem.view;

import javax.swing.*;
import java.awt.*;

public class EditProductDialog extends JDialog {
    private JTextField txtName;
    private JTextField txtQuantity;
    private JTextField txtPrice;
    private JLabel lblCategory;
    private JButton btnSave;

    public EditProductDialog(JFrame parent) {
        super(parent, "Edit Product", true);
        setSize(400, 250);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("Product Name:"));
        txtName = new JTextField();
        add(txtName);

        add(new JLabel("Quantity:"));
        txtQuantity = new JTextField();
        add(txtQuantity);

        add(new JLabel("Price:"));
        txtPrice = new JTextField();
        add(txtPrice);

        add(new JLabel("Category:")); // Readonly category display
        lblCategory = new JLabel();
        add(lblCategory);

        btnSave = new JButton("Save");
        add(new JLabel());
        add(btnSave);
    }

    public JTextField getTxtName() { return txtName; }
    public JTextField getTxtQuantity() { return txtQuantity; }
    public JTextField getTxtPrice() { return txtPrice; }
    public JLabel getLblCategory() { return lblCategory; }
    public JButton getBtnSave() { return btnSave; }
}
