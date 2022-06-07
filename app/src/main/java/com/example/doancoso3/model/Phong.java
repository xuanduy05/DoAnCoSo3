package com.example.doancoso3.model;

public class Phong {
    private int id;
    private String Ten_phong,Anh;
    private String Mo_ta;
    private float Gia;
    private int id_loaiphong,cho_trong,Trang_thai;
    private String loai_phong;


//    private String id,Ten_phong,cho_trong,Mo_ta,Trang_thai,Gia,id_loaiphong,loai_phong;

    public Phong() {

    }

//    public Phong(String id, String ten_phong, String cho_trong, String mo_ta, String trang_thai, String gia, String id_loaiphong, String loai_phong) {
//        this.id = id;
//        Ten_phong = ten_phong;
//        this.cho_trong = cho_trong;
//        Mo_ta = mo_ta;
//        Trang_thai = trang_thai;
//        Gia = gia;
//        this.id_loaiphong = id_loaiphong;
//        this.loai_phong = loai_phong;
//    }


    public Phong(int id, String ten_phong, String anh, int cho_trong, String mo_ta, int trang_thai, float gia, int id_loaiphong, String loai_phong) {
        this.id = id;
        Ten_phong = ten_phong;
        Anh = anh;
        this.cho_trong = cho_trong;
        Mo_ta = mo_ta;
        Trang_thai = trang_thai;
        Gia = gia;
        this.id_loaiphong = id_loaiphong;
        this.loai_phong = loai_phong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen_phong() {
        return Ten_phong;
    }

    public void setTen_phong(String ten_phong) {
        Ten_phong = ten_phong;
    }

    public String getAnh() {
        return Anh;
    }

    public void setAnh(String anh) {
        Anh = anh;
    }

    public int getCho_trong() {
        return cho_trong;
    }

    public void setCho_trong(int cho_trong) {
        this.cho_trong = cho_trong;
    }

    public String getMo_ta() {
        return Mo_ta;
    }

    public void setMo_ta(String mo_ta) {
        Mo_ta = mo_ta;
    }

    public int getTrang_thai() {
        return Trang_thai;
    }

    public void setTrang_thai(int trang_thai) {
        Trang_thai = trang_thai;
    }

    public float getGia() {
        return Gia;
    }

    public void setGia(float gia) {
        Gia = gia;
    }

    public int getId_loaiphong() {
        return id_loaiphong;
    }

    public void setId_loaiphong(int id_loaiphong) {
        this.id_loaiphong = id_loaiphong;
    }

    public String getLoai_phong() {
        return loai_phong;
    }

    public void setLoai_phong(String loai_phong) {
        this.loai_phong = loai_phong;
    }

    @Override
    public String toString() {
        return "Phong{" +
                "Ten_phong='" + Ten_phong + '\'' +
                ", cho_trong='" + cho_trong + '\'' +
                ", Mo_ta='" + Mo_ta + '\'' +
                ", Trang_thai='" + Trang_thai + '\'' +
                ", Gia='" + Gia + '\'' +
                ", id_loaiphong='" + id_loaiphong + '\'' +
                '}';
    }
}
