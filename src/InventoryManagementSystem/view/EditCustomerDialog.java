package InventoryManagementSystem.view;

import javax.swing.*;
import java.awt.*;

public class EditCustomerDialog extends JDialog {
    private JTextField txtName;
    private JTextField txtMobile;
    private JTextField txtEmail;
    private JButton btnSave;

    public EditCustomerDialog(JFrame parent) {
        super(parent, "Edit Customer", true);
        setSize(350, 250);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel("Name:"));
        txtName = new JTextField();
        add(txtName);

        add(new JLabel("Mobile:"));
        txtMobile = new JTextField();
        add(txtMobile);

        add(new JLabel("Email:"));
        txtEmail = new JTextField();
        add(txtEmail);

        btnSave = new JButton("Save");
        add(new JLabel()); // Empty cell
        add(btnSave);
    }

    // Getters
    public JTextField getTxtName() { return txtName; }
    public JTextField getTxtMobile() { return txtMobile; }
    public JTextField getTxtEmail() { return txtEmail; }
    public JButton getBtnSave() { return btnSave; }
}
