package com.example.myview.view;

import android.content.Context;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.myview.R;

import java.util.ArrayList;

public class NavigationBar extends LinearLayout {

    NavigationItem navigationItem0;
    NavigationItem navigationItem1;
    NavigationItem navigationItem2;
    NavigationItem navigationItem3;
    public static final int NAVID0 = 0;
    public static final int NAVID1 = 1;
    public static final int NAVID2 = 2;
    public static final int NAVID3 = 3;
    private int[] ids = {NAVID0,NAVID1,NAVID2,NAVID3};
    private String[] titles = {"电影","电视剧","游戏","我的"};
    private LinearLayout linearLayout;
    private ArrayList<NavigationItem> navigationItems;
    private ViewPager pager;

    public NavigationBar(Context context) {
        super(context);
        init(context);
        initListener();

    }

    public NavigationBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        initListener();
    }

    public NavigationBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context){
        Log.d("dingyl","bar init");
        LayoutInflater.from(context).inflate(R.layout.navigation_bar,this,true);
        linearLayout = findViewById(R.id.navigation_bar_layout);
        navigationItems = new ArrayList<>();
        navigationItem0 = new NavigationItem(context);
        navigationItem1 = new NavigationItem(context);
        navigationItem2 = new NavigationItem(context);
        navigationItem3 = new NavigationItem(context);
        navigationItems.add(navigationItem0);
        navigationItems.add(navigationItem1);
        navigationItems.add(navigationItem2);
        navigationItems.add(navigationItem3);
        for (int i=0;i<navigationItems.size();i++){
            NavigationItem ni = navigationItems.get(i);
            LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            ni.setLayoutParams(params);
            ni.setId(ids[i]);
            ni.setNavigationText(titles[i]);
            linearLayout.addView(ni);
        }
        navigationItems.get(0).showText();
    }

    public void setViewPager(ViewPager pager){
        this.pager = pager;
    }

    private void initListener(){
        for (int i=0;i<navigationItems.size();i++){
            final int item = i;
            navigationItems.get(i).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    pager.setCurrentItem(item);
                }
            });
        }
    }

    public void showHideItem(int position){
        for (NavigationItem ni : navigationItems){
            if (position == ni.getId()){
                Log.d("dingyl","position : " + position);
                ni.showText();
            }else {
                ni.hideText();
            }
        }
    }
}
