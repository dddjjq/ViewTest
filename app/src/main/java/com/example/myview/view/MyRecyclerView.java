package com.example.myview.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.example.myview.MyLinearLayoutManager;
import com.example.myview.util.DensityUtil;

import java.lang.ref.WeakReference;

public class MyRecyclerView extends RecyclerView {

    private MyLinearLayoutManager layoutManager;
    public MyHandler handler;
    private int firstVisibleItem;
    private OnRefreshLayout refreshLayout;
    float oldX = 0,oldY = 0;
    float currentX = 0,currentY = 0;
    private int deltaDp;
    private float overY,maxY;
    private Context context;

    public MyRecyclerView(@NonNull Context context) {
        super(context);
        handler = new MyHandler(this);
    }

    public MyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        handler = new MyHandler(this);
        this.context = context;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e){

        layoutManager = (MyLinearLayoutManager)getLayoutManager();
        switch (e.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.i("dingyl","ACTION_DOWN");
                oldX = e.getX();
                oldY = e.getRawY();
                Log.i("dingyl","oldY : " + oldY);
                break;
            case MotionEvent.ACTION_MOVE:
                //Log.i("dingyl","ACTION_MOVE");
                firstVisibleItem = layoutManager.findFirstVisibleItemPosition();
                currentX = e.getX();
                currentY = e.getRawY();
                if (currentY > maxY){
                    maxY = currentY;
                }
                overY = 0;
                deltaDp = DensityUtil.px2dp(context,currentY - oldY);
                if (deltaDp > 230){
                    /*overY = maxY - oldY - DensityUtil.dp2px(context,230);
                    oldY = overY + oldY;*/
                    oldY = maxY - DensityUtil.dp2px(context,230);
                }
                //Log.d("dingyl","firstVisibleItem : " + firstVisibleItem);
                Log.d("dingyl","oldY : " + oldY);
                Log.d("dingyl","currentY : " + currentY);
                Log.d("dingyl","maxY : " + maxY);
                Log.d("dingyl","230 : " + DensityUtil.dp2px(context,230));
                if (currentY > oldY && firstVisibleItem == 0){
                    refreshLayout.refreshLayout(currentY - oldY);
                }
                break;
            case MotionEvent.ACTION_UP:
                maxY = 0;
                refreshLayout.refreshStop();
                break;
        }
        return super.onTouchEvent(e);
    }

    public void setRefreshLayout(OnRefreshLayout refreshLayout){
        this.refreshLayout = refreshLayout;
    }

    public interface OnRefreshLayout{
        void refreshLayout(float delta);
        void refreshStop();
    }

    public static class MyHandler extends Handler{
        WeakReference<MyRecyclerView> myRecyclerViewWeakReference;

        public MyHandler(MyRecyclerView myRecyclerView){
            myRecyclerViewWeakReference = new WeakReference<>(myRecyclerView);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){

            }
        }
    }
}
