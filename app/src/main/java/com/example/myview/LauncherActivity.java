package com.example.myview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.myview.view.NavigationBar;

public class LauncherActivity extends AppCompatActivity {

    private NavigationBar navigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        navigationBar = findViewById(R.id.navigation_bar);
    }
}
