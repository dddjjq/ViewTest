package com.example.myview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.myview.adapter.SlideRecyclerAdapter;
import com.example.myview.entry.SlideType;
import com.example.myview.view.SlideRecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class SlideActivity extends AppCompatActivity {

    private ArrayList<SlideType> slideTypes;
    private SlideRecyclerView recyclerView;
    public static final int NORMAL_TYPE = 0;
    public static final int OFFICIAL_TYPE = 1;
    private SlideRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);
        initView();
        initData();
        initListener();
    }

    private void initView(){
        recyclerView = findViewById(R.id.recycler);
    }

    private void initData(){
        Random random = new Random();
        slideTypes = new ArrayList<>();
        for (int i=0;i<20;i++){
            int x = random.nextInt(20);
            if (x % 2 == 0){
                x = NORMAL_TYPE;
            }else {
                x = OFFICIAL_TYPE;
            }
            SlideType slideType = new SlideType();
            slideType.setType(x);
            slideType.setText("第 " + i + " 项");
            slideTypes.add(slideType);
        }
    }

    private void initListener(){
        adapter = new SlideRecyclerAdapter(this,slideTypes);
        recyclerView.setAdapter(adapter);
        SlideLayoutManager layoutManager = new SlideLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    }
}
