package com.example.leeyoungjae.my.View.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.leeyoungjae.my.R;
import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

public class PhotoFragment extends Fragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootview= (ViewGroup) inflater.inflate(R.layout.fragment_photo,container,false);
        PhotoView photoView=rootview.findViewById(R.id.photo_view);
        Bundle bundle=getArguments();
        if(bundle!=null){
            String url=bundle.getString("photo");
            Picasso.with(getContext())
                    .load(url)
                    .into(photoView);
        }
        return rootview;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
