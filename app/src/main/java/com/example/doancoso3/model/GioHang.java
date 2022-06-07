package com.example.doancoso3.model;

import java.io.Serializable;

public class GioHang implements Serializable {
    int id;
    int idsp;
    String tensp;
    String hinhsp;
    float giasp;
    int soluong;
    float tonggia;

    public GioHang() {
    }

    public GioHang(int idsp, String tensp, String hinhsp, float giasp, int soluong) {
        this.idsp = idsp;
        this.tensp = tensp;
        this.hinhsp = hinhsp;
        this.giasp = giasp;
        this.soluong = soluong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdsp() {
        return idsp;
    }

    public void setIdsp(int idsp) {
        this.idsp = idsp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getHinhsp() {
        return hinhsp;
    }

    public void setHinhsp(String hinhsp) {
        this.hinhsp = hinhsp;
    }

    public float getGiasp() {
        return giasp;
    }

    public void setGiasp(float giasp) {
        this.giasp = giasp;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public float getTonggia() {
        return tonggia;
    }

    public void setTonggia(float tonggia) {
        this.tonggia = tonggia;
    }
}
