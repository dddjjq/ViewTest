package com.example.myview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.myview.view.PieData;
import com.example.myview.view.PieView;

import java.util.ArrayList;

public class PieViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PieView view = new PieView(this);
        setContentView(view);

        ArrayList<PieData> datas = new ArrayList<>();
        PieData pieData = new PieData("sloop", 20);
        PieData pieData2 = new PieData("sloop", 30);
        PieData pieData3 = new PieData("sloop", 40);
        PieData pieData4 = new PieData("sloop", 50);
        PieData pieData5 = new PieData("sloop", 60);
        datas.add(pieData);
        datas.add(pieData2);
        datas.add(pieData3);
        datas.add(pieData4);
        datas.add(pieData5);
        view.setDatas(datas);
        //view.setStartAngle(30);
    }
}
