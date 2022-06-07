package com.example.doancoso3.model;

public class DanhMuc {
    private int id;
    private String Ten_danhmuc;

    public DanhMuc() {

    }


    public DanhMuc(int id, String ten_danhmuc) {
        this.id = id;
        Ten_danhmuc = ten_danhmuc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen_danhmuc() {
        return Ten_danhmuc;
    }

    public void setTen_danhmuc(String ten_danhmuc) {
        Ten_danhmuc = ten_danhmuc;
    }

    @Override
    public String toString() {
        return "DanhMuc{" +
                "Ten_danhmuc='" + Ten_danhmuc + '\'' +
                '}';
    }
}
