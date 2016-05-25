package com.lpnpcs.yuanhelper.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.lpnpcs.yuanhelper.R;
import com.lpnpcs.yuanhelper.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by lpnpcs.
 * email:lpnpcs@gmail.com
 * description：
 */
public class JokeFragment extends BaseFragment {
    public static final int TYPE_JOKE = 0;
    public static final int TYPE_JOKE_IMAGE = 1;
    @BindView(R.id.tab_layout)
     TabLayout mTablayout;
    @BindView(R.id.viewpager)
     ViewPager mViewPager;

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.fragment_joketab;
    }

    @Override
    protected void initViews() {
        mViewPager.setOffscreenPageLimit(1);
        setupViewPager(mViewPager);
        mTablayout.addTab(mTablayout.newTab().setText(R.string.joke));
        mTablayout.addTab(mTablayout.newTab().setText(R.string.joke_image));
        mTablayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void initData() {

    }
    private void setupViewPager(ViewPager mViewPager) {
        //Fragment中嵌套使用Fragment一定要使用getChildFragmentManager(),否则会有问题
        MyPagerAdapter adapter = new MyPagerAdapter(getChildFragmentManager());
        adapter.addFragment(JokeListFragment.newInstance(TYPE_JOKE), getString(R.string.joke));
        adapter.addFragment(JokeListFragment.newInstance(TYPE_JOKE_IMAGE), getString(R.string.joke_image));
        mViewPager.setAdapter(adapter);
    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
