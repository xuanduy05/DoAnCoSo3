package com.example.doancoso3.inteface;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserInterface extends NetworkInterface{
    @FormUrlEncoded
    @POST("retrieve.php")
    Call<String> geAlltUser(
            @Field("Ten") String uname
    );

}
