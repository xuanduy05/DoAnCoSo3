package com.example.doancoso3.model;

import java.util.Date;

public class User {
//    private int id,user_id;
//    private String id,name,Ho,Ten,email,password,SDT,gioitinh,diachi,cmnd,ngay_vao_lam,user_id,Password_app;
//    private Date ngay_vao_lam;
    private int id,user_id;
    private String name,Ho,Ten,email,password,SDT,gioitinh,diachi,cmnd,Password_app;
    private Date ngay_vao_lam;

    public User() {

    }

//    public User(int id, int user_id, String name, String ho, String ten, String email, String password, String SDT, String gioitinh, String diachi, String cmnd, Date ngay_vao_lam) {
//        this.id = id;
//        this.user_id = user_id;
//        this.name = name;
//        Ho = ho;
//        Ten = ten;
//        this.email = email;
//        this.password = password;
//        this.SDT = SDT;
//        this.gioitinh = gioitinh;
//        this.diachi = diachi;
//        this.cmnd = cmnd;
//        this.ngay_vao_lam = ngay_vao_lam;
//    }


    public User(int id, String name, String ho, String ten, String email, String password, String SDT, String gioitinh, String diachi, String cmnd, Date ngay_vao_lam, int user_id, String password_app) {
        this.id = id;
        this.name = name;
        Ho = ho;
        Ten = ten;
        this.email = email;
        this.password = password;
        this.SDT = SDT;
        this.gioitinh = gioitinh;
        this.diachi = diachi;
        this.cmnd = cmnd;
        this.ngay_vao_lam = ngay_vao_lam;
        this.user_id = user_id;
        Password_app = password_app;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHo() {
        return Ho;
    }

    public void setHo(String ho) {
        Ho = ho;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getPassword_app() {
        return Password_app;
    }

    public void setPassword_app(String password_app) {
        Password_app = password_app;
    }

    public Date getNgay_vao_lam() {
        return ngay_vao_lam;
    }

    public void setNgay_vao_lam(Date ngay_vao_lam) {
        this.ngay_vao_lam = ngay_vao_lam;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", name='" + name + '\'' +
                ", Ho='" + Ho + '\'' +
                ", Ten='" + Ten + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", SDT='" + SDT + '\'' +
                ", gioitinh='" + gioitinh + '\'' +
                ", diachi='" + diachi + '\'' +
                ", cmnd='" + cmnd + '\'' +
                ", ngay_vao_lam=" + ngay_vao_lam +
                '}';
    }
}
