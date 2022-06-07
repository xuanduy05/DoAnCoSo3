package com.example.doancoso3.inteface;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface PhongInterface extends NetworkInterface{
    @FormUrlEncoded
    @POST("phonggetAll.php")
    Call<String> geAlltPhong(
            @Field("Ten") String name
    );
}
