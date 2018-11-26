package com.example.myview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ViewTestActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_test);
        imageView = findViewById(R.id.image_test);
        //saveBitmap();
        getBoundBitmap();
    }

    private void saveBitmap(){
        /*Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.icon_navigation);
        File file = new File(getFilesDir(),"test.png");
        Log.d("dingyl","getFilesDir : " + getFilesDir());
        try {
            if (!file.exists()){
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        bitmap.recycle();*/
        /*Bitmap bitmap = null;
        try{
            FileInputStream fi = new FileInputStream(new File(getFilesDir()+"/test.png"));
            bitmap = BitmapFactory.decodeStream(fi);
            imageView.setImageBitmap(bitmap);
            fi.close();
        }catch (IOException e){
            e.printStackTrace();
        }*/
        imageView.setImageBitmap(scaleBitmap(1.0f));
    }

    private Bitmap scaleBitmap(float rate){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.icon_navigation);
        Matrix matrix = new Matrix();
        matrix.postScale(rate,rate);
        Bitmap bitmap1 = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
        return bitmap1;
    }

    private void getBoundBitmap(){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.test);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap output = Bitmap.createBitmap(width,height, bitmap.getConfig());
        Canvas canvas = new Canvas(output);
        canvas.translate(imageView.getLeft()+500,imageView.getTop());
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        RectF rectF = new RectF(imageView.getLeft(),imageView.getTop(),imageView.getLeft()+1000,imageView.getTop()+1000);
        Matrix matrix = new Matrix();
        canvas.drawRoundRect(rectF,500,500,paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap,matrix,paint);
        imageView.setImageBitmap(output);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("dingyl","activity dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("dingyl","activity onTouchEvent");
        return super.onTouchEvent(event);
    }

    @Override
    public void onUserInteraction() {
        Log.d("dingyl","activity onUserInteraction");
        super.onUserInteraction();
    }
}
