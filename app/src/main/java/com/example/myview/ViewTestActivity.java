package com.example.myview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

public class ViewTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_test);
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
