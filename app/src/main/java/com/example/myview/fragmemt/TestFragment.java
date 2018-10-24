package com.example.myview.fragmemt;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myview.R;

public class TestFragment extends Fragment {

    private TextView test;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        int count = bundle.getInt("currentItem");
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        test = view.findViewById(R.id.text_test);
        test.setText("第 " + count + " 项");
        return view;
    }
}
