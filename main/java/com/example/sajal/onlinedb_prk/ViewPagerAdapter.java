package com.example.sajal.onlinedb_prk;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Sajal on 24-06-2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    String titles [] = {"Delhi","Mumbai", "Chennai"};

    public ViewPagerAdapter(android.support.v4.app.FragmentManager fm){super(fm);}
    @Override
    public Fragment getItem(int position) {

        return  new DataFragment(position);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
