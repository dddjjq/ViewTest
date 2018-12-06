package com.example.myview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

public class ReadView extends LinearLayout {

    private BaseReadView prePage,currPage,nextPage;
    private int preLeft,currLeft,width;
    private boolean isInit = true;

    public ReadView(Context context) {
        super(context);
        init(context);
    }

    public ReadView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(isInit){
            isInit = false;
            width = getMeasuredWidth();
            preLeft = -width;
            currLeft = 0;
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        prePage.layout(-width+100,getTop(),100,getBottom());
        currPage.layout(100,getTop(),width+100,getBottom());
        nextPage.layout(width+100,getTop(),width+100+width,getBottom());
        Log.d("dingyl","prePage.getWidth() : " + nextPage.getLeft());
        Log.d("dingyl","prePage.getHeight() : " + nextPage.getWidth());
    }

    private void init(Context context){
        setOrientation(HORIZONTAL);
        prePage = new BaseReadView(context);
        currPage = new BaseReadView(context);
        nextPage = new BaseReadView(context);
        addView(prePage);
        addView(currPage);
        addView(nextPage);
    }
}
