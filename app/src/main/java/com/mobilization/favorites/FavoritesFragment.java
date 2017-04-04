package com.mobilization.favorites;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mobilization.R;
import com.mobilization.favorites.favorite.FavoriteFragment;
import com.mobilization.favorites.history.HistoryFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FavoritesFragment extends Fragment {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    ViewPagerAdapter adapter;


    public FavoritesFragment() {
        // Required empty public constructor
    }

    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (visible) {
            ((HistoryFragment)adapter.getItem(0)).displayData();
            ((FavoriteFragment)adapter.getItem(1)).displayData();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_favorite, container, false);
        ButterKnife.bind(this, rootView);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        return rootView;
    }

    private void setupViewPager (ViewPager pager) {
        adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new HistoryFragment(),getString(R.string.history));
        adapter.addFragment(new FavoriteFragment(),getString(R.string.favorite));
        pager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return 2;
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
