package com.lpnpcs.yuanhelper.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lpnpcs.yuanhelper.R;
import com.lpnpcs.yuanhelper.base.BaseActivity;
import com.lpnpcs.yuanhelper.ui.fragment.WebViewFragment;
import com.lpnpcs.yuanhelper.ui.fragment.ZhiHuFragment;
import com.lpnpcs.yuanhelper.util.API;
import com.lpnpcs.yuanhelper.util.SnackUtil;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;

import butterknife.BindView;

/**
 * Created by lpnpcs.
 * email:lpnpcs@gmail.com
 * description：main UI
 */
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private boolean backPressed;

    @Override
    protected void initViews() {
        super.initViews();
        setupDrawer();
        initNavigationView();
        replaceFragment(new ZhiHuFragment());
    }

    private void initNavigationView() {

        Glide.with(this).load(R.drawable.head)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into((ImageView) navView.getHeaderView(0).findViewById(R.id.headImage));
        navView.setNavigationItemSelectedListener(this);
        navView.inflateMenu(R.menu.main_menu_all);

        Menu menu = navView.getMenu();
        menu.getItem(0).setChecked(true);

        menu.getItem(0).setIcon(
                new IconicsDrawable(this).
                        icon(MaterialDesignIconic.Icon.gmi_rss));
        menu.getItem(1).setIcon(
                new IconicsDrawable(this).
                        icon(MaterialDesignIconic.Icon.gmi_github_alt));
        menu.getItem(2).setIcon(
                new IconicsDrawable(this).
                        icon(MaterialDesignIconic.Icon.gmi_globe));
        menu.getItem(3).setIcon(
                new IconicsDrawable(this).
                        icon(MaterialDesignIconic.Icon.gmi_mood));
        Menu sub = menu.getItem(4).getSubMenu();
        sub.getItem(0).setIcon(
                new IconicsDrawable(this).
                        icon(MaterialDesignIconic.Icon.gmi_settings)
                        .color(Color.DKGRAY));

    }

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_main;
    }


    private void setupDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }

    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            doublePressBackToQuit();
        }
    }

    private void doublePressBackToQuit() {
        if (backPressed) {
            super.onBackPressed();
            android.os.Process.killProcess(android.os.Process.myPid());
        }
        backPressed = true;
        SnackUtil.showSnack(drawerLayout, R.string.leave_app);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                backPressed = false;
            }
        }, 2000);
    }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
        supportPostponeEnterTransition();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about) {
            //    startActivity(new Intent(this, AboutActivity.class));
            return true;
        } else if (id == R.id.action_exit) {
            android.os.Process.killProcess(android.os.Process.myPid());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_zhihu) {
            replaceFragment(new ZhiHuFragment());
        } else if (id == R.id.nav_github) {
            replaceFragment(new WebViewFragment(API.GITHUB));
        } else if (id == R.id.nav_csdn) {
            replaceFragment(new WebViewFragment(API.CSDN));
        } else if (id == R.id.nav_joke) {

        } else if (id == R.id.nav_setting) {


        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
