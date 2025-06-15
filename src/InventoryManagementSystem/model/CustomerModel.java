package InventoryManagementSystem.model;

public class CustomerModel {
    private int id;
    private String name;
    private String mobile;
    private String email;

    public CustomerModel(int id, String name, String mobile, String email) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getMobile() { return mobile; }
    public String getEmail() { return email; }
}
