package com.example.doancoso3.model;

import java.util.Date;

public class DatMonAn {
    private int id;
    private String phuongthucthanhtoan,Ten_khach,email,SDT,diachi,ghichu;
    private float Tong_tien;
    private String Trang_thai,user_id;
    private Date ngay_dat;

    public DatMonAn() {

    }

    public DatMonAn(int id, String user_id, String phuongthucthanhtoan, String ten_khach, String email, String SDT, String diachi, String ghichu, float tong_tien, String trang_thai, Date ngay_dat) {
        this.id = id;
        this.user_id = user_id;
        this.phuongthucthanhtoan = phuongthucthanhtoan;
        Ten_khach = ten_khach;
        this.email = email;
        this.SDT = SDT;
        this.diachi = diachi;
        this.ghichu = ghichu;
        Tong_tien = tong_tien;
        Trang_thai = trang_thai;
        this.ngay_dat = ngay_dat;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPhuongthucthanhtoan() {
        return phuongthucthanhtoan;
    }

    public void setPhuongthucthanhtoan(String phuongthucthanhtoan) {
        this.phuongthucthanhtoan = phuongthucthanhtoan;
    }

    public String getTen_khach() {
        return Ten_khach;
    }

    public void setTen_khach(String ten_khach) {
        Ten_khach = ten_khach;
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

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public float getTong_tien() {
        return Tong_tien;
    }

    public void setTong_tien(float tong_tien) {
        Tong_tien = tong_tien;
    }

    public String getTrang_thai() {
        return Trang_thai;
    }

    public void setTrang_thai(String trang_thai) {
        Trang_thai = trang_thai;
    }

    public Date getNgay_dat() {
        return ngay_dat;
    }

    public void setNgay_dat(Date ngay_dat) {
        this.ngay_dat = ngay_dat;
    }

    @Override
    public String toString() {
        return "DatMonAn{" +
                "user_id=" + user_id +
                ", phuongthucthanhtoan='" + phuongthucthanhtoan + '\'' +
                ", Ten_khach='" + Ten_khach + '\'' +
                ", email='" + email + '\'' +
                ", SDT='" + SDT + '\'' +
                ", diachi='" + diachi + '\'' +
                ", ghichu='" + ghichu + '\'' +
                ", Tong_tien=" + Tong_tien +
                ", Trang_thai='" + Trang_thai + '\'' +
                '}';
    }
}
