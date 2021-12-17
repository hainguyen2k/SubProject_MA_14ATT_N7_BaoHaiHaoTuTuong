package com.example.subproject_ma_14att_n7_baohaihaotutuong.model;

public class User {
    private String email;
    private String password;
    private String token;
    private String username;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }
}
