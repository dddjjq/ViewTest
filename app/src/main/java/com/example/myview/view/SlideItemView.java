package com.example.myview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;

import com.example.myview.R;
import com.example.myview.util.DensityUtil;

public class SlideItemView extends LinearLayout {

    private Scroller scroller;
    private int maxMenuWidth;
    private int moveX;
    private View mainView;
    private View menuView;
    private TextView contentText;
    private LinearLayout slideLayout;
    private int currentState;
    private static final int STATE_OPEN = 0;
    private static final int STATE_CLOSE = 1;
    private int touchSlop;
    private boolean isMenuOpen;

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
        isMenuOpen = false;
        Log.d("dingyl","init");
        scroller = new Scroller(context);
        maxMenuWidth = DensityUtil.dp2px(context,100+120+100);
        LayoutInflater.from(context).inflate(R.layout.slide_item_view,this,true);
        slideLayout = findViewById(R.id.slide_layout);
        mainView = LayoutInflater.from(context).inflate(R.layout.content_view,this,false);
        menuView = LayoutInflater.from(context).inflate(R.layout.menu_view,this,false);
        slideLayout.addView(mainView);
        slideLayout.addView(menuView);
        contentText = mainView.findViewById(R.id.slide_text);
        touchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }

    public void smoothScrollBy(int dx,int dy){
        moveX = dx;
        if (!isMenuOpen){
            //Log.d("dingyl","!isMenuOpen");
            if (dx > menuView.getWidth()){
                dx = menuView.getWidth();
            }
            if (dx < 0){
                dx = 0;
            }
            mainView.layout(-dx,mainView.getTop(),mainView.getWidth()-dx,getMeasuredHeight());
            menuView.layout(mainView.getWidth()-dx,menuView.getTop(),menuView.getWidth() + mainView.getWidth()-dx,getMeasuredHeight());
        }else {
            //Log.d("dingyl","isMenuOpen");
            if (dx > 0){
                dx = 0;
            }
            if (dx < -menuView.getWidth()){
                dx = -menuView.getWidth();
            }
            mainView.layout(-menuView.getWidth()-dx,mainView.getTop(),-menuView.getWidth()-dx+mainView.getWidth(),getMeasuredHeight());
            menuView.layout(-menuView.getWidth()-dx+mainView.getWidth(),menuView.getTop(),-dx+mainView.getWidth(),getMeasuredHeight());
        }
    }

    @Override
    public void computeScroll() {
        //Log.d("dingyl","computeScroll");
        if (scroller.computeScrollOffset()){
            scrollTo(scroller.getCurrX(),scroller.getCurrY());
            invalidate();
        }
    }

    public void setContentText(String text){
        contentText.setText(text);
    }

    public void smoothOpenMenu(){
       // Log.d("dingyl","open");
        scroller.startScroll(getScrollX(),0,menuView.getWidth() - moveX,0,350);
        invalidate();
        isMenuOpen = true;
        /*mainView.layout(-menuView.getWidth(), 0, mainView.getWidth()-menuView.getWidth(), getMeasuredHeight());
        menuView.layout(mainView.getWidth()-menuView.getWidth(), 0, mainView.getWidth(), getMeasuredHeight());
        invalidate();*/
    }

    public void smoothCloseMenu(){
        //Log.d("dingyl","close");
        scroller.startScroll(getScrollX(),0,moveX,0,350);
        invalidate();
        isMenuOpen = false;
        /*mainView.layout(0, 0, mainView.getWidth(), mainView.getMeasuredHeight());
        menuView.layout(mainView.getWidth(), 0, mainView.getWidth() + menuView.getWidth(), mainView.getMeasuredHeight());
        invalidate();*/
    }

    public void swipeMenu(MotionEvent event){
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (mainView.getLeft() < 0){
                    smoothCloseMenu();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
               // Log.d("dingyl","ACTION_UP");
                if (moveX > touchSlop){
                    if (moveX > menuView.getWidth()/2 ){
                        // Log.d("dingyl",">");
                        smoothOpenMenu();
                        currentState = STATE_OPEN;
                    }else {
                        //Log.d("dingyl","<");
                        smoothCloseMenu();
                        currentState = STATE_CLOSE;
                    }
                }
                moveX = 0;
                break;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (menuView != null){
            menuView.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                    MeasureSpec.makeMeasureSpec(getMeasuredHeight(), MeasureSpec.EXACTLY));
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if(mainView != null)
            mainView.layout(0, 0, getMeasuredWidth(), mainView.getMeasuredHeight());
        if(menuView != null)
            menuView.layout(getMeasuredWidth(), 0, getMeasuredWidth() + menuView.getMeasuredWidth(), mainView.getMeasuredHeight());
    }
}
