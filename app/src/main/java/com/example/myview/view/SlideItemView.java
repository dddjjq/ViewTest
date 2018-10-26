package com.example.myview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.example.myview.R;

public class SlideItemView extends LinearLayout {

    private Scroller scroller;

    public SlideItemView(Context context) {
        super(context);
        init(context);
    }

    public SlideItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SlideItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context){
        scroller = new Scroller(context);
        //LayoutInflater.from(context).inflate(R.layout.slide_item,this,true);
    }

    public void smoothScrollTo(int fx,int fy){
        int dx = fx - scroller.getFinalX();
        int dy = fy - scroller.getFinalY();
        scroller.startScroll(scroller.getFinalX(),scroller.getFinalY(),dx,dy);
        invalidate();
    }

    public void smoothScrollBy(int dx,int dy){
        scroller.startScroll(scroller.getFinalX(),scroller.getFinalY(),dx,dy);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()){
            scrollTo(scroller.getCurrX(),scroller.getCurrY());
            postInvalidate();
        }
        super.computeScroll();
    }
}
