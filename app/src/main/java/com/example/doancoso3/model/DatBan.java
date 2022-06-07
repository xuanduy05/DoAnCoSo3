package com.example.doancoso3.model;


import android.text.format.Time;

import java.util.Date;


public class DatBan {
    private int id;
    private String HoTen,email,SDT,loai_phong;
    private Date Ngay;
//    private Date Gio;
    private Time Gio;
    private int so_nguoi;
    private int id_phong;
//    private String id,HoTen,email,SDT,Ngay,Gio,so_nguoi,id_phong,loai_phong;

    public DatBan() {

    }

    public DatBan(int id, String hoTen, String email, String SDT, String loai_phong, Date ngay, int so_nguoi, int id_phong) {
        this.id = id;
        HoTen = hoTen;
        this.email = email;
        this.SDT = SDT;
        this.loai_phong = loai_phong;
        Ngay = ngay;
        this.so_nguoi = so_nguoi;
        this.id_phong = id_phong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getLoai_phong() {
        return loai_phong;
    }

    public void setLoai_phong(String loai_phong) {
        this.loai_phong = loai_phong;
    }

    public Date getNgay() {
        return Ngay;
    }

    public void setNgay(Date ngay) {
        Ngay = ngay;
    }

    public Time getGio() {
        return Gio;
    }

    public void setGio(Time gio) {
        Gio = gio;
    }


//    public Date getGio() {
//        return Gio;
//    }
//
//    public void setGio(Date gio) {
//        Gio = gio;
//    }

    public int getSo_nguoi() {
        return so_nguoi;
    }

    public void setSo_nguoi(int so_nguoi) {
        this.so_nguoi = so_nguoi;
    }

    public int getId_phong() {
        return id_phong;
    }

    public void setId_phong(int id_phong) {
        this.id_phong = id_phong;
    }

    @Override
    public String toString() {
        return "DatBan{" +
                "HoTen='" + HoTen + '\'' +
                ", email='" + email + '\'' +
                ", SDT='" + SDT + '\'' +
                ", Ngay='" + Ngay + '\'' +
                ", Gio='" + Gio + '\'' +
                ", so_nguoi='" + so_nguoi + '\'' +
                ", id_phong='" + id_phong + '\'' +
                '}';
    }
}
