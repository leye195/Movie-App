package com.example.leeyoungjae.my.View.Activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.leeyoungjae.my.Controller.MovieListService;
import com.example.leeyoungjae.my.Model.Comment;
import com.example.leeyoungjae.my.Model.CommentList;
import com.example.leeyoungjae.my.R;
import com.example.leeyoungjae.my.View.Adapter.CommentAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AllCommentActivity extends AppCompatActivity {
    private RatingBar ratingBar;
    private LinearLayout write_post;
    private ListView listview;
    private CommentAdapter adapter;
    private TextView movie_title;
    private ImageView imageView;
    private TextView movie_rate;
    private TextView total_comment;

    private ArrayList<Comment> comment_list;
    private MovieListService movieService;
    private Retrofit retrofit;

    private String m_id;
    private String age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_comment);

        movie_title=findViewById(R.id.movie_title);
        imageView=findViewById(R.id.movie_grade);
        movie_rate=findViewById(R.id.rate_num);
        ratingBar=findViewById(R.id.rating_star);
        write_post=findViewById(R.id.write_comment);
        total_comment=findViewById(R.id.c_count);

        Intent g_intent=getIntent();
        if(g_intent!=null){
            String title=g_intent.getStringExtra("title");
            String rate=g_intent.getStringExtra("rate");
            m_id=g_intent.getStringExtra("id");
            age=g_intent.getStringExtra("age");
            movie_title.setText(title);
            movie_rate.setText(rate);
            setAgeImg(age);
        }
        retrofit=new Retrofit.Builder()
                .baseUrl("http://boostcourse-appapi.connect.or.kr:10000/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        movieService=retrofit.create(MovieListService.class);

        write_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCommentWriteActivity();
            }
        });
        comment_list=getIntent().getParcelableArrayListExtra("comments");
        total_comment.setText("("+String.valueOf(comment_list.size())+"명)");
        listview=findViewById(R.id.comment_list);
        adapter=new CommentAdapter(getApplicationContext());
        for(Comment item:comment_list){
            adapter.addItem(item);
        }
        listview.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent=new Intent();
        intent.putExtra("id",m_id);
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==101){
            if(data!=null){
                String mid=data.getStringExtra("id");
                Log.d("From Write",mid);
                adapter=new CommentAdapter(getApplicationContext());
                getCommentList(mid);
            }
        }
    }

    public void showCommentWriteActivity(){
        Intent intent=new Intent(AllCommentActivity.this,WriteCommentActivity.class);
        //float rate_num=ratingBar.getRating();
        //intent.putExtra("rating",rate_num);
        intent.putExtra("id",m_id);
        intent.putExtra("title",movie_title.getText().toString());
        intent.putExtra("grade",age);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivityForResult(intent,101);
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
    public void getCommentList(String id){
        Map<String,String> mp=new HashMap<>();
        mp.put("id",id);
        Call<CommentList> call=movieService.readCommentList(mp);
        call.enqueue(new Callback<CommentList>() {
            @Override
            public void onResponse(Call<CommentList> call, Response<CommentList> response) {
                if(response.isSuccessful() &&response.body()!=null){
                    if(response.code()==200){
                        comment_list=response.body().getResult();
                        total_comment.setText(String.valueOf(comment_list.size()));
                        for(Comment item:comment_list){
                            adapter.addItem(item);
                        }
                        // Log.d("From Comment",comments.toString());
                        listview.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<CommentList> call, Throwable t) {

            }
        });
    }
}
