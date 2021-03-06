package io.chuumong.booksearch.ui.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;
import io.chuumong.booksearch.R;
import io.chuumong.booksearch.data.local.model.SearchHistory;
import io.chuumong.booksearch.ui.adapter.ViewPagerAdapter;

public class MainActivity extends DaggerAppCompatActivity {

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bnView;

    @Inject
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        bnView.setSelectedItemId(R.id.action_search);
                        break;
                    case 1:
                        bnView.setSelectedItemId(R.id.action_search_list);
                        break;
                    case 2:
                        bnView.setSelectedItemId(R.id.action_setting);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        bnView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_search:
                    viewPager.setCurrentItem(0, true);
                    break;
                case R.id.action_search_list:
                    viewPager.setCurrentItem(1, true);
                    break;
                case R.id.action_setting:
                    viewPager.setCurrentItem(2, true);
                    break;
            }
            return true;
        });

        viewPager.setAdapter(viewPagerAdapter);
    }

    public void sendSearch(String query) {
        viewPager.setCurrentItem(0, true);
        viewPagerAdapter.getSearchFragment().getQuerySubject().onNext(query);
    }
}
