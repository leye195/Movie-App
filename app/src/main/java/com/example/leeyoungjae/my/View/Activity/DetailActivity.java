package com.example.leeyoungjae.my.View.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.leeyoungjae.my.DB.DBHelper;
import com.example.leeyoungjae.my.Controller.MovieListService;
import com.example.leeyoungjae.my.Model.Comment;
import com.example.leeyoungjae.my.Model.CommentList;
import com.example.leeyoungjae.my.Model.MovieDetail;
import com.example.leeyoungjae.my.NetworkStatus;
import com.example.leeyoungjae.my.R;
import com.example.leeyoungjae.my.View.Adapter.CommentAdapter;
import com.example.leeyoungjae.my.View.Adapter.gallery_viewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivity extends AppCompatActivity {
    private Button upbtn;
    private TextView likecnt;
    private TextView dislikecnt;
    private Button downbtn;
    private ListView comment_list;
    private ImageView movie_img;
    private TextView title;
    private ImageView age;
    private TextView rate;

    private TextView synopsis;
    private TextView date;
    private TextView genre_time;
    private TextView audience_rate;
    private TextView audience;
    private TextView director;
    private TextView actor;

    private RecyclerView gallery_view;
    private gallery_viewAdapter g_adapter;

    private ArrayList<Comment> comments;
    private CommentAdapter adapter;
    private boolean likeState=false;
    private boolean dislikeState=false;
    private String a;
    private String m_id;
    private ArrayList<String>imgs;
    int likeCount=0;
    int dislikeCount=0;


    MovieListService movieService;
    Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        DBHelper.openDB(getApplicationContext(),"movie");
        DBHelper.openDB(getApplicationContext(),"review");

        Intent g_intent=getIntent();
        movie_img=findViewById(R.id.movie_img);
        title=findViewById(R.id.movie_title);
        age=findViewById(R.id.movie_age);
        rate=findViewById(R.id.buy_rate);

        likecnt=findViewById(R.id.liked_cnt);
        upbtn=findViewById(R.id.thumb_up);
        dislikecnt=findViewById(R.id.disliked_cnt);
        downbtn=findViewById(R.id.thumb_down);

        synopsis=findViewById(R.id.summary);
        date=findViewById(R.id.opening_date);
        genre_time=findViewById(R.id.genre_time);
        audience_rate=findViewById(R.id.rate_txt);
        audience=findViewById(R.id.people_cnt);
        director=findViewById(R.id.director_name);
        actor=findViewById(R.id.actors);

        gallery_view=findViewById(R.id.gallery_view);
        LinearLayoutManager manager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        gallery_view.setLayoutManager(manager);
        g_adapter=new gallery_viewAdapter(getApplicationContext());

        comment_list=findViewById(R.id.comment_list);
        adapter=new CommentAdapter(getApplicationContext());
        retrofit=new Retrofit.Builder()
                .baseUrl("http://boostcourse-appapi.connect.or.kr:10000/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        movieService=retrofit.create(MovieListService.class);
        if(g_intent!=null){
            String t=g_intent.getStringExtra("title");
            String r=g_intent.getStringExtra("rate");
            String im=g_intent.getStringExtra("img");
            a=g_intent.getStringExtra("age");
            m_id=g_intent.getStringExtra("movieid");

            title.setText(t);
            rate.setText(r+"%");
            setAgeImg(a);
            Picasso.with(getApplicationContext())
                    .load(im)
                    .into(movie_img);
            if(NetworkStatus.getConnectivityStatus(getApplicationContext())==0) {
                DBHelper.createTable("movie");
                DBHelper.createTable("review");
                getMovieDetail(m_id);
                getCommentList(m_id);
            }else{
                gallery_view.setVisibility(View.GONE);
                Cursor c1= DBHelper.selectData("movie",Integer.parseInt(m_id));
                while(c1.moveToNext()){
                    synopsis.setText(c1.getString(17));
                    date.setText(c1.getString(2));
                    director.setText(c1.getString(18));
                    actor.setText(c1.getString(19));
                    audience.setText(String.valueOf(c1.getInt(16)));
                    audience_rate.setText(String.valueOf(c1.getFloat(4)));
                    genre_time.setText(c1.getString(14)+"/"+String.valueOf(c1.getInt(15))+"분");
                    likeCount=c1.getInt(20);
                    likecnt.setText(String.valueOf(likeCount));
                    dislikeCount=c1.getInt(21);
                    dislikecnt.setText(String.valueOf(dislikeCount));

                }
                c1.close();

                Cursor c2= DBHelper.selectData("review",Integer.parseInt(m_id));
                DBHelper.println(String.valueOf(c2.getCount()));
                comments=new ArrayList<>();
                while(c2.moveToNext()){
                    String id=String.valueOf(c2.getInt(0));
                    String writer=c2.getString(1);
                    String movie_id=String.valueOf(c2.getInt(2));
                    String time=c2.getString(4);
                    String comment=c2.getString(7);
                    float rate=c2.getFloat(6);
                    String time_stamp=c2.getString(5);
                    String writer_img=c2.getString(3);
                    int recommend=c2.getInt(8);
                    Comment c=new Comment(id,writer,movie_id,time,comment,rate,time_stamp,writer_img,recommend);
                    DBHelper.println(writer+" "+comment);
                    comments.add(c);
                    adapter.addItem(c);
                }
                comment_list.setAdapter(adapter);
                c2.close();
            }
        }
        upbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(likeState==false){
                    incrCnt(0);
                    if(dislikeState) {
                        dislikeState = false;
                        decCnt(1);
                    }
                }else{
                    decCnt(0);
                }
                likeState=!likeState;
            }
        });
        downbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dislikeState==false){
                    incrCnt(1);
                    if(likeState){
                        likeState=false;
                        decCnt(0);
                    }
                }else{
                    decCnt(1);
                }
                dislikeState=!dislikeState;
            }
        });

        /*comments=new ArrayList<>();
        comments.add(new Comment("leye195","10분전","진짜 재밌더라...",4.5f));
        comments.add(new Comment("ljj195","15분전","뭐죠???? 이게 재밌다고??",3.5f));
        adapter=new CommentAdapter(getApplicationContext(),comments);
        comment_list.setAdapter(adapter);*/

        View v1=findViewById(R.id.post_comment);
        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Snackbar.make(v,"작성하기",Snackbar.LENGTH_LONG).show();
                Intent intent=new Intent(getApplicationContext(),WriteCommentActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("id",m_id);
                intent.putExtra("title",title.getText().toString());
                intent.putExtra("grade",a);
                startActivityForResult(intent,101);
            }
        });
        View v2=findViewById(R.id.readall);
        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"모두 보기",Snackbar.LENGTH_LONG).show();
                Intent intent=new Intent(DetailActivity.this,AllCommentActivity.class);
                intent.putParcelableArrayListExtra("comments",comments);
                intent.putExtra("title",title.getText().toString());
                intent.putExtra("age",a);
                intent.putExtra("rate",audience_rate.getText().toString());
                intent.putExtra("id",m_id);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent,102);
            }
        });
    }
    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    protected void onPause() {
        super.onPause();
        //DBHelpe
    }

    public void getMovieDetail(String id){
        Map<String,String>mp=new HashMap<>();
        mp.put("id",id);
        Call<MovieDetail>call= movieService.readMovie(mp);
        call.enqueue(new Callback<MovieDetail>(){

            @Override
            public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                if(response.code()==200&&response.body()!=null){
                    ArrayList<MovieDetail.MovieItem> movieDetail=response.body().getResult();
                    Log.d("ReadMovie",movieDetail.toString());
                    if(movieDetail!=null){
                        synopsis.setText(movieDetail.get(0).getSynopsis());
                        date.setText(movieDetail.get(0).getDate()+"개봉");
                        director.setText(movieDetail.get(0).getDirector());
                        actor.setText(movieDetail.get(0).getActor());
                        audience.setText(movieDetail.get(0).getAudience());
                        audience_rate.setText(movieDetail.get(0).getAudience_rating());
                        genre_time.setText(movieDetail.get(0).getGenre()+"/"+movieDetail.get(0).getDuration()+"분");
                        likeCount=movieDetail.get(0).getLike();
                        likecnt.setText(String.valueOf(likeCount));
                        dislikeCount=movieDetail.get(0).getDislike();
                        dislikecnt.setText(String.valueOf(dislikeCount));

                        imgs=new ArrayList<String>();
                        if(movieDetail.get(0).getPhotos()!=null){
                            for(String s:movieDetail.get(0).getPhotos().split(",")){
                                imgs.add(s);
                            }
                        }
                        g_adapter.addItems(imgs);
                        gallery_view.setAdapter(g_adapter);

                        g_adapter.setOnItemClickListener(new gallery_viewAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(gallery_viewAdapter.ViewHolder viewholder, View v, int pos) {
                                Intent intent=new Intent(getApplicationContext(),PhotoActivity.class);
                                intent.putStringArrayListExtra("photos",imgs);
                                intent.putExtra("pos",pos);
                                //intent.putExtra("photo",imgs.get(pos));
                                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|
                                        Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);

                            }
                        });

                        Object obj=movieDetail.get(0);
                        DBHelper.insertData("movie",obj);


                    }
                }
            }
            @Override
            public void onFailure(Call<MovieDetail> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==101||requestCode==102){
            if(data!=null){
                String mid=data.getStringExtra("id");
                Log.d("From Write",mid);
                adapter=new CommentAdapter(getApplicationContext());
                getCommentList(mid);
            }
        }
    }

    public void getCommentList(String id){
        Map<String,String>mp=new HashMap<>();
        mp.put("id",id);
        Call<CommentList>call=movieService.readCommentList(mp);
        call.enqueue(new Callback<CommentList>() {
            @Override
            public void onResponse(Call<CommentList> call, Response<CommentList> response) {
                if(response.isSuccessful() &&response.body()!=null){
                    if(response.code()==200){
                        comments=response.body().getResult();
                        for(Comment item:comments){
                            adapter.addItem(item);
                            Object obj=item;
                            DBHelper.println(item.toString());
                            DBHelper.insertData("review",obj);
                        }
                       // Log.d("From Comment",comments.toString());
                        comment_list.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<CommentList> call, Throwable t) {

            }
        });
    }
    public void setAgeImg(String a){
        if(a.equals(getResources().getString(R.string.all_age))){
            age.setImageResource(R.drawable.ic_all);
        }else if(a.equals(getResources().getString(R.string.nineteen_age))){
            age.setImageResource(R.drawable.ic_19);
        }else if(a.equals(getResources().getString(R.string.fifteen_age))){
            age.setImageResource(R.drawable.ic_15);
        }else if(a.equals(getResources().getString(R.string.twelve_age))){
            age.setImageResource(R.drawable.ic_12);
        }
    }
    public void incrCnt(int kind){
        if(kind==0){
            likeCount+=1;
            likecnt.setText(String.valueOf(likeCount));
            upbtn.setBackgroundResource(R.drawable.ic_thumb_up_selected);
            //decCnt(1);
        }else if(kind==1){
            dislikeCount+=1;
            dislikecnt.setText(String.valueOf(dislikeCount));
            downbtn.setBackgroundResource(R.drawable.ic_thumb_down_selected);
            //decCnt(0);
        }
    }
    public void decCnt(int kind){
        if(kind==0){
            likeCount-=1;
            likecnt.setText(String.valueOf(likeCount));
            upbtn.setBackgroundResource(R.drawable.ic_thumb_up);
        }else if(kind==1){
            dislikeCount-=1;
            dislikecnt.setText(String.valueOf(dislikeCount));
            downbtn.setBackgroundResource(R.drawable.ic_thumb_down);
        }
    }
}
