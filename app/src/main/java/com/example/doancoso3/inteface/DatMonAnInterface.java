package com.example.doancoso3.inteface;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface DatMonAnInterface extends NetworkInterface{
    @FormUrlEncoded
    @POST("datMongetAll.php")
    Call<String> geAlltDatMon(
            @Field("Ten") String name
    );
}
