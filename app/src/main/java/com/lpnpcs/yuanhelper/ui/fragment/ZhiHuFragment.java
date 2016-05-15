package com.lpnpcs.yuanhelper.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.lpnpcs.yuanhelper.R;
import com.lpnpcs.yuanhelper.base.BaseActivity;
import com.lpnpcs.yuanhelper.data.entity.ZhiHuEntity;
import com.lpnpcs.yuanhelper.di.component.DaggerZhiHuFragmetComponent;
import com.lpnpcs.yuanhelper.di.module.ZhiHuPresenterModule;
import com.lpnpcs.yuanhelper.presenter.Contract.ZhiHuContract;
import com.lpnpcs.yuanhelper.ui.OnListFragmentInteract;
import com.lpnpcs.yuanhelper.ui.activity.ZhiHuDetailActivity;
import com.lpnpcs.yuanhelper.ui.adpter.ZhiHuListAdapter;
import com.lpnpcs.yuanhelper.util.Constants;
import com.lpnpcs.yuanhelper.util.LogUtil;
import com.lpnpcs.yuanhelper.util.SettingUtil;
import com.lpnpcs.yuanhelper.util.SnackUtil;

/**
 * Created by lpnpcs.
 * email:lpnpcs@gmail.com
 * description：the view of ZhiHuNews
 */
public class ZhiHuFragment extends  RecyclerFragment  implements ZhiHuContract.View,SwipeRefreshLayout.OnRefreshListener,OnListFragmentInteract{
    private static final int PRELOAD_COUNT = 1;
    public static final int TYPE_ZHIHU = 1024;
    private ZhiHuContract.Presenter mPresenter;
    private LinearLayoutManager layoutManager;
    private ZhiHuListAdapter adapter;
    private ConvenientBanner banner;
    private BaseActivity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = DaggerZhiHuFragmetComponent.builder().zhiHuPresenterModule(new ZhiHuPresenterModule(this)).build().getZhiHuPresenter();
        LogUtil.d("presenter"+mPresenter);
    }
    @Override
    protected void initViews() {
        super.initViews();
        type = ZhiHuFragment.TYPE_ZHIHU;
        mActivity = (BaseActivity) getActivity();
        layoutManager = new LinearLayoutManager(mActivity);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ZhiHuListAdapter(this,new ZhiHuEntity());
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    onListScrolled();
                }
            }
        });
    }

    private void onListScrolled() {
        initBanner();
        firstPosition = layoutManager.findFirstVisibleItemPosition();
        lastPosition = layoutManager.findLastVisibleItemPosition();
        if (lastPosition + PRELOAD_COUNT == adapter.getItemCount()) {
            mPresenter.loadBefore();
        }
    }

    private void initBanner() {
        if (null == banner) {
            if (recyclerView.getChildCount() != 0 && layoutManager.findFirstVisibleItemPosition() == 0) {
                banner = (ConvenientBanner) layoutManager.findViewByPosition(0);
                banner.setScrollDuration(1000);
                banner.startTurning(5000);
            }
        }
    }

    @Override
    protected void initData() {
        initBanner();
        onRefresh();
    }

    @Override
    public void onRefresh() {
            mPresenter.loadNews();
    }

    @Override
    public void setPresenter(ZhiHuContract.Presenter presenter) {

    }

    @Override
    public void addNews(ZhiHuEntity zhiHuEntity) {
        adapter.setNews(zhiHuEntity);
    }

    @Override
    public void addBeforeNews(ZhiHuEntity zhiHuEntity) {
        adapter.addNews(zhiHuEntity);
    }

    @Override
    public void showProgress() {
        showProgress(true);
    }

    @Override
    public void hideProgress() {
        showProgress(false);
    }

    @Override
    public void loadFailed(String msg) {
        SnackUtil.showSnack(getActivity().getCurrentFocus(), R.string.load_fail);
    }

    @Override
    public void onListFragmentInteraction(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof ZhiHuListAdapter.ViewHolder) {
            ZhiHuListAdapter.ViewHolder holder = (ZhiHuListAdapter.ViewHolder) viewHolder;
            Intent intent = new Intent(getActivity(), ZhiHuDetailActivity.class);
            intent.putExtra("id", holder.zhihuStory.getId());
            intent.putExtra("title",holder.zhihuStory.getTitle());
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                    holder.mImage, getString(R.string.shared_img));
            ActivityCompat.startActivity(getActivity(), intent, optionsCompat.toBundle());

            holder.mTitle.setTextColor(ZhiHuListAdapter.textGrey);
        }
    }


    @Override
    public void onDestroyView() {
       SettingUtil.save(type + Constants.POSITION, firstPosition);
        if (banner!=null){
            banner.stopTurning();
        }
        super.onDestroyView();
    }
}
