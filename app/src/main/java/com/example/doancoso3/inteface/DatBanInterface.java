package com.example.doancoso3.inteface;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface DatBanInterface extends NetworkInterface{
    @FormUrlEncoded
    @POST("getDatBan.php")
    Call<String> getAlltDatBan(
            @Field("Ten") String name
    );

    @POST("getDatBan.php")
    Call<String> getSuatDatBan(
            @Field("Ten") String name
    );
}
