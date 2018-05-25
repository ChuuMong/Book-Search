package io.chuumong.booksearch.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import io.chuumong.booksearch.date.local.model.SearchHistory;
import io.chuumong.booksearch.ui.activity.MainActivity;
import io.chuumong.booksearch.ui.fragment.SearchFragment;
import io.chuumong.booksearch.ui.fragment.SearchHistoryFragment;
import io.chuumong.booksearch.ui.fragment.SettingFragment;

/**
 * Created by jonghunlee on 2018-05-25.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final SearchFragment searchFragment;
    private final SearchHistoryFragment searchHistoryFragment;
    private final SettingFragment settingFragment;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);

        searchFragment = SearchFragment.newInstance();
        searchHistoryFragment = SearchHistoryFragment.newInstance();
        settingFragment = SettingFragment.newInstance();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return searchFragment;
            case 1:
                return searchHistoryFragment;
            case 2:
                return settingFragment;
        }

        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }

    public SearchFragment getSearchFragment() {
        return searchFragment;
    }
}
