package com.example.myview;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.myview.adapter.LauncherPagerAdapter;
import com.example.myview.fragmemt.TestFragment;
import com.example.myview.view.NavigationBar;

import java.util.ArrayList;
import java.util.Locale;

public class LauncherActivity extends AppCompatActivity {

    private NavigationBar navigationBar;
    private  ViewPager viewpager;
    private LauncherPagerAdapter adapter;
    private ArrayList<TestFragment> fragments;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        navigationBar = findViewById(R.id.navigation_bar);
        viewpager = findViewById(R.id.view_pager);
        initData();
        initListener();

    }

    @Override
    public void onResume(){
        super.onResume();
    }

    private void initListener(){
        adapter = new LauncherPagerAdapter(getSupportFragmentManager(),fragments);
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(0);
        navigationBar.setViewPager(viewpager);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public synchronized void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                navigationBar.showHideItem(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        HandlerThread handlerThread = new HandlerThread("test");
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper()){
            @Override
            public void handleMessage(Message msg){
                Log.d("dingyl","thread is : " + Thread.currentThread());
                try{
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        handler.sendEmptyMessage(1);
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
