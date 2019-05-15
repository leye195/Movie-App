package com.example.leeyoungjae.my.DB;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.leeyoungjae.my.DB.DAO.movieDAO;
import com.example.leeyoungjae.my.DB.DAO.outlineDAO;
import com.example.leeyoungjae.my.DB.Entity.movie;
import com.example.leeyoungjae.my.DB.Entity.outline;

//, movie.class, review.class
@Database(entities ={outline.class, movie.class},version = 1,exportSchema = false)
public abstract class MovieDBHelper extends RoomDatabase {
    abstract public outlineDAO getOutlineDAO();
    abstract public movieDAO getMovieDAO();


    private static MovieDBHelper INSTANCE;
    public static MovieDBHelper getDatabase(final Context context){
        if(INSTANCE==null){
            synchronized (MovieDBHelper.class){
                INSTANCE=Room.databaseBuilder(context.getApplicationContext(),
                        MovieDBHelper.class,"outline").fallbackToDestructiveMigration()
                        .build();
            }
        }
        return INSTANCE;
    }
    public static void destroyInstance(){
        INSTANCE=null;
    }
}
