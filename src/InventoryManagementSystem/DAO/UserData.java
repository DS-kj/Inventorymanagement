package InventoryManagementSystem.DAO;

public class UserData {
    private String email;
    private String password;

    public UserData(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Optional: Getters
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
