package com.example.myview;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.myview.adapter.LauncherPagerAdapter;
import com.example.myview.fragmemt.TestFragment;
import com.example.myview.view.NavigationBar;

import java.util.ArrayList;

public class LauncherActivity extends AppCompatActivity {

    private NavigationBar navigationBar;
    private ViewPager viewpager;
    private LauncherPagerAdapter adapter;
    private ArrayList<TestFragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        navigationBar = findViewById(R.id.navigation_bar);
        viewpager = findViewById(R.id.view_pager);
        initData();
        initListener();

    }

    private void initListener(){
        adapter = new LauncherPagerAdapter(getSupportFragmentManager(),fragments);
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(0);
        navigationBar.setViewPager(viewpager);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                navigationBar.showHideItem(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void initData(){
        fragments = new ArrayList<>();
        for (int i = 0;i<4;i++){
            TestFragment fragment = new TestFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("currentItem",i);
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
    }
}
