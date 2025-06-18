
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package InventoryManagementSystem.model;

/**
 *
 * @author nitesskatuwal
 */
public class LoginRequest {
    private String email;
    private String Password;

    public LoginRequest(String email, String Password) {
        this.email = email;
        this.Password = Password;
    }

    public String getUsername() {
        return email;
    }

    public String getPassword() {
        return Password;
}
}