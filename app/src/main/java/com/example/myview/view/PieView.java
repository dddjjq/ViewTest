package com.example.myview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class PieView extends View {

    private int[] colors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};

    private float startAngle = 0;

    private ArrayList<PieData> datas;

    private int width,height;

    private Paint paint = new Paint();

    private PointF startPointf = new PointF(20,20);
    private PointF currentPointf = new PointF(startPointf.x,startPointf.y);


    public PieView(Context context) {
        super(context);
    }

    public PieView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
    }

    @Override
    public void onMeasure(int widthMeasureSpec,int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
    }

    @Override
    public void onSizeChanged(int w,int h,int oldW,int oldH){
        super.onSizeChanged(w,h,oldW,oldH);
        width = w;
        height = h;
    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        if (datas == null)
            return;
        float currentStartAngle = startAngle;
        canvas.translate(width/2,height/2);
        float r = (float)(Math.min(width,height)/2*0.8);
        RectF rectF = new RectF(-r,-r,r,r);
        float sumValue = 0;

        for (PieData pd:datas){
            sumValue += pd.getValue();
        }
        for (int i =0;i<datas.size();i++){
            PieData pieData = datas.get(i);
            paint.setColor(pieData.getColor());
            canvas.drawArc(rectF,currentStartAngle,pieData.getAngle(),true,paint);
            currentStartAngle += pieData.getAngle();
            canvas.save();
            canvas.rotate(90 + currentStartAngle - pieData.getAngle()/2);
            paint.setTextSize(50);
            canvas.drawText((int)(pieData.getValue()/sumValue*100)+"%",0-(pieData.getValue()+"").length()*25/2,-r-50,paint);
            canvas.restore();

        }
    }

    public void setStartAngle(int startAngle){
        this.startAngle = startAngle;
        invalidate();
    }

    public void setDatas(ArrayList<PieData> datas){
        this.datas = datas;
        initData(datas);
        invalidate();
    }

    private void initData(ArrayList<PieData> datas){
        if (datas == null || datas.size() == 0 )
            return;
        float sumValue = 0;
        for (int i =0 ;i<datas.size();i++){
            PieData pieData = datas.get(i);
            sumValue += pieData.getValue();
            int j = i % colors.length;
            pieData.setColor(colors[i]);
        }
        float sumAngle = 0;
        for (int i = 0;i<datas.size();i++){
            PieData pieData = datas.get(i);
            float percentage = pieData.getValue()/sumValue;
            float angle = percentage * 360;
            pieData.setPercentage(percentage);
            pieData.setAngle(angle);
            sumAngle += angle;
            Log.d("dingyl","" + pieData.getAngle());
        }
    }
}
