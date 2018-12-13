package com.gotechcn.frameworks.other.MaterialDesign;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.gotechcn.frameworks.R;

public class TabLayoutActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);

        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.view_pager);
        mViewPager.setAdapter(new MyViewPager2(getSupportFragmentManager()));

        // 设置viewpager
        mTabLayout.setupWithViewPager(mViewPager);
        // 设置指示器的颜色
        mTabLayout.setSelectedTabIndicatorColor(R.color.colorAccent);
    }



    /**
     * 一般，Fragment数量多，需要动态加载，使用FragmentStatePagerAdapter
     */
    class MyViewPager extends FragmentStatePagerAdapter{

        public MyViewPager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return null;
        }

        @Override
        public int getCount() {
            return 0;
        }
    }

    /**
     * 一般，Fragment数量少，页面静态使用FragmentPagerAdapter
     */
    class MyViewPager2 extends FragmentPagerAdapter{

        public MyViewPager2(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return TablayoutFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return "TAB" + position;
        }
    }
}
