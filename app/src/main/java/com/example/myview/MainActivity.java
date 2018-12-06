package com.example.myview;

import android.app.Notification;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button circleViewButton;
    private Button irTestBtn;
    private Button pieButton;
    private Button refreshButton;
    private Button socketButton;
    private Button launcherButton;
    private Button slideMenuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        circleViewButton = findViewById(R.id.circle_view);
        circleViewButton.setOnClickListener(this);
        irTestBtn = findViewById(R.id.ir_key_button);
        irTestBtn.setOnClickListener(this);
        pieButton = findViewById(R.id.pie_view);
        pieButton.setOnClickListener(this);
        refreshButton = findViewById(R.id.refresh_button);
        refreshButton.setOnClickListener(this);
        socketButton = findViewById(R.id.socket_button);
        socketButton.setOnClickListener(this);
        launcherButton = findViewById(R.id.launcher_test);
        launcherButton.setOnClickListener(this);
        slideMenuButton = findViewById(R.id.slide_menu);
        slideMenuButton.setOnClickListener(this);
        findViewById(R.id.viewgroup_test).setOnClickListener(this);
        findViewById(R.id.handler_test).setOnClickListener(this);
        findViewById(R.id.reader_layout).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.circle_view:
                Intent intent = new Intent(this,CircleViewActivity.class);
                startActivity(intent);
                break;
            case R.id.ir_key_button:
                Intent intent2 = new Intent(this,IRKeyActivity.class);
                startActivity(intent2);
                break;
            case R.id.pie_view:
                Intent intent3 = new Intent(this,PieViewActivity.class);
                startActivity(intent3);
                break;
            case R.id.refresh_button:
                Intent intent4 = new Intent(this, RefreshActivity.class);
                startActivity(intent4);
                break;
            case R.id.socket_button:
                Intent intent5 = new Intent(this,SocketTestActivity.class);
                startActivity(intent5);
                break;
            case R.id.launcher_test:
                Intent intent1 = new Intent(this,LauncherActivity.class);
                startActivity(intent1);
                break;
            case R.id.slide_menu:
                Intent intent6 = new Intent(this,SlideActivity.class);
                startActivity(intent6);
                break;
            case R.id.viewgroup_test:
                Intent intent7 = new Intent(this,ViewTestActivity.class);
                startActivity(intent7);
                break;
            case R.id.handler_test:
                Intent intent8 = new Intent(this,HandlerTestActivity.class);
                startActivity(intent8);
                break;
            case R.id.reader_layout:
                Intent intent9 = new Intent(this,ReadLayoutActivity.class);
                startActivity(intent9);
                break;
        }

    }
}
