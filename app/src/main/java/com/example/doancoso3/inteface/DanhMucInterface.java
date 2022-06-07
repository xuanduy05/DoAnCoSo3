package com.example.doancoso3.inteface;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface DanhMucInterface extends NetworkInterface{
    @FormUrlEncoded
    @POST("getAllDanhMuc.php")
    Call<String> getAlltDanhMuc(
            @Field("Ten") String uname
    );

    @FormUrlEncoded
    @POST("getSuaDanhMuc.php")
    Call<String> getSuatDanhMuc(
            @Field("id") int id,
            @Field("Ten_danhmuc") String Ten_danhmuc
    );

    @FormUrlEncoded
    @POST("getXoaDanhMuc.php")
    Call<String> getXoatDanhMuc(
            @Field("id") int id
    );

    @FormUrlEncoded
    @POST("getThemDanhMuc.php")
    Call<String> getThemtDanhMuc(
            @Field("Ten_danhmuc") String Ten_danhmuc
    );
}
