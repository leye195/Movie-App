package com.example.leeyoungjae.my.View.Activity;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.leeyoungjae.my.R;

import java.util.ArrayList;
import java.util.List;

public class MainInfoActivity extends AppCompatActivity {
    private TabLayout tab;
    private ViewPager viewPager;

    private String[]titles={"영화추천","평론","기타"};
    private List<Fragment>f_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_info);
        f_list=new ArrayList<>();
        tab=findViewById(R.id.tab);
        for(int i=0;i<titles.length;i++) {
            tab.addTab(tab.newTab().setText(titles[i]));
            f_list.add(new Fragment());
        }

        viewPager=findViewById(R.id.viewpager);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return f_list.get(i);
            }

            @Override
            public int getCount() {
                return f_list.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        });
        tab.setupWithViewPager(viewPager);
    }
}
