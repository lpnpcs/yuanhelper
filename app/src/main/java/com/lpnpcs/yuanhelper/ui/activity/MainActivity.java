package com.lpnpcs.yuanhelper.ui.activity;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lpnpcs.yuanhelper.R;
import com.lpnpcs.yuanhelper.base.BaseActivity;

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
        //load headerView's image
        Glide.with(this).load(R.drawable.head)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into((ImageView) navView.getHeaderView(0).findViewById(R.id.headImage));
        navView.setNavigationItemSelectedListener(this);
      //  boolean isSecretOn = SPUtil.getBoolean(SettingFragment.SECRET_MODE);
       /* if (isSecretOn) {
            navView.inflateMenu(R.menu.main_menu_all);
        } else {
            navView.inflateMenu(R.menu.main_drawer);
        }
        //select the first menu at startup
        Menu menu = navView.getMenu();
        menu.getItem(0).setChecked(true);

        menu.getItem(0).setIcon(
                new IconicsDrawable(this).
                        icon(GoogleMaterial.Icon.gmd_explore));
        menu.getItem(1).setIcon(
                new IconicsDrawable(this).
                        icon(GoogleMaterial.Icon.gmd_face)
                        .color(Color.RED));
        Menu sub = menu.getItem(isSecretOn ? 3 : 2).getSubMenu();
        sub.getItem(0).setIcon(
                new IconicsDrawable(this).
                        icon(GoogleMaterial.Icon.gmd_share)
                        .color(Color.DKGRAY));
        sub.getItem(1).setIcon(
                new IconicsDrawable(this).
                        icon(GoogleMaterial.Icon.gmd_settings)
                        .color(Color.GRAY));
        if (isSecretOn) {
            menu.getItem(2).setIcon(new IconicsDrawable(this)
                    .icon(GoogleMaterial.Icon.gmd_whatshot)
                    .color(Color.WHITE));
        }*/
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
