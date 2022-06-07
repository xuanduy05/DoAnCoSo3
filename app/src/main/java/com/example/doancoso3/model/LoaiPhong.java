package com.example.doancoso3.model;

public class LoaiPhong {
    private int id;
    private String MoTa;

    public LoaiPhong() {

    }

    public LoaiPhong(int id, String moTa) {
        this.id = id;
        MoTa = moTa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    @Override
    public String toString() {
        return "LoaiPhong{" +
                "MoTa='" + MoTa + '\'' +
                '}';
    }
}
