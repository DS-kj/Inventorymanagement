package InventoryManagementSystem.model;

public class AdminPanelModel {
    private String id;
    private String PhoneNumberEntry;
    private String UsernameAdminPanelEntry;
    private String PasswordAdminPanelEntry;

    public AdminPanelModel(String PhoneNumberEntry, String UsernameAdminPanelEntry, String PasswordAdminPanelEntry) {
        this.PhoneNumberEntry = PhoneNumberEntry;
        this.UsernameAdminPanelEntry = UsernameAdminPanelEntry;
        this.PasswordAdminPanelEntry = PasswordAdminPanelEntry;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getPhoneNumberEntry() {
    return PhoneNumberEntry;
    }

    public String getUsernameAdminPanelEntry() {
    return UsernameAdminPanelEntry;
    }

    public String getPasswordAdminPanelEntry() {
    return PasswordAdminPanelEntry;

    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setPhoneNumebrEntry(String PhoneNumberEntry) {
        this.PhoneNumberEntry = PhoneNumberEntry;
    }

    public void setUsernameAdminPanelEntry(String UsernameAdminPanelEntry) {
        this.UsernameAdminPanelEntry = UsernameAdminPanelEntry;
    }

    public void setPasswordAdminPanelEntry(String PasswordAdminPanelEntry) {
        this.PasswordAdminPanelEntry = PasswordAdminPanelEntry;
    }
}
