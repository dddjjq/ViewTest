package com.example.myview.adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myview.R;
import com.example.myview.util.DensityUtil;
import com.example.myview.view.MyRecyclerView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class RefreshAdapter extends RecyclerView.Adapter implements MyRecyclerView.OnRefreshLayout{

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_NORMAL = 1;
    private static final int TYPE_FOOTER = 2;
    private static final int HIDE_HEAD = 0;
    private ArrayList<String> datas;
    private Context context;
    private LayoutInflater inflater;
    private HeadViewHolder headViewHolder1,headViewHolder2;
    private RecyclerView recyclerView;
    private MyHandler handler;
    private int overY;
    private boolean canRefresh;

    public RefreshAdapter(Context context,ArrayList<String> datas,RecyclerView recyclerView){
        this.datas = datas;
        this.context = context;
        inflater = LayoutInflater.from(context);
        handler = new MyHandler(this);
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == TYPE_HEADER || viewType == TYPE_FOOTER){
            View view = inflater.inflate(R.layout.normal_header,viewGroup,false);
            RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,0);
            view.setLayoutParams(layoutParams);
            return new HeadViewHolder(view);
        }
        View view = inflater.inflate(R.layout.normal_item,viewGroup,false);
        return new NormalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        switch (getItemViewType(position)){
            case TYPE_HEADER:
                headViewHolder1 = (HeadViewHolder)viewHolder;
                headViewHolder1.refreshIcon.setImageResource(R.drawable.refresh);
                headViewHolder1.refreshText.setText("下拉刷新");
                break;
            case TYPE_FOOTER:
                headViewHolder2 = (HeadViewHolder)viewHolder;
                headViewHolder2.refreshIcon.setImageResource(R.drawable.load_more);
                headViewHolder2.refreshText.setText("上拉加载");
                break;
            case TYPE_NORMAL:
                final int realPosition = getRealPosition(position);
                NormalViewHolder normalViewHolder = (NormalViewHolder)viewHolder;
                normalViewHolder.normalText.setText(datas.get(realPosition));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return datas.size() + 2;
    }

    private int getRealPosition(int position){
        return position - 1;
    }

    @Override
    public int getItemViewType(int position){
        if (position == 0){
            return TYPE_HEADER;
        }else if (position == datas.size() + 1){
            return TYPE_FOOTER;
        }else {
            return TYPE_NORMAL;
        }
    }

    @Override
    public void refreshLayout(float delta) {
        //handler.removeMessages(HIDE_HEAD);
        headViewHolder1.refreshIcon.setVisibility(View.VISIBLE);
        headViewHolder1.refreshText.setVisibility(View.VISIBLE);
        headViewHolder1.loadingImage.setVisibility(View.GONE);
        //Log.d("dingyl","delta : " + DensityUtil.px2dp(context,delta));
        int deltaY = DensityUtil.px2dp(context,delta);
        if (deltaY > 200){
            //Log.i("dingyl","> 200 " + deltaY + "");
            headViewHolder1.refreshIcon.setImageResource(R.drawable.load_more);
            headViewHolder1.refreshText.setText("松开刷新");
            canRefresh = true;
        }else {
            //Log.i("dingyl","< 200 " + deltaY + "");
            headViewHolder1.refreshIcon.setImageResource(R.drawable.refresh);
            headViewHolder1.refreshText.setText("上拉刷新");
            canRefresh = false;
        }
        headViewHolder1.setVisibleHeight(deltaY);

    }

    @Override
    public void refreshStop() {
        if (canRefresh){
            headViewHolder1.refreshIcon.setVisibility(View.GONE);
            headViewHolder1.refreshText.setVisibility(View.GONE);
            headViewHolder1.loadingImage.setVisibility(View.VISIBLE);
            ObjectAnimator rotate = ObjectAnimator.ofFloat(headViewHolder1.loadingImage,"rotation",360f,0f);
            rotate.setDuration(1000);
            rotate.setRepeatCount(-1);
            rotate.start();
            handler.sendEmptyMessageDelayed(HIDE_HEAD,1000);
        }else {
            handler.sendEmptyMessage(HIDE_HEAD);
        }

    }

    class NormalViewHolder extends RecyclerView.ViewHolder{
        TextView normalText;

        public NormalViewHolder(@NonNull View itemView) {
            super(itemView);
            normalText = itemView.findViewById(R.id.normal_item);
        }
    }

    class HeadViewHolder extends RecyclerView.ViewHolder{
        ImageView loadingImage,refreshIcon;
        TextView refreshText;

        public HeadViewHolder(@NonNull View itemView) {
            super(itemView);
            loadingImage = itemView.findViewById(R.id.loading_image);
            refreshIcon = itemView.findViewById(R.id.refresh_icon);
            refreshText = itemView.findViewById(R.id.refresh_text);
        }

        public void setVisibleHeight(int height){
            if (height <= 230){
                RecyclerView.LayoutParams lp  = (RecyclerView.LayoutParams) itemView.getLayoutParams();
                lp.height = height;
                itemView.setLayoutParams(lp);
            }else {
                overY = height - 230;
            }
        }
    }

    static class MyHandler extends Handler{

        WeakReference<RefreshAdapter> refreshAdapterWeakReference;
        RefreshAdapter adapter;

        public MyHandler(RefreshAdapter adapter){
            refreshAdapterWeakReference = new WeakReference<>(adapter);

        }

        @Override
        public void handleMessage(Message msg) {
            adapter = refreshAdapterWeakReference.get();
            switch (msg.what){
                case HIDE_HEAD:
                    adapter.headViewHolder1.setVisibleHeight(0);
                    adapter.recyclerView.smoothScrollToPosition(0);
                    break;
            }
        }
    }
}
