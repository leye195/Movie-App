package com.example.leeyoungjae.my.View;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.leeyoungjae.my.R;

import java.util.zip.Inflater;

public class CommentItemView extends LinearLayout {
    private TextView userid;
    private TextView time;
    private TextView comment;
    private TextView recommend_cnt;
    private RatingBar ratingBar;
    public CommentItemView(Context context) {
        super(context);
        init(context);
    }

    public CommentItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    public void init(Context context){
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.comment_item_view,this,true);
        userid=findViewById(R.id.userid);
        time=findViewById(R.id.time);
        comment=findViewById(R.id.comment);
        ratingBar=findViewById(R.id.rating_star);
        ratingBar.setMax(5);
        ratingBar.setNumStars(5);
        recommend_cnt=findViewById(R.id.recommend_cnt);
    }
    public void setUserid(String u_id){
        userid.setText(u_id);
    }
    public void setTime(String t){
        time.setText(t);
    }
    public void setComment(String c){
        comment.setText(c);
    }
    public void setRate(float r){ratingBar.setRating(r);}
    public void setRecommend_cnt(String rc){recommend_cnt.setText(rc);}
}
