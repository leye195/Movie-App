package com.example.leeyoungjae.my.View.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.leeyoungjae.my.Model.Comment;
import com.example.leeyoungjae.my.View.CommentItemView;

import java.util.ArrayList;

public class CommentAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Comment>list=new ArrayList<>();

    public CommentAdapter(Context context){
        this.context=context;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        CommentItemView v=null;
        if(convertView==null){
            v=new CommentItemView(context);
        }else{
            v= (CommentItemView) convertView;
        }
        Comment item=list.get(position);
        v.setUserid(item.getWriter());
        v.setTime(item.getTime());
        v.setComment(item.getComment());
        v.setRate(item.getRate());
        return v;
    }
    public void addItem(Comment c){
        list.add(c);
    }
}
