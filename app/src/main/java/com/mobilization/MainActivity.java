package com.mobilization;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mobilization.favorites.FavoritesFragment;
import com.mobilization.main.MainFragment;
import com.mobilization.views.CustomViewPager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Drawable> list = new ArrayList();
    private CustomViewPager viewPager;
    private TabLayout bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setBottomBar();
    }

    public void setBottomBar() {
        list.add(getResources().getDrawable(R.drawable.ic_translate_black_24dp));
        list.add(getResources().getDrawable(R.drawable.ic_turned_in_black_24dp));
        list.add(getResources().getDrawable(R.drawable.ic_settings_black_24dp));
        viewPager = (CustomViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        bottomBar = (TabLayout) findViewById(R.id.bottom_navigation);
        bottomBar.setupWithViewPager(viewPager);
        setIcons();
        viewPager.setCurrentItem(0);
        bottomBar.getTabAt(0).setIcon(setColorFilter(0, true));
    }

    private void setupViewPager(final ViewPager pager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MainFragment(), "translate");
        adapter.addFragment(new FavoritesFragment(), "favorite");
        adapter.addFragment(new Fragment(), "settings");
        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(adapter.getCount() - 1);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setIcons();
                bottomBar.getTabAt(position).setIcon(setColorFilter(position, true));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setIcons() {
        for (int i = 0; i < bottomBar.getTabCount(); i++) {
            bottomBar.getTabAt(i).setIcon(setColorFilter(i, false));
        }
    }

    private Drawable setColorFilter(int position, boolean selected) {
        Drawable dr = list.get(position);
        int color = getResources().getColor(R.color.colorLightGray);
        if (selected == true)
            color = getResources().getColor(android.R.color.black);
        dr.setColorFilter(new
                PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
        return dr;
    }
}
