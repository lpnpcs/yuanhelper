package com.lpnpcs.yuanhelper.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lpnpcs.yuanhelper.data.entity.IJEntity;
import com.lpnpcs.yuanhelper.di.component.DaggerJokeComponent;
import com.lpnpcs.yuanhelper.di.module.JokePresenterModule;
import com.lpnpcs.yuanhelper.presenter.Contract.JokeContract;
import com.lpnpcs.yuanhelper.presenter.JokePresenter;

/**
 * Created by lpnpcs.
 * email:lpnpcs@gmail.com
 * description：
 */
public class JokeListFragment extends RecyclerFragment implements JokeContract.View{
    private int mType = JokeFragment.TYPE_JOKE;
    private JokePresenter jokePresenter;

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
    protected void initData() {
        onRefresh();
    }

    @Override
    public void onRefresh() {
        jokePresenter.getJoke(mType);
    }

    @Override
    public void addJoke(IJEntity data) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void loadFailed(String msg) {

    }

    @Override
    public void setPresenter(JokeContract.Presenter presenter) {

    }
}
