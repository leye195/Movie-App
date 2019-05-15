package com.example.leeyoungjae.my.View.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class MovieViewPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment>list=new ArrayList<>();
    public MovieViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }

    @Override
    public int getCount() {
        return list.size();
    }
    public void addItem(Fragment item){
        list.add(item);
    }
}
