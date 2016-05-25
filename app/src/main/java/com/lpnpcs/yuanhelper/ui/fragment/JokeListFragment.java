package com.lpnpcs.yuanhelper.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import com.lpnpcs.yuanhelper.R;
import com.lpnpcs.yuanhelper.data.entity.ImageEntity;
import com.lpnpcs.yuanhelper.data.entity.JokeEntity;
import com.lpnpcs.yuanhelper.di.component.DaggerJokeComponent;
import com.lpnpcs.yuanhelper.di.module.JokePresenterModule;
import com.lpnpcs.yuanhelper.presenter.Contract.JokeContract;
import com.lpnpcs.yuanhelper.presenter.JokePresenter;
import com.lpnpcs.yuanhelper.ui.activity.MainActivity;
import com.lpnpcs.yuanhelper.ui.adpter.ImageAdapter;
import com.lpnpcs.yuanhelper.ui.adpter.JokeAdapter;
import com.lpnpcs.yuanhelper.util.SnackUtil;

import java.util.ArrayList;

/**
 * Created by lpnpcs.
 * email:lpnpcs@gmail.com
 * description：
 */
public class JokeListFragment extends RecyclerFragment implements JokeContract.View {
    private int mType = JokeFragment.TYPE_JOKE;
    private JokePresenter jokePresenter;
    private ImageAdapter imageAdapter;
    private JokeAdapter jokeAdapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        jokePresenter = DaggerJokeComponent.builder().jokePresenterModule(new JokePresenterModule(this)).build().getJokePresenter();
        mType = getArguments().getInt("type");
    }

    public static JokeListFragment newInstance(int type) {
        Bundle args = new Bundle();
        JokeListFragment fragment = new JokeListFragment();
        args.putInt("type", type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initViews() {
        super.initViews();
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        if(mType ==JokeFragment.TYPE_JOKE){
            jokeAdapter = new JokeAdapter(getActivity().getApplicationContext());
            recyclerView.setAdapter(jokeAdapter);
        }else if(mType == JokeFragment.TYPE_JOKE_IMAGE){
            imageAdapter = new ImageAdapter(getActivity().getApplicationContext());
            recyclerView.setAdapter(imageAdapter);
        }
    }

    @Override
    protected void initData() {

        onRefresh();
    }

    @Override
    public void onRefresh() {
        jokePresenter.getJoke(mType);
    }

    @Override
    public void addJoke(ArrayList<JokeEntity> datas) {
       jokeAdapter.setmDate(datas);
    }

    @Override
    public void addImage(ArrayList<ImageEntity> datas) {
        imageAdapter.setmDate(datas);
    }

    @Override
    public void showProgress() {
        swipeRefresh.setRefreshing(true);
        shapeLoadingDialog.show();
    }

    @Override
    public void hideProgress() {
        swipeRefresh.setRefreshing(false);
        shapeLoadingDialog.dismiss();
    }

    @Override
    public void loadFailed(String msg) {
        SnackUtil.showSnack(((MainActivity) getActivity()).getDrawerLayout(), R.string.load_fail);

    }

    @Override
    public void setPresenter(JokeContract.Presenter presenter) {

    }
}
