package com.example.myview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.myview.R;

public class NavigationBar extends LinearLayout {

    NavigationItem navigationItem1;
    NavigationItem navigationItem2;
    NavigationItem navigationItem3;
    NavigationItem navigationItem4;
    private LinearLayout linearLayout;

    public NavigationBar(Context context) {
        super(context);
        init(context);
    }

    public NavigationBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NavigationBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context){
        Log.d("dingyl","bar init");
        View view = LayoutInflater.from(context).inflate(R.layout.navigation_bar,this,true);
        linearLayout = findViewById(R.id.navigation_bar_layout);
        navigationItem1 = new NavigationItem(context);
        navigationItem2 = new NavigationItem(context);
        navigationItem3 = new NavigationItem(context);
        navigationItem4 = new NavigationItem(context);
        LayoutParams params1 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LayoutParams params2 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LayoutParams params3 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LayoutParams params4 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        navigationItem1.setLayoutParams(params1);
        navigationItem2.setLayoutParams(params2);
        navigationItem3.setLayoutParams(params3);
        navigationItem4.setLayoutParams(params4);
        linearLayout.addView(navigationItem1);
        linearLayout.addView(navigationItem2);
        linearLayout.addView(navigationItem3);
        linearLayout.addView(navigationItem4);
    }

}
