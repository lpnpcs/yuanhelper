package com.lpnpcs.yuanhelper.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lpnpcs.yuanhelper.R;
import com.lpnpcs.yuanhelper.data.entity.ZhiHuDetailEntity;
import com.lpnpcs.yuanhelper.di.component.DaggerZhiHuDetailComponent;
import com.lpnpcs.yuanhelper.di.module.ZhiHuDetailPresenterModule;
import com.lpnpcs.yuanhelper.presenter.Contract.ZhiHuDetailContract;
import com.lpnpcs.yuanhelper.presenter.ZhiHuDetailPresenter;
import com.lpnpcs.yuanhelper.util.Share;
import com.lpnpcs.yuanhelper.util.SnackUtil;
import com.lpnpcs.yuanhelper.widget.loadview.ShapeLoadingDialog;
import com.lpnpcs.yuanhelper.widget.swipeback.SwipeBackActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lpnpcs.
 * email:lpnpcs@gmail.com
 * description：知乎详情页面
 */
public class ZhiHuDetailActivity  extends SwipeBackActivity implements ZhiHuDetailContract.View {
    @BindView(R.id.detail_img)
    ImageView detailImg;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.web_container)
    FrameLayout webContainer;
    private ShapeLoadingDialog shapeLoadingDialog;
    private WebView webView;
    private ZhiHuDetailPresenter zhiHuDetailPresenter;
    private  ZhiHuDetailEntity zhiHuDetail;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        zhiHuDetailPresenter = DaggerZhiHuDetailComponent.builder()
                .zhiHuDetailPresenterModule(new ZhiHuDetailPresenterModule(this))
                .build().getZhiHuDetailPresenter();
        initViews();
    }

    protected void initViews() {
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);
        String id = getIntent().getStringExtra("id");
        String title = getIntent().getStringExtra("title");
        shapeLoadingDialog = new ShapeLoadingDialog(this);
        shapeLoadingDialog.setLoadingText("加载中...");
        toolbarLayout.setTitle(title);
        initWebView();
        zhiHuDetailPresenter.loadNewsDetail(id);

    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {
        webView = new WebView(this);
        webContainer.addView(webView);
        webView.setVisibility(View.INVISIBLE);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(final WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    view.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            view.setVisibility(View.VISIBLE);
                            hideProgress();
                        }
                    }, 300);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        webView.setVisibility(View.INVISIBLE);
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        webView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        webView.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //System.exit(0);
    }

    @Override
    public void showProgress() {
        shapeLoadingDialog.show();
    }

    @Override
    public void showDetail(ZhiHuDetailEntity zhiHuDetailEntity) {
        zhiHuDetail = zhiHuDetailEntity;
        Glide.with(this).load(zhiHuDetailEntity.getImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(detailImg);
        //add css style to webView
        String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/css/news.css\" type=\"text/css\">";
        String html = "<html><head>" + css + "</head><body>" + zhiHuDetailEntity.getBody() + "</body></html>";
        html = html.replace("<div class=\"img-place-holder\">", "");
        webView.loadDataWithBaseURL("x-data://base", html, "text/html", "UTF-8", null);
        initFAB();
    }
    private void initFAB() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Share.shareText(ZhiHuDetailActivity.this, zhiHuDetail.getShare_url());
            }
        });
    }
    @Override
    public void hideProgress() {
       shapeLoadingDialog.dismiss();
    }

    @Override
    public void showLoadFailed(String msg) {
        SnackUtil.showSnackLong(webContainer, R.string.load_fail);
    }

    @Override
    public void setPresenter(ZhiHuDetailContract.Presenter presenter) {

    }
}
