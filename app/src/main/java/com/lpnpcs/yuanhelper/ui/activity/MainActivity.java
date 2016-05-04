package com.lpnpcs.yuanhelper.ui.activity;

import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lpnpcs.yuanhelper.R;
import com.lpnpcs.yuanhelper.base.BaseActivity;
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

    @Override
    protected void initViews() {
        super.initViews();
        setupDrawer();
        initNavigationView();

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
                        icon(MaterialDesignIconic.Icon.gmi_mood));
        Menu sub = menu.getItem(3).getSubMenu();
        sub.getItem(0).setIcon(
                new IconicsDrawable(this).
                        icon(MaterialDesignIconic.Icon.gmi_settings)
                        .color(Color.DKGRAY));

        }

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.activity_main;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
    }

    private void setupDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }
}
