package com.example.doancoso3.inteface;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoaiNguoiDungInterface extends NetworkInterface{
    @FormUrlEncoded
    @POST("getLoaiNguoiDung.php")
    Call<String> geAlltNguoidung(
            @Field("Ten") String name
    );
}
