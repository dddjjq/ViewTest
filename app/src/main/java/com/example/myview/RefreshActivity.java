package com.example.myview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.myview.R;
import com.example.myview.adapter.RefreshAdapter;
import com.example.myview.view.MyRecyclerView;

import java.util.ArrayList;

public class RefreshActivity extends AppCompatActivity {

    MyRecyclerView recyclerView;
    private ArrayList<String> datas;
    private RefreshAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);
        initView();
        initData();
    }

    private void initView(){
        recyclerView = findViewById(R.id.recycler);
    }

    private void initData(){
        datas = new ArrayList<>();
        for (int i = 0;i<20;i++){
            datas.add("第 " + i + "项");
        }
        adapter = new RefreshAdapter(this,datas,recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        MyLinearLayoutManager manager = new MyLinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setRefreshLayout(adapter);

    }
}
