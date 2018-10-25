package com.example.myview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;

public class SlideLayoutManager extends LinearLayoutManager {

    private boolean canScroll = true;

    public SlideLayoutManager(Context context) {
        super(context);
    }

    public SlideLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public SlideLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setCanScroll(boolean canScroll){
        this.canScroll = canScroll;
    }

    @Override
    public boolean canScrollVertically() {
        return canScroll&&super.canScrollVertically();
    }
}
