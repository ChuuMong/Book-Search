package io.chuumong.booksearch.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import javax.inject.Inject;

import io.chuumong.booksearch.ui.fragment.FragmentHolder;
import io.chuumong.booksearch.ui.fragment.SearchFragment;
import io.chuumong.booksearch.ui.fragment.SearchListFragment;
import io.chuumong.booksearch.ui.fragment.SettingFragment;

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
                return fragmentHolder.getSearchListFragment();
            case 2:
                return fragmentHolder.getSettingFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // super.destroyItem(container, position, object);
    }
}
