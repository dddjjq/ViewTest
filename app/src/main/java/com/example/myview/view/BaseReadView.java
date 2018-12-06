package com.example.myview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.example.myview.R;

public class BaseReadView extends LinearLayout {

    public BaseReadView(Context context) {
        super(context);
        init(context);
    }

    public BaseReadView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        LayoutInflater.from(context).inflate(R.layout.read_view_layout,this,true);
    }
}
