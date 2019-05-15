package com.example.leeyoungjae.my.DB.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.leeyoungjae.my.DB.Entity.outline;
import com.example.leeyoungjae.my.Model.Comment;

import java.util.ArrayList;

@Dao
public interface reviewDAO {
    /*
    private static String insertReview="Insert or replace into review (id,writer,movieid,writer_image,time_val,timestamp,rating,contents,recommend)"+
            " values (?,?,?,?,?,?,?,?,?)";
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void InsertReview(int id,String writer,int movieid,String writer_image,String time_val,String timestamp
                            ,float rating,String contents,int recommend);

    @Query("Select * from review where movieid = :id")
    public ArrayList<Comment> selectReview(String id);


}
