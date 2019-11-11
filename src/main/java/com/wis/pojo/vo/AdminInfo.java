package com.wis.pojo.vo;

public class AdminInfo {

    private int id;
    private String username;
    private String adminName;
    private String telephone;

    public void setId(int id) {
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getAdminName() {
        return adminName;
    }

    public String getTelephone() {
        return telephone;
    }
}
