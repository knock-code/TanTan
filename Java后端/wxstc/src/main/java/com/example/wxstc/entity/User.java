package com.example.wxstc.entity;


public class User {

    private int user_id;
    private String user_name;
    private String user_icon;
    private String user_password;
    private String user_studentId;
    public User() {
    }

    public User(String user_name, String user_icon, String user_password, String user_studentId) {
        this.user_name = user_name;
        this.user_icon = user_icon;
        this.user_password = user_password;
        this.user_studentId = user_studentId;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_icon() {
        return user_icon;
    }

    public void setUser_icon(String user_icon) {
        this.user_icon = user_icon;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_studentId() {
        return user_studentId;
    }

    public void setUser_studentId(String user_studentId) {
        this.user_studentId = user_studentId;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", user_icon='" + user_icon + '\'' +
                ", user_password='" + user_password + '\'' +
                ", user_studentId='" + user_studentId + '\'' +
                '}';
    }
}
