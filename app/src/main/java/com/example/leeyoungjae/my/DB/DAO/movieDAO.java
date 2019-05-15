package com.example.leeyoungjae.my.DB.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.leeyoungjae.my.DB.Entity.movie;
import com.example.leeyoungjae.my.DB.Entity.outline;
import com.example.leeyoungjae.my.Model.Movie;

import java.util.ArrayList;

@Dao
public interface movieDAO {
    /*
   private static String insertMovie="Insert or replace into movie (id,title,date_val,user_rating,audience_rating,reviewer_rating,reservation_rate,reservation_grade,grade,"+
            "thumb,image,photos,videos,outlink,genre,duration,audience,synopsis,director,actor,_like,_dislike)"+
            " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void InsertMovie(movie item);

    @Query("Select * from movie where id=:m_id")
    public movie selectMovie(String m_id);
}
