package io.chuumong.booksearch.ui.fragment;


import android.support.v4.app.FragmentManager;

import javax.inject.Inject;

public class FragmentHolder {

    private final SearchFragment searchFragment;
    private final SearchListFragment searchListFragment;
    private final SettingFragment settingFragment;

    @Inject
    public FragmentHolder(SearchFragment searchFragment,
                          SearchListFragment searchListFragment,
                          SettingFragment settingFragment) {
        this.searchFragment = searchFragment;
        this.searchListFragment = searchListFragment;
        this.settingFragment = settingFragment;
    }

    public SearchFragment getSearchFragment() {
        return searchFragment;
    }

    public SearchListFragment getSearchListFragment() {
        return searchListFragment;
    }

    public SettingFragment getSettingFragment() {
        return settingFragment;
    }
}
