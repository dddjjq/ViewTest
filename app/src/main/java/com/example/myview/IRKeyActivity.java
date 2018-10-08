package com.example.myview;

import android.app.Instrumentation;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.InputDevice;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class IRKeyActivity extends AppCompatActivity {

    private Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_irkey);
        send = findViewById(R.id.send_message);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendKeyEvent(KeyEvent.KEYCODE_VOLUME_MUTE);
            }
        });


    }

    @Override
    public void onResume(){
        super.onResume();
        sendKeyEvent(KeyEvent.KEYCODE_VOLUME_MUTE);
    }
    @Override
    public boolean onKeyDown(int keyCode,KeyEvent event){
        String deviceName = InputDevice.getDevice(event.getDeviceId()).getName();
        Toast.makeText(this,deviceName,Toast.LENGTH_SHORT).show();
        return super.onKeyDown(keyCode,event);
    }

    private static void sendKeyEvent(final int keyCode) {
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(1000);
                    Log.d("dingyl","keycode is " + keyCode);
                    Instrumentation inst = new Instrumentation();
                    inst.sendKeyDownUpSync(keyCode);
                } catch (Exception e) {
                    Log.e("dingyl", e.toString());
                }
            }
        }.start();
    }
}
