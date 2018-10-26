package com.example.myview.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import com.example.myview.SlideLayoutManager;
import com.example.myview.adapter.SlideRecyclerAdapter;

public class SlideRecyclerView extends RecyclerView {

    private float oldX;
    private float oldY;
    private float newX;
    private float newY;
    private int touchSlop;
    private float dx;
    private float dy;
    private SlideLayoutManager manager;
    private boolean canJudge = true;
    private int currentItem;
    private SlideRecyclerAdapter adapter;

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
        //Log.d("dingyl","touchSlop : " + touchSlop);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        manager = (SlideLayoutManager) getLayoutManager();
        adapter = (SlideRecyclerAdapter) getAdapter();
        float newScrollX;
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
                newScrollX = currentX - dx - oldX;
                Log.d("dingyl","dx : " + newScrollX);
                if ((Math.abs(dx) > touchSlop) && (Math.abs(dx) > Math.abs(dy))){
                    manager.setCanScroll(false);
                    canJudge = false;
                    adapter.slideMenu(dx);
                    Log.d("dingyl","dx : " + newScrollX);
                }
                break;
            case MotionEvent.ACTION_UP:
                manager.setCanScroll(true);
                break;

        }
        return super.onTouchEvent(e);
    }

}
