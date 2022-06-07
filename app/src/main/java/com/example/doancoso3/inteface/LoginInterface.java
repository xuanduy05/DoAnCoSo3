package com.example.doancoso3.inteface;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginInterface extends NetworkInterface{
//    String LOGINURL = "http://192.168.1.12:8081/app_android/";
    @FormUrlEncoded
    @POST("login.php")
    Call<String> getUserLogin(

            @Field("Ten") String uname,
            @Field("password") String password
    );
}
