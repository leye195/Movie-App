package com.example.leeyoungjae.my.View.Activity;

import android.content.Intent;
import android.media.Rating;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leeyoungjae.my.Controller.MovieListService;
import com.example.leeyoungjae.my.Model.Comment;
import com.example.leeyoungjae.my.NetworkStatus;
import com.example.leeyoungjae.my.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WriteCommentActivity extends AppCompatActivity {
    private RatingBar rating;
    private Button btn;
    private Button cancel_btn;
    private EditText comment;
    private TextView movie_title;
    private ImageView imageView;

    private String m_id;
    private MovieListService movieService;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_comment);
        rating=findViewById(R.id.rating_star);
        comment=findViewById(R.id.content_input);
        btn=findViewById(R.id.s_btn);
        cancel_btn=findViewById(R.id.b_btn);
        movie_title=findViewById(R.id.movie_title);
        imageView=findViewById(R.id.movie_grade);

        retrofit=new Retrofit.Builder()
                .baseUrl("http://boostcourse-appapi.connect.or.kr:10000/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        movieService=retrofit.create(MovieListService.class);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnBack();
            }
        });
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent=getIntent();
        processIntent(intent);
    }
    private void processIntent(Intent intent){
        intent=getIntent();
        if(intent!=null){
            String t=intent.getStringExtra("title");
            String a=intent.getStringExtra("grade");
            float rate=intent.getFloatExtra("rating",0.0f);
            m_id=intent.getStringExtra("id");
            rating.setRating(rate);
            movie_title.setText(t);
            setAgeImg(a);
        }
    }
    public void returnBack(){
        if(NetworkStatus.getConnectivityStatus(getApplicationContext())==0){
        Intent intent=new Intent();
        String content=comment.getText().toString();
        float rat_num=rating.getRating();
        //SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //Date d=new Date();
        ProcessComment(m_id,"ulwa",String.valueOf(rat_num),content);
        intent.putExtra("id",m_id);
        //intent.putExtra("content",content);
        //intent.putExtra("rate",rat_num);
        setResult(RESULT_OK,intent);
        finish();
        }else{
            Toast.makeText(getApplicationContext(),"인터넷 연결 확인 하세요",Toast.LENGTH_LONG).show();
        }
    }
    public void ProcessComment(String id,String writer,String rating,String contents){
        Map<String,String>mp=new HashMap<>();
        mp.put("id",id);
        mp.put("writer",writer);
        mp.put("rating",rating);
        mp.put("contents",contents);

        Call<Comment>call=movieService.createComment(mp);
        call.enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                if(response.isSuccessful()&&response.code()==200){
                    Toast.makeText(getApplicationContext(),"작성 성공",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"작성 실패",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {

            }
        });
    }
    public void setAgeImg(String a){
        if(a.equals(getResources().getString(R.string.all_age))){
            imageView.setImageResource(R.drawable.ic_all);
        }else if(a.equals(getResources().getString(R.string.nineteen_age))){
            imageView.setImageResource(R.drawable.ic_19);
        }else if(a.equals(getResources().getString(R.string.fifteen_age))){
            imageView.setImageResource(R.drawable.ic_15);
        }else if(a.equals(getResources().getString(R.string.twelve_age))){
            imageView.setImageResource(R.drawable.ic_12);
        }
    }
}
