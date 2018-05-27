package io.chuumong.booksearch.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import io.chuumong.booksearch.ui.fragment.SearchFragment;
import io.chuumong.booksearch.ui.fragment.SearchHistoryFragment;
import io.chuumong.booksearch.ui.fragment.SettingFragment;

/**
 * Created by jonghunlee on 2018-05-25.
 */
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
                return SearchHistoryFragment.newInstance();
            case 2:
                return SettingFragment.newInstance();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    //    private final SearchFragment searchFragment;
//    private final SearchHistoryFragment searchHistoryFragment;
//    private final SettingFragment settingFragment;


//    public SearchFragment getSearchFragment() {
//        return searchFragment;
//    }

}
