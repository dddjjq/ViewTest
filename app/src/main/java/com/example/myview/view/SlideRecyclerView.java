package com.example.myview.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import com.example.myview.SlideLayoutManager;

public class SlideRecyclerView extends RecyclerView {

    private float oldX;
    private float oldY;
    private float newX;
    private float newY;
    private int touchSlop;
    private float dx;
    private float dy;
    private boolean canScroll = true;
    private SlideLayoutManager manager;

    public SlideRecyclerView(@NonNull Context context) {
        super(context);
        init();
    }

    public SlideRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        touchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        Log.d("dingyl","touchSlop : " + touchSlop);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        manager = (SlideLayoutManager) getLayoutManager();
        float currentX = e.getRawX();
        float currentY = e.getRawY();
        switch (e.getAction()){
            case MotionEvent.ACTION_DOWN:
                oldX = currentX;
                oldY = currentY;
                break;
            case MotionEvent.ACTION_MOVE:
                dx = currentX - oldX;
                dy = currentY - oldY;
                break;
            case MotionEvent.ACTION_UP:
                canScroll = false;
                manager.setCanScroll(canScroll);
                break;
        }
        return super.onTouchEvent(e);
    }

    private boolean judgeDirection(float dx,float dy){
        return false;
    }
}
