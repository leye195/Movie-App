package com.example.leeyoungjae.my.View.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.example.leeyoungjae.my.Model.MovieDetail;
import com.example.leeyoungjae.my.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class gallery_viewAdapter extends RecyclerView.Adapter<gallery_viewAdapter.ViewHolder> {
    private Context context;
    private ArrayList<String>img_video_list=new ArrayList<>();
    private OnItemClickListener listener;

    public static interface OnItemClickListener{
        public void onItemClick(ViewHolder viewholder,View v,int pos);
    }
    public gallery_viewAdapter(Context context) {
        this.context=context;
    }
    public void addItem(String item){
        img_video_list.add(item);
    }
    public void addItems(ArrayList<String>list){
        img_video_list=list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=inflater.inflate(R.layout.gallery_item,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String item=img_video_list.get(i);
        viewHolder.setItem(item);
        viewHolder.setOnItemClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return img_video_list.size();
    }

    public String getItem(int pos){
        return img_video_list.get(pos);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private OnItemClickListener listener;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.v_img);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=getAdapterPosition();
                    if(listener!=null){
                        listener.onItemClick(ViewHolder.this,v,pos);
                    }
                }
            });
        }
        public void setItem(String url){
            Picasso.with(itemView.getContext())
                    .load(url)
                    .into(img);
        }
        public void setOnItemClickListener(OnItemClickListener listener){
            this.listener=listener;
        }
    }
}
