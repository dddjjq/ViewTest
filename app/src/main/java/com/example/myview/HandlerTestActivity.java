package com.example.myview;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class HandlerTestActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_test);
        textView = findViewById(R.id.text_handler_test);
        thread.start();
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
}
