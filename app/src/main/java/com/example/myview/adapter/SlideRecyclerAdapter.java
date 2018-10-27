package com.example.myview.adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myview.R;
import com.example.myview.SlideActivity;
import com.example.myview.SlideLayoutManager;
import com.example.myview.entry.SlideType;
import com.example.myview.view.SlideItemView;

import java.util.ArrayList;

public class SlideRecyclerAdapter extends RecyclerView.Adapter<SlideRecyclerAdapter.ViewHolder> {

    private Context context;
    private ArrayList<SlideType> slideTypes;
    private int currentItem;
    private ViewHolder holder;
    private SlideLayoutManager manager;
    private RecyclerView recyclerView;

    public SlideRecyclerAdapter(Context context,ArrayList<SlideType> slideTypes,RecyclerView recyclerView){
        this.context = context;
        this.slideTypes = slideTypes;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public SlideRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.slide_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SlideRecyclerAdapter.ViewHolder viewHolder,int i) {
        //holder = viewHolder;
        /*int type = slideTypes.get(i).getType();
        if (type == SlideActivity.NORMAL_TYPE){
            //TODO to judge is normal or not
        }*/
        viewHolder.slideLayout.setContentText(slideTypes.get(i).getText());
    }

    @Override
    public int getItemCount() {
        return slideTypes.size();
    }

    public void slideMenu(float width,View view){
        //Log.d("dingyl","currentItem : " + currentItem);
        //Log.d("dingyl","width : " + width);
        /*ObjectAnimator animator = ObjectAnimator.ofInt(holder.slideLayout,"translationX",0,(int) width);
        animator.setDuration(300);
        animator.start();*/
        //holder.slideLayout.smoothScrollTo(-(int)width,0);
        holder = (ViewHolder) recyclerView.getChildViewHolder(view);
        //Log.d("dingyl","holder : " + holder.itemText.getText().toString());
        holder.slideLayout.smoothScrollBy(-(int)width,0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public SlideItemView slideLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            slideLayout = itemView.findViewById(R.id.slide_layout);
        }
    }
}
