package com.example.leeyoungjae.my.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.leeyoungjae.my.Model.Comment;
import com.example.leeyoungjae.my.Model.MovieDetail;
import com.example.leeyoungjae.my.Model.MovieInfo;
import com.example.leeyoungjae.my.Model.MovieList;

import static android.support.constraint.Constraints.TAG;

public class DBHelper {
    private static final String TAG="DBHelper";
    private static SQLiteDatabase db;
    private static String createTableOutline="create table if not exists outline "+
            "("+
            //" _id integer PRIMARY KEY autoincrement, "+
            " id integer PRIMARY KEY, "+
            " title text, "+
            " title_eng text, "+
            " dateValue text, "+
            " user_rating float, "+
            " audience_rating float, "+
            " reviewer_rating float, "+
            " reservation_rate float, "+
            " reservation_grade integer, "+
            " grade integer, "+
            " thumb text, "+
            " image text"+
            ")";
    private static String createTableMovie="create table if not exists movie "+
            "("+
            //" _id integer PRIMARY KEY autoincrement, "+
            " id integer PRIMARY KEY, "+
            " title text, "+
            " date_val text, "+
            " user_rating float, "+
            " audience_rating float, "+
            " reviewer_rating float, "+
            " reservation_rate float, "+
            " reservation_grade integer, "+
            " grade integer, "+
            " thumb text, "+
            " image text, "+
            " photos text, "+
            " videos text, "+
            " outlink text, "+
            " genre text, "+
            " duration integer, "+
            " audience integer, "+
            " synopsis text, "+
            " director text, "+
            " actor text, "+
            " _like integer, "+
            " _dislike integer"+
            ")";
    private static String createTableReview="create table if not exists review "+
            "("+
            //" _id integer PRIMARY KEY autoincrement, "+
            " id integer PRIMARY KEY, "+
            " writer text, "+
            " movieid integer, "+
            " writer_image text, "+
            " time_val text, "+
            " timestamp text, "+
            " rating float, "+
            " contents text, "+
            " recommend integer"+
            ")";
    private static String insertOutline="Insert or replace into outline (id,title,title_eng,dateValue,user_rating,audience_rating,reviewer_rating,reservation_rate,reservation_grade,grade,thumb,image)"+
            " values (?,?,?,?,?,?,?,?,?,?,?,?)";
    private static String insertMovie="Insert or replace into movie (id,title,date_val,user_rating,audience_rating,reviewer_rating,reservation_rate,reservation_grade,grade,"+
            "thumb,image,photos,videos,outlink,genre,duration,audience,synopsis,director,actor,_like,_dislike)"+
            " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static String insertReview="Insert or replace into review (id,writer,movieid,writer_image,time_val,timestamp,rating,contents,recommend)"+
            " values (?,?,?,?,?,?,?,?,?)";

    private static String selectOutline="Select * from outline";
    private static String selectOutline_ord2="Select * from outline order by id desc";
    private static String selectOutline_ord3="Select * from outline order by dateValue desc";


    private static String selectMovie="Select * from movie where id=?";
    private static String selectReview="Select * from review where movieid=? order by id desc";
    public static void openDB(Context context, String dbname){
        println("openDB()호출됨");
        db=context.openOrCreateDatabase(dbname,Context.MODE_PRIVATE,null);
        if(db!=null){
            println("DB 오픈됨");
        }
    }
    public static void createTable(String table){
        println("createTable()호출됨");
        if(db!=null){
            if(table.equals("outline")){
                db.execSQL(createTableOutline);
                println("outline table 생성요청됨");
            }else if(table.equals("movie")){
                db.execSQL(createTableMovie);
                println("movie table 생성요청됨");
            }else if(table.equals("review")){
                db.execSQL(createTableReview);
                println("review table 생성요청됨");
            }
        }else{
            println("DB 오픈 먼저");
        }
    }
    public static void DropTable(String table){
        if(db!=null){
            db.execSQL("Drop table if exists "+table);
        }
    }
    public static void insertData(String table,Object obj){
        if(db!=null){
            if(table.equals("outline")){
                if(obj instanceof MovieInfo){
                    Object[]o_list={((MovieInfo) obj).getId(),((MovieInfo) obj).getTitle(),((MovieInfo) obj).getTitle_eng(),((MovieInfo) obj).getDate(),
                    ((MovieInfo) obj).getUser_rating(),((MovieInfo) obj).getAudience_rating(),((MovieInfo) obj).getReviewer_rating(),((MovieInfo) obj).getReservation_rate(),
                    ((MovieInfo) obj).getReservation_grade(),((MovieInfo) obj).getGrade(),((MovieInfo) obj).getThumb(),((MovieInfo) obj).getImage()};
                    db.execSQL(insertOutline,o_list);
                }
                //db.close();
                println("outline 데이터 insert요청됨");
            }else if(table.equals("movie")){
                if(obj instanceof MovieDetail.MovieItem){
                    Object[]o_list={String.valueOf(((MovieDetail.MovieItem) obj).getId()),((MovieDetail.MovieItem) obj).getTitle(),((MovieDetail.MovieItem) obj).getDate(),String.valueOf(((MovieDetail.MovieItem) obj).getUser_rating()),
                            String.valueOf(((MovieDetail.MovieItem) obj).getAudience_rating()),String.valueOf(((MovieDetail.MovieItem) obj).getReviewer_rating()),
                            String.valueOf(((MovieDetail.MovieItem) obj).getReservation_rate()),String.valueOf(((MovieDetail.MovieItem) obj).getReservation_grade()),
                            String.valueOf(((MovieDetail.MovieItem) obj).getGrade()),((MovieDetail.MovieItem) obj).getThumb(),((MovieDetail.MovieItem) obj).getImage(),
                            ((MovieDetail.MovieItem) obj).getPhotos(),((MovieDetail.MovieItem) obj).getVideos(),((MovieDetail.MovieItem) obj).getOutlink(),
                            ((MovieDetail.MovieItem) obj).getGenre(),String.valueOf(((MovieDetail.MovieItem) obj).getDuration()),String.valueOf(((MovieDetail.MovieItem) obj).getAudience()),
                            ((MovieDetail.MovieItem) obj).getSynopsis(),((MovieDetail.MovieItem) obj).getDirector(),((MovieDetail.MovieItem) obj).getActor(),
                            String.valueOf(((MovieDetail.MovieItem) obj).getLike()),String.valueOf(((MovieDetail.MovieItem) obj).getDislike())};

                    db.execSQL(insertMovie,o_list);
                }
                //db.close();
                println("movie 데이터 insert 요청됨");
            }else if(table.equals("review")){
                if(obj instanceof Comment){
                    Object[]o_list={String.valueOf(((Comment) obj).getId()),((Comment) obj).getWriter(),String.valueOf(((Comment) obj).getMovie_id()),
                            ((Comment) obj).getWriter_img(),((Comment) obj).getTime(),((Comment) obj).getTimestamp(),
                            String.valueOf(((Comment) obj).getRate()),((Comment) obj).getComment(),String.valueOf(((Comment) obj).getRecommend())};
                    db.execSQL(insertReview,o_list);
                }
                //db.close();
                println("review 데이터 insert 요청됨");
            }
        }else{
            println("DB 오픈 먼저");
        }
    }
    public static Cursor selectData(String table,int id){
        if(db!=null){
            if(table.equals("outline")){
                if(id==1){
                    Cursor c=db.rawQuery(selectOutline,null);
                    println("outline 데이터 select1요청됨");
                    println(String.valueOf(c.getCount()));
                    return c;
                }else if(id==2){
                    Cursor c=db.rawQuery(selectOutline_ord2,null);
                    println("outline 데이터 select2요청됨");
                    println(String.valueOf(c.getCount()));
                    return c;
                }else if(id==3) {
                    Cursor c=db.rawQuery(selectOutline_ord3,null);
                    println("outline 데이터 select3요청됨");
                    println(String.valueOf(c.getCount()));
                    return c;
                }
                //c.close();
            }else if(table.equals("movie")){
                String[]param={String.valueOf(id)};
                Cursor c =db.rawQuery(selectMovie,param);
                println("movie 데이터 select 요청됨");
                return c;
            }else if(table.equals("review")){
                String[]param={String.valueOf(id)};
                Cursor c=db.rawQuery(selectReview,param);
                println("review 데이터 select 요청됨");
                return c;
            }
        }else{
            println("DB 오픈 먼저");
        }
        return null;
    }
    public static void println(String data){
        Log.d(TAG,data);
    }
}