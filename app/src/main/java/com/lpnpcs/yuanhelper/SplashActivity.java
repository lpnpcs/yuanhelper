package com.lpnpcs.yuanhelper;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lpnpcs.yuanhelper.di.component.DaggerSplashComponent;
import com.lpnpcs.yuanhelper.di.module.SplashPresenterModule;
import com.lpnpcs.yuanhelper.presenter.Contract.SplashContract;
import com.lpnpcs.yuanhelper.ui.activity.MainActivity;

import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity implements SplashContract.View {

    private ImageView splash;
    private SplashContract.Presenter mPresenter;
    private static final int SPLASH_DURATION = 2000;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(R.layout.activity_splash);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
        splash = (ImageView) findViewById(R.id.splash);
        mPresenter = DaggerSplashComponent.builder().splashPresenterModule(new SplashPresenterModule(this)).build().getSplashPresenter();

        initSplash();
    }

    private void initSplash() {

        getSplash();
    }

    private void getSplash() {
        mPresenter.getSplash();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void setSplash(String s) {
        if (!TextUtils.isEmpty(s)) {
            Glide.with(this)
                    .load(s)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .animate(R.anim.splash_anim)
                    .crossFade()
                    .into(splash);
        } else {
            Glide.with(this)
                    .load(R.drawable.splash)
                    .crossFade(SPLASH_DURATION)
                    .animate(R.anim.splash_anim)
                    .into(splash);
        }

            startDelayActivity();
    }

    private void startDelayActivity() {

        splash.postDelayed(new Runnable() {
            @Override
            public void run() {
                startApp();
            }
        },SPLASH_DURATION);
    }

    private void startApp() {
        startActivity(new Intent(this, MainActivity.class));
        overridePendingTransition(android.support.v7.appcompat.R.anim.abc_grow_fade_in_from_bottom, android.support.v7.appcompat.R.anim.abc_shrink_fade_out_from_bottom);
        finish();

    }

    @Override
    public void setPresenter(SplashContract.Presenter presenter) {

    }
}
