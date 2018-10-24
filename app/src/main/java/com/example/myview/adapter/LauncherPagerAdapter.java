package com.example.myview.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.example.myview.fragmemt.TestFragment;

import java.util.ArrayList;
import java.util.List;

public class LauncherPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<TestFragment> fragments;

    public LauncherPagerAdapter(FragmentManager fm,ArrayList<TestFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
