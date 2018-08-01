package io.chuumong.booksearch.ui.fragment;


import javax.inject.Inject;

public class FragmentHolder {

    private final SearchFragment searchFragment;
    private final SearchHistoryFragment searchHistoryFragment;
    private final SettingFragment settingFragment;

    @Inject
    public FragmentHolder(SearchFragment searchFragment,
                          SearchHistoryFragment searchHistoryFragment,
                          SettingFragment settingFragment) {
        this.searchFragment = searchFragment;
        this.searchHistoryFragment = searchHistoryFragment;
        this.settingFragment = settingFragment;
    }

    public SearchFragment getSearchFragment() {
        return searchFragment;
    }

    public SearchHistoryFragment getSearchHistoryFragment() {
        return searchHistoryFragment;
    }

    public SettingFragment getSettingFragment() {
        return settingFragment;
    }
}
