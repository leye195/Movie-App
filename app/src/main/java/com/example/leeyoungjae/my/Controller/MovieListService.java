package com.example.leeyoungjae.my.Controller;

import com.example.leeyoungjae.my.Model.Comment;
import com.example.leeyoungjae.my.Model.CommentList;
import com.example.leeyoungjae.my.Model.MovieDetail;
import com.example.leeyoungjae.my.Model.MovieInfo;
import com.example.leeyoungjae.my.Model.MovieList;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface MovieListService {
    @GET("readMovieList")
    Call<MovieList>readMovieList(@Query("type") String type);

    @GET("readMovie")
    Call<MovieDetail>readMovie(@QueryMap Map<String,String>mp);

    @GET("readCommentList")
    Call<CommentList>readCommentList(@QueryMap Map<String,String>mp);

    @GET("createComment")
    Call<Comment>createComment(@QueryMap Map<String,String>mp);

}
