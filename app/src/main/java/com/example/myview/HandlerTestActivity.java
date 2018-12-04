package com.example.myview;

import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HandlerTestActivity extends AppCompatActivity {

    private TextView textView;
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_test);
        textView = findViewById(R.id.text_handler_test);
        layout = findViewById(R.id.layout);
        //thread.start();
        textView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //calculateText();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            //Toast.makeText(HandlerTestActivity.this, "ceshi1", Toast.LENGTH_SHORT).show();
            Looper.prepare();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(HandlerTestActivity.this, "ceshi", Toast.LENGTH_SHORT).show();
                    //textView.setText("test");
                }
            },1000);
            Looper.loop();
        }
    });

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus){
            calculateText();
        }
    }

    private void calculateText(){
        float textSize = textView.getTextSize();
        float space = textView.getLineSpacingExtra();
        int lineCount = (int)(textView.getHeight()/(textSize + space + textSize/5.3)) ;
        //这里是因为textSize和text的高度不一样，但是设置includeFontPadding不起作用(textSize/5.3可以作为经验值)
        Log.d("dingyl","textView.getHeight() : " + textView.getHeight());
        Log.d("dingyl","textView.getMeasuredHeight() : " + textView.getMeasuredHeight());
        Log.d("dingyl","textSize + space : " + (textSize + space));
        Log.d("dingyl","linecount : " + lineCount);
        Log.d("dingyl","textView.getHeight() : " + textView.getHeight());
        Log.d("dingyl","layout.getHeight() : " + layout.getHeight());
        String text = getString(R.string.text_size_test);
        int begin = 0;
        int end;
        boolean isChapterEnd = false;
        String result = "";
        int lineTextCount = (int)(textView.getWidth()/textSize);
        int myLineCount = lineTextCount;
        for (int i=0;i<lineCount;i++){
            end = begin + myLineCount;
            String show = "";
            String chapter = "";
            if (text.length() > end){
                show = text.substring(begin,end);
            }else {
                show = text.substring(begin,text.length());
                isChapterEnd = true;
            }
            //Log.d("dingyl","show : " + show);
            if (show.contains("@")){
                if (begin == 0){
                    result = "\u3000\u3000" + result;
                }
                chapter = show.substring(0,show.indexOf("@"));
                begin += 1;
                result += chapter + "\n\u3000\u3000";
                myLineCount = lineTextCount - 2;
            }else {
                chapter = show;
                result += chapter + "\n";
                myLineCount = lineTextCount;
                if (begin == 0){
                    result = "\u3000\u3000" + result;
                    myLineCount = lineTextCount - 2;
                }
            }
            begin += chapter.length();
            if (isChapterEnd){
                break;
            }
        }
        textView.setText(result);
    }
}
