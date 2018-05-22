package io.chuumong.booksearch.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import io.chuumong.booksearch.ui.fragment.SearchFragment;
import io.chuumong.booksearch.ui.fragment.SearchListFragment;
import io.chuumong.booksearch.ui.fragment.SettingFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return SearchFragment.newInstance();
            case 1:
                return SearchListFragment.newInstance();
            case 2:
                return SettingFragment.newInstance();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
