package com.bapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Pair;

import java.util.List;

/**
 * @author BuMingYang
 * @des HomeFragment中的ViewPage适配器
 */

public class HomeAdapter extends FragmentPagerAdapter {
    private List<Pair<String, Fragment>> mList;

    public HomeAdapter(FragmentManager fm, List<Pair<String, Fragment>> mList) {
        super(fm);
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position).second;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mList.get(position).first;
    }
}
