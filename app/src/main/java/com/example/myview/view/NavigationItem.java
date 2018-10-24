package com.example.myview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myview.R;

public class NavigationItem extends LinearLayout {

    private ImageView navigationIcon;
    private TextView navigationText;

    public NavigationItem(Context context) {
        super(context);
        init(context);
    }

    public NavigationItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NavigationItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context){
        LayoutInflater.from(context).inflate(R.layout.navigation_item,this,true);
        navigationIcon = findViewById(R.id.navigation_icon);
        navigationText = findViewById(R.id.navigation_text);
    }

    public void setNavigationIcon(int iconId){
        navigationIcon.setImageResource(iconId);
    }

    public void setNavigationText(String text){
        navigationText.setText(text);
    }

    class ViewWrapper{

        private TextView textView;

        public ViewWrapper(TextView textView){
            this.textView = textView;
        }

        public int getWidth(){
            return textView.getWidth();
        }

        public void setWidth(int width){
            textView.getLayoutParams().width = width;
            textView.requestLayout();
        }
    }
}
