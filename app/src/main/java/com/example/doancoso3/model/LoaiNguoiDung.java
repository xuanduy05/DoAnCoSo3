package com.example.doancoso3.model;

public class LoaiNguoiDung {
    private int Id;
    private String Mo_Ta;

    public LoaiNguoiDung() {

    }

    public LoaiNguoiDung(int id, String mo_Ta) {
        Id = id;
        Mo_Ta = mo_Ta;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getMo_Ta() {
        return Mo_Ta;
    }

    public void setMo_Ta(String mo_Ta) {
        Mo_Ta = mo_Ta;
    }

    @Override
    public String toString() {
        return "LoaiNguoiDung{" +
                "Mo_Ta='" + Mo_Ta + '\'' +
                '}';
    }
}
