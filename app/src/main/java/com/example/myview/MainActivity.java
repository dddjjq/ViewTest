package com.example.myview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button circleViewButton;
    private Button irTestBtn;
    private Button pieButton;

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
        }
    }
}
