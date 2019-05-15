package com.example.leeyoungjae.my.View.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class PhotoPager_Adapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment>list=new ArrayList<>();
    public PhotoPager_Adapter(FragmentManager fm) {
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

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title=String.valueOf(position+1)+"/"+list.size();
        return title;
    }
}
