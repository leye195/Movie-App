package com.example.leeyoungjae.my.DB.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.leeyoungjae.my.DB.Entity.outline;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface outlineDAO {
    /*
     private static String insertOutline="Insert or replace into outline (id,title,title_eng,dateValue,user_rating,audience_rating,reviewer_rating,reservation_rate,reservation_grade,grade,thumb,image)"+
            " values (?,?,?,?,?,?,?,?,?,?,?,?)";
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void InsertOutline(outline out);

    @Query("Select * from outline")
    public List<outline>getoutline_ord1();
    @Query("Select * from outline order by id desc")
    public List<outline> getoutline_ord2();
    @Query("Select * from outline order by dateValue desc")
    public List<outline>getoutline_ord3();



}
