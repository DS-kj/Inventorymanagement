package InventoryManagementSystem.model;

public class AdminPanelModel {
    private String id;
    private String phoneNumber;
    private String name;
    private String password;

    public AdminPanelModel(String phoneNumber, String name, String password) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.password = password;
    }

    public AdminPanelModel(String id, String phoneNumber, String name, String password) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.password = password;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
