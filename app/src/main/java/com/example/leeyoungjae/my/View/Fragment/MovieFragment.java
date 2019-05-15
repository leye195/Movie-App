package com.example.leeyoungjae.my.View.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leeyoungjae.my.Controller.onClickBtnCallBack;
import com.example.leeyoungjae.my.R;
import com.example.leeyoungjae.my.View.Activity.DetailActivity;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class MovieFragment extends Fragment {
    public onClickBtnCallBack callBack;
    @Override
    public void onAttach(Context context) {
        if(context instanceof onClickBtnCallBack)
            callBack= (onClickBtnCallBack) context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootview= (ViewGroup) inflater.inflate(R.layout.fragment_movie,container,false);
        ImageView img=rootview.findViewById(R.id.movie_img);
        final TextView title=rootview.findViewById(R.id.movie_title);
        final TextView rate=rootview.findViewById(R.id.rate);
        final TextView age=rootview.findViewById(R.id.watching_age);
        final Bundle bundle=getArguments();
        int img_res;
        if(bundle!=null){
            String movie_title=bundle.get("title").toString();
            String movie_rate=bundle.get("rate").toString();
            String movie_age=bundle.get("age").toString();
            String movie_img=bundle.get("img").toString();
            //int idx=bundle.getInt("idx");
            Log.d("From Fragment:",movie_title+" "+movie_rate+" "+movie_age);
            title.setText(movie_title);
            rate.setText("예매율: "+movie_rate+" |");
            age.setText(" "+movie_age+"세 관람가");
            Picasso.with(getContext())
                    .load(movie_img)
                    //.fit()
                    //.resize(600,500)
                    //.centerCrop()
                    .into(img);
            /*switch (idx){
                case 0:
                    img.setImageResource(R.drawable.image1);
                    break;
                case 1:
                    img.setImageResource(R.drawable.image2);
                    break;
                case 2:
                    img.setImageResource(R.drawable.image3);
                    break;
                case 3:
                    img.setImageResource(R.drawable.image4);
                    break;
                case 4:
                    img.setImageResource(R.drawable.image5);
                    break;
            }*/
        }
        Button btn=rootview.findViewById(R.id.detail_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), DetailActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|
                        Intent.FLAG_ACTIVITY_CLEAR_TOP|
                        Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("title",title.getText().toString());
                intent.putExtra("age",bundle.getString("age"));
                intent.putExtra("rate",bundle.getString("rate"));
                intent.putExtra("img",bundle.getString("img"));
                intent.putExtra("movieid",bundle.getString("movieid"));
                //intent.putExtra("img",bundle.getInt("idx"));
                startActivity(intent);
                //callBack.onChangeFragment(title.getText().toString(),bundle.getString("age"),bundle.getString("rate"),bundle.getInt("idx"));
            }
        });
        return rootview;
    }
    @Override
    public void onDetach() {
        super.onDetach();
    }
}
