package io.chuumong.booksearch.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import javax.inject.Inject;

import io.chuumong.booksearch.ui.fragment.FragmentHolder;
import io.chuumong.booksearch.ui.fragment.SearchFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final FragmentHolder fragmentHolder;

    @Inject
    public ViewPagerAdapter(FragmentManager fm, FragmentHolder fragmentHolder) {
        super(fm);
        this.fragmentHolder = fragmentHolder;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return fragmentHolder.getSearchFragment();
            case 1:
                return fragmentHolder.getSearchHistoryFragment();
            case 2:
                return fragmentHolder.getSettingFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    public SearchFragment getSearchFragment() {
        return fragmentHolder.getSearchFragment();
    }
}
