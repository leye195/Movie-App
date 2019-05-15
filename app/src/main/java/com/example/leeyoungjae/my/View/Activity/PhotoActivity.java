package com.example.leeyoungjae.my.View.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.leeyoungjae.my.R;
import com.example.leeyoungjae.my.View.Adapter.PhotoPager_Adapter;
import com.example.leeyoungjae.my.View.Fragment.PhotoFragment;
import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PhotoActivity extends AppCompatActivity {
    //private PhotoView photoView;
    private ViewPager viewPager;
    private PhotoPager_Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        //photoView=findViewById(R.id.photo_view);
        viewPager=findViewById(R.id.photo_pager);
        adapter=new PhotoPager_Adapter(getSupportFragmentManager());
        Intent intent=getIntent();
        if(intent!=null){
            /*String url=intent.getStringExtra("photo");
            Picasso.with(getApplicationContext())
                    .load(url)
                    .into(photoView);*/
            ArrayList<String>urls =intent.getStringArrayListExtra("photos");
            int pos=intent.getIntExtra("pos",0);
            if(urls.size()>0){
                for(String url:urls){
                    PhotoFragment fragment=new PhotoFragment();
                    Bundle bundle=new Bundle();
                    bundle.putString("photo",url);
                    fragment.setArguments(bundle);
                    adapter.addItem(fragment);
                }
            }
            Log.d("Now:",String.valueOf(pos));
            viewPager.setOffscreenPageLimit(3);
            viewPager.setAdapter(adapter);
            viewPager.setCurrentItem(pos,false);
        }

    }
}
