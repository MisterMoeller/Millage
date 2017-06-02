package com.moeller.millage.millage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.ViewGroup;


public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {
    VillageFragment village;
    MineFragment mine;
    ScienceFragment science;

    public SimpleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("ADAPTER", "GETITEM");
        switch (position) {
            case 0:
                return new VillageFragment();
            case 1:
                return new MineFragment();
            case 2:
                return new ScienceFragment();
            default:
                return null;
        }
    }
        @Override
        public int getCount () {
            return 3;
        }

    }
