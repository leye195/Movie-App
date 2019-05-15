package com.example.leeyoungjae.my.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leeyoungjae.my.Model.Movie;
import com.example.leeyoungjae.my.R;
import com.example.leeyoungjae.my.View.Activity.DetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static android.view.View.inflate;

public class MovieListAdapter extends BaseAdapter {
    private List<Movie.itemInfo> list;
    private Context context;

    public MovieListAdapter(Context context,List<Movie.itemInfo>list){
        this.context=context;
        this.list=list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v;
        /*if(position==0){
             convertView=inflate(context,R.layout.item_list_top,null);
            return convertView;
        }else if(position==list.size()-1){
            convertView=inflate(context,R.layout.item_list_bottom,null);
            return convertView;
        }*/
        ViewHolder h=null;
        if(convertView==null||convertView.getTag()==null){
            convertView = inflate(context,R.layout.item_list_data, null);
            h=new ViewHolder();
            //h.movie_title=convertView.findViewById(R.id.movie_title);
            h.movie_title=convertView.findViewById(R.id.movie_title);
            h.movie_director=convertView.findViewById(R.id.movie_director);
            h.movie_actor=convertView.findViewById(R.id.movie_actor);
            h.img=convertView.findViewById(R.id.movie_img);
            convertView.setTag(h);
        }else{
            h=(ViewHolder)convertView.getTag();
        }
        //h.movie_title.setText(list.get(position).getTitie());
        String title=list.get(position).getTitie();
        String director=list.get(position).getDirector();
        String actor=list.get(position).getActor();
        if(title!=null)
            h.movie_title.setText(list.get(position).getTitie());
        if(director!=null)
            h.movie_director.setText("Director: "+list.get(position).getDirector());
        if(actor!=null)
            h.movie_actor.setText("Actors: "+list.get(position).getActor());
        if(list.get(position).getImage().length()>0) {
            Picasso.with(context)
                    .load(list.get(position).getImage())
                    //.fit()
                    .resize(600,500)
                    //.centerCrop()
                    .into(h.img);
        }
        final View finalConvertView = convertView;
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(list.get(position).getLink()));
                Intent intent=new Intent(context, DetailActivity.class);
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                finalConvertView.getContext().startActivity(intent);
            }
        });
        notifyDataSetChanged();
        return convertView;
    }

    class ViewHolder{
        TextView movie_title;
        //TextView movie_sub;
        TextView movie_director;
        TextView movie_actor;
        ImageView img;
    }
}
