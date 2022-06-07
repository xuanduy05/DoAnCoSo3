package com.example.doancoso3.inteface;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface MonAnInterface extends NetworkInterface{
    @FormUrlEncoded
    @POST("monAngetAll.php")
    Call<String> geAlltMonAn(
            @Field("Ten") String name
    );
    }

