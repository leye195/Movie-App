package com.example.leeyoungjae.my.Controller;

import com.example.leeyoungjae.my.Model.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {
    @GET("search/{type}")
    Call<Movie>getSearch(@Header("X-Naver-Client-Id")String clientId,
                         @Header("X-Naver-Client-Secret")String clientPw,
                         @Path("type")String type,
                         @Query("query")String query);
}
