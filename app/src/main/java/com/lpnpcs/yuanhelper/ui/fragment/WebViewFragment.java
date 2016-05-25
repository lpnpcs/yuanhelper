package com.lpnpcs.yuanhelper.ui.fragment;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.lpnpcs.yuanhelper.R;
import com.lpnpcs.yuanhelper.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by lpnpcs.
 * email:lpnpcs@gmail.com
 * description：
 */
@SuppressLint("ValidFragment")
public class WebViewFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.git_webview)
    WebView progressWebView;
    @BindView(R.id.webview_process)
    ProgressBar progressBar;
    private String url;

    @SuppressLint("ValidFragment")
    public WebViewFragment(String url) {
        this.url = url;
    }

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.fragment_webview;
    }

    @Override
    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    protected void initViews() {
        Drawable drawable = getActivity().getResources().getDrawable(R.drawable.progress_bar_states);
        progressBar.setProgressDrawable(drawable);
        swipeRefresh.setOnRefreshListener(this);
        WebSettings settings = progressWebView.getSettings();
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        // 设置可以访问文件
        settings.setAllowFileAccess(true);
        //如果访问的页面中有Javascript，则webview必须设置支持Javascript
        settings.setJavaScriptEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setDisplayZoomControls(false);
        progressWebView.setWebChromeClient(new WebChromeClient());
        progressWebView.setWebViewClient(new MyAndroidWebViewClient());
        progressWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && progressWebView.canGoBack()) {  //表示按返回键
                        progressWebView.goBack();   //后退
                        //webview.goForward();//前进
                        return true;    //已处理
                    }
                }
                return false;
            }
        });
        CookieManager.setAcceptFileSchemeCookies(true);
        CookieManager cookieManager = CookieManager.getInstance();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            CookieSyncManager.createInstance(getActivity());
        } else {
            cookieManager.setAcceptThirdPartyCookies(progressWebView, true);
        }
        cookieManager.setAcceptCookie(true);
        cookieManager.acceptCookie();
        //String cookie = CookieManager.getInstance().getCookie("https://github.com/");
    }

    @Override
    protected void initData() {
        progressWebView.loadUrl(url);
    }

    @Override
    public void onRefresh() {
        progressWebView.reload();
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void onPause() {
        progressWebView.onPause();
        super.onPause();
    }

    @Override
    public void onResume() {
        progressWebView.onResume();
        super.onResume();
    }

    protected class MyAndroidWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    protected class WebChromeClient extends android.webkit.WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                if (progressBar != null) {
                    progressBar.setVisibility(View.GONE);
                }
            } else {
                if (progressBar != null) {
                    if (progressBar.getVisibility() == View.GONE)
                        progressBar.setVisibility(View.VISIBLE);
                    progressBar.setProgress(newProgress);
                }
            }
            super.onProgressChanged(view, newProgress);
        }

    }


}
