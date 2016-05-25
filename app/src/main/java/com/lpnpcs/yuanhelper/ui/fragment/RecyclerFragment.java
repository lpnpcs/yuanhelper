package com.lpnpcs.yuanhelper.ui.fragment;


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.lpnpcs.yuanhelper.R;
import com.lpnpcs.yuanhelper.base.BaseFragment;
import com.lpnpcs.yuanhelper.util.Constants;
import com.lpnpcs.yuanhelper.util.SettingUtil;
import com.lpnpcs.yuanhelper.widget.loadview.ShapeLoadingDialog;

import butterknife.BindView;



/**
 * All fragments have recyclerView & swipeRefresh must implement this.
 */
public abstract class RecyclerFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.list)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    boolean isFirst = true;   //whether is first time to enter fragment
    int type;               // type of recyclerView's content
    int lastPosition;       //last visible position
    int firstPosition;      //first visible position

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.fragment_recycler;
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState == null) {
            //restoring position when reentering fragment.
            lastPosition = SettingUtil.getInt(type + Constants.POSITION);
            if (lastPosition > 0) {
                recyclerView.scrollToPosition(lastPosition);
            }
        }
    }

    @Override
    public void onDestroyView() {

        super.onDestroyView();
    }

    @Override
    public void onPause() {
        super.onPause();
        SettingUtil.save(type + Constants.POSITION, firstPosition);
    }

    @Override
    protected void initViews() {
        recyclerView.setHasFixedSize(true);
        //noinspection ResourceAsColor
        swipeRefresh.setColorSchemeColors(R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorAccent);
        swipeRefresh.setOnRefreshListener(this);

    }

    public void showProgress(final boolean refreshState) {
        if (null != swipeRefresh) {
            swipeRefresh.setRefreshing(refreshState);
        }
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }
}
