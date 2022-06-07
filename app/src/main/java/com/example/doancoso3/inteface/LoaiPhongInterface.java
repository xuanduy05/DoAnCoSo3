package com.example.doancoso3.inteface;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoaiPhongInterface extends NetworkInterface{
    @FormUrlEncoded
    @POST("loaiPhonggetAll.php")
    Call<String> geAlltPhong(
            @Field("Ten") String name
    );
}
