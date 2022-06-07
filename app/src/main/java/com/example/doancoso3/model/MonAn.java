package com.example.doancoso3.model;

public class MonAn {
    private int id;
    private String Ten_monan,Mo_ta,HinhAnh;
    private float Gia;
    private int idDanhMuc;
    private String Ten_danhmuc;

    public MonAn() {

    }

//    public MonAn(int id, String ten_monan, String mo_ta, String hinhAnh, float gia, int idDanhMuc, String ten_danhmuc) {
//        this.id = id;
//        Ten_monan = ten_monan;
//        Mo_ta = mo_ta;
//        HinhAnh = hinhAnh;
//        Gia = gia;
//        this.idDanhMuc = idDanhMuc;
//        Ten_danhmuc = ten_danhmuc;
//    }


    public MonAn(int id, String ten_monan, String mo_ta, String hinhAnh, float gia, int idDanhMuc, String ten_danhmuc) {
        this.id = id;
        Ten_monan = ten_monan;
        Mo_ta = mo_ta;
        HinhAnh = hinhAnh;
        Gia = gia;
        this.idDanhMuc = idDanhMuc;
        Ten_danhmuc = ten_danhmuc;
    }

    public MonAn(String ten, String loai, float gia,String HinhAnh) {
        this.Ten_monan = ten;
        this.Ten_danhmuc = loai;
        this.Gia = gia;
        this.HinhAnh = HinhAnh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen_monan() {
        return Ten_monan;
    }

    public void setTen_monan(String ten_monan) {
        Ten_monan = ten_monan;
    }

    public String getMo_ta() {
        return Mo_ta;
    }

    public void setMo_ta(String mo_ta) {
        Mo_ta = mo_ta;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public float getGia() {
        return Gia;
    }

    public void setGia(float gia) {
        Gia = gia;
    }

    public int getIdDanhMuc() {
        return idDanhMuc;
    }

    public void setIdDanhMuc(int idDanhMuc) {
        this.idDanhMuc = idDanhMuc;
    }

    public String getTen_danhmuc() {
        return Ten_danhmuc;
    }

    public void setTen_danhmuc(String ten_danhmuc) {
        Ten_danhmuc = ten_danhmuc;
    }

    @Override
    public String toString() {
        return "MonAn{" +
                "Ten_monan='" + Ten_monan + '\'' +
                ", Mo_ta='" + Mo_ta + '\'' +
                ", HinhAnh='" + HinhAnh + '\'' +
                ", Gia='" + Gia + '\'' +
                ", idDanhMuc='" + idDanhMuc + '\'' +
                '}';
    }
}
