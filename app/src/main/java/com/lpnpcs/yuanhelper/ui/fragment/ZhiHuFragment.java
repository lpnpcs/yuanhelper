package com.lpnpcs.yuanhelper.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;

import com.lpnpcs.yuanhelper.presenter.Contract.ZhiHuContract;

/**
 * Created by lpnpcs.
 * email:lpnpcs@gmail.com
 * description：the view of ZhiHuNews
 */
public class ZhiHuFragment extends  RecyclerFragment  implements ZhiHuContract.View,SwipeRefreshLayout.OnRefreshListener{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void setPresenter(ZhiHuContract.Presenter presenter) {

    }
}
