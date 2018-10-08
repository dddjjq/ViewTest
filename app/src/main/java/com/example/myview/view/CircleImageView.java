package com.example.myview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.myview.R;

public class CircleImageView extends View {

    private static final String TAG = "CircleImageView";
    private Paint paint;
    private Drawable drawable;
    private int width;
    private int height;

    public CircleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    private void initAttrs(AttributeSet attrs){
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.CircleImageView);
        drawable = ta.getDrawable(R.styleable.CircleImageView_src);
        ta.recycle();
        measureDrawable();
    }

    private void measureDrawable(){
        if (drawable == null){
            throw new RuntimeException("Source 不能为空");
        }
        width = drawable.getIntrinsicWidth();
        height = drawable.getIntrinsicHeight();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec,int heightMeasureSpec){
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int mWidth = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int mHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(measureWidth(widthMode,mWidth),measureHeight(heightMode,mHeight));
    }

    private int measureWidth(int widthMode,int width){
        switch (widthMode){
            case MeasureSpec.UNSPECIFIED:
            case MeasureSpec.EXACTLY:
                break;
            case MeasureSpec.AT_MOST:
                this.width = width;
        }
        return width;
    }

    private int measureHeight(int heightMode,int height){
        switch (heightMode){
            case MeasureSpec.UNSPECIFIED:
            case MeasureSpec.EXACTLY:
                break;
            case MeasureSpec.AT_MOST:
                this.height = height;
        }
        return height;
    }
    @Override
    public void onDraw(Canvas canvas){
        if (drawable == null){
            return;
        }
        BitmapDrawable bd = (BitmapDrawable)drawable;
        Bitmap bitmap = Bitmap.createScaledBitmap(bd.getBitmap(),getMeasuredWidth(),getMeasuredHeight(),true);
        Log.d(TAG,"getMeasuredWidth is : " + getMeasuredWidth());
        Log.d(TAG,"getWidth is : " + getWidth());

        Log.d(TAG,"getMeasuredHeight is : " + getMeasuredHeight());
        Log.d(TAG,"getHeight is : " + getHeight());
        Log.d(TAG,"scale is : " + getResources().getDisplayMetrics().density);
        canvas.drawBitmap(bitmap,getLeft(),getTop(),paint);

    }
}
