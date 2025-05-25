import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Dashboard extends JFrame {

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

        String[] columnNames = {"ID", "Product Name", "Category", "Quantity", "Price (per)", "Rate"};
        Object[][] data=new Object[20][6];
        data[0][0]="1";
        data[0][1]="Rijin";
        data[0][2]="Food";
        data[0][3]="2";
        data[0][4]="80";
        data[0][5]="160";
        
        JTable table = new JTable(new DefaultTableModel(data, columnNames) {
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
});

        JScrollPane tableScroll = new JScrollPane(table);

        add(tableScroll, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Dashboard().setVisible(true);
        });
    }
}
