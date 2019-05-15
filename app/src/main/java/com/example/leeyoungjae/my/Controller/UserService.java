package com.example.leeyoungjae.my.Controller;

import com.example.leeyoungjae.my.Model.User;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserService {
    @GET("Postitem_CF.php")
    Call<User> getRespos(@Query("userid") String id);

    @FormUrlEncoded
    @POST("Login.php")
    Call<User> postRespos(@Field("userId") String id,@Field("userpw")String pwd);

    @GET("users/CheckValidate.php")
    Call<User> CheckUserIdValidate(@Query("userId")String id);

    @FormUrlEncoded
    @POST("SignUp.php")
    Call<User> UserSignUp(@FieldMap Map<String,String> userInfo);
}
