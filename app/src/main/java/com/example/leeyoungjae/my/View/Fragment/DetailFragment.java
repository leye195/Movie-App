package com.example.leeyoungjae.my.View.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.leeyoungjae.my.Model.Comment;
import com.example.leeyoungjae.my.R;
import com.example.leeyoungjae.my.View.Activity.AllCommentActivity;
import com.example.leeyoungjae.my.View.Activity.DetailActivity;
import com.example.leeyoungjae.my.View.Activity.WriteCommentActivity;
import com.example.leeyoungjae.my.View.Adapter.CommentAdapter;

import java.util.ArrayList;

public class DetailFragment extends Fragment {
    private Button upbtn;
    private TextView likecnt;
    private TextView dislikecnt;
    private Button downbtn;
    private ListView comment_list;
    private ImageView movie_img;
    private TextView title;
    private ImageView age;
    private TextView rate;

    private ArrayList<Comment> comments;
    private CommentAdapter adapter;
    private boolean likeState=false;
    private boolean dislikeState=false;
    int likeCount=0;
    int dislikeCount=0;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootview= (ViewGroup) inflater.inflate(R.layout.fragment_detail,container,false);

        View v1=rootview.findViewById(R.id.post_comment);
        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Snackbar.make(v,"작성하기",Snackbar.LENGTH_LONG).show();
                Intent intent=new Intent(getContext(), WriteCommentActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        View v2=rootview.findViewById(R.id.readall);
        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"모두 보기",Snackbar.LENGTH_LONG).show();
                Intent intent=new Intent(getContext(), AllCommentActivity.class);
                intent.putParcelableArrayListExtra("comments",comments);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        comment_list=rootview.findViewById(R.id.comment_list);
        /*comments=new ArrayList<>();
        comments.add(new Comment("leye195","10분전","진짜 재밌더라...",4.5f));
        comments.add(new Comment("ljj195","15분전","뭐죠???? 이게 재밌다고??",3.5f));
        adapter=new CommentAdapter(getContext());
        for(Comment item:com)
        comment_list.setAdapter(adapter);*/
        return rootview;
    }

    @Override
    public void onDetach() {
        super.onDetach();
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
