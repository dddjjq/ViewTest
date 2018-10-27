package com.example.myview.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import com.example.myview.SlideLayoutManager;
import com.example.myview.adapter.SlideRecyclerAdapter;
import com.example.myview.util.DensityUtil;

public class SlideRecyclerView extends RecyclerView {

    private float oldX;
    private float oldY;
    private int touchSlop;
    private float dx;
    private float dy;
    private SlideLayoutManager manager;
    private int currentItem;
    private SlideRecyclerAdapter adapter;
    private static int itemHeight;
    private SlideRecyclerAdapter.ViewHolder viewHolder;
    private View view;

    public SlideRecyclerView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public SlideRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        touchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        itemHeight = DensityUtil.dp2px(context,60);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        manager = (SlideLayoutManager) getLayoutManager();
        adapter = (SlideRecyclerAdapter) getAdapter();
        if (viewHolder != null){
            viewHolder.slideLayout.swipeMenu(e);
        }
        float currentX = e.getX();
        float currentY = e.getY();
        switch (e.getAction()){
            case MotionEvent.ACTION_DOWN:
                oldX = currentX;
                oldY = currentY;
                currentItem = getCurrentItem(manager,e);
                view = findChildViewUnder(oldX,oldY);
                viewHolder = (SlideRecyclerAdapter.ViewHolder)getChildViewHolder(view);
                break;
            case MotionEvent.ACTION_MOVE:
                dx = currentX - oldX;
                dy = currentY - oldY;
                if ((Math.abs(dx) > touchSlop) && (Math.abs(dx) > Math.abs(dy))){
                    //Log.d("dingyl","move");
                    manager.setCanScroll(false);
                    viewHolder.slideLayout.smoothScrollBy(-(int)dx,0);
                }
                break;
            case MotionEvent.ACTION_UP:
                manager.setCanScroll(true);
                break;
        }
        return super.onTouchEvent(e);
    }

    private int getCurrentItem(SlideLayoutManager manager,MotionEvent event){
        int result;
        int firstVisibleItem = manager.findFirstVisibleItemPosition();
        View view = getChildAt(firstVisibleItem);
        float currentY = event.getY();
        result = (int) ((currentY - view.getY())/ itemHeight) + firstVisibleItem;
        return result;
    }

}
