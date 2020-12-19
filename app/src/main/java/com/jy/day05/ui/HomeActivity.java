package com.jy.day05.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.jy.day05.R;
import com.jy.day05.ui.fragment.FaxianFragment;
import com.jy.day05.ui.fragment.Recommendragment;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    private void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new Recommendragment());
        fragments.add(new FaxianFragment());
        fragments.add(new Recommendragment());
        fragments.add(new Recommendragment());
        fragments.add(new Recommendragment());

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });

        mTabLayout.setupWithViewPager(mViewPager);

        mTabLayout.getTabAt(0).setText("推荐");
        mTabLayout.getTabAt(1).setText("广场");
        mTabLayout.getTabAt(2).setText("视频");
        mTabLayout.getTabAt(3).setText("影视");
        mTabLayout.getTabAt(4).setText("知识文章");
    }
}
