package com.example.myview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myview.R;
import com.example.myview.SlideActivity;
import com.example.myview.entry.SlideType;

import java.util.ArrayList;

public class SlideRecyclerAdapter extends RecyclerView.Adapter<SlideRecyclerAdapter.ViewHolder> {

    private Context context;
    private ArrayList<SlideType> slideTypes;

    public SlideRecyclerAdapter(Context context,ArrayList<SlideType> slideTypes){
        this.context = context;
        this.slideTypes = slideTypes;
    }

    @NonNull
    @Override
    public SlideRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.slide_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SlideRecyclerAdapter.ViewHolder viewHolder, int i) {
        int type = slideTypes.get(i).getType();
        if (type == SlideActivity.NORMAL_TYPE){
            //TODO to judge is normal or not

        }
        viewHolder.itemText.setText(slideTypes.get(i).getText());
    }

    @Override
    public int getItemCount() {
        return slideTypes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout layoutItem;
        TextView itemText;
        RelativeLayout slideMenuLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutItem = itemView.findViewById(R.id.slide_layout_main);
            itemText = itemView.findViewById(R.id.slide_text);
            slideMenuLayout = itemView.findViewById(R.id.slide_menu_layout);
        }
    }
}
