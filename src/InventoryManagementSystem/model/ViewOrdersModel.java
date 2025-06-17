package InventoryManagementSystem.model;

public class ViewOrdersModel {
    private int id;
    private String name;
    private String mobileNumber;
    private String email;

    public ViewOrdersModel(int id, String name, String mobileNumber, String email) {
        this.id = id;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.email = email;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getMobileNumber() { return mobileNumber; }
    public String getEmail() { return email; }
//    public javax.swing.JTable getCustomertable() {
//    return Customertable;
//}
//
//    public javax.swing.JButton getSelectButton() {
//    return Select;
//}
}
