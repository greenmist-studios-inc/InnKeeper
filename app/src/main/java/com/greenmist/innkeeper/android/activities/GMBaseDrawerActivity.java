package com.greenmist.innkeeper.android.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import com.greenmist.innkeeper.android.R;
import com.greenmist.innkeeper.model.enums.NavDrawerItem;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by geoff.powell on 1/20/2017.
 */

public abstract class GMBaseDrawerActivity extends GMBaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar_main)    Toolbar toolbar;
    @BindView(R.id.toolbar_title)   TextView toolbarTitle;
    @BindView(R.id.drawer_layout)   DrawerLayout drawerLayout;
    @BindView(R.id.nav_view)        NavigationView navigationView;

    protected ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        ButterKnife.bind(this);

        setupToolbar();
        setupDependencies();
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.content_desc_drawer_open, R.string.content_desc_drawer_open);
        drawerLayout.addDrawerListener(drawerToggle);

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(R.layout.activity_drawer_toolbar);
        if (layoutResID > 0) {
            ViewGroup container = ButterKnife.findById(this, R.id.content);
            getLayoutInflater().inflate(layoutResID, container, true);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        NavDrawerItem navDrawerItem = NavDrawerItem.getNavDrawerItem(item.getItemId());

        if (navDrawerItem != null) {
            drawerLayout.closeDrawer(GravityCompat.START);
            startActivity(new Intent(this, navDrawerItem.getActivityClass()));
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
            return;
        }
        super.onBackPressed();
    }

    protected abstract void setupDependencies();

    @LayoutRes
    protected abstract int getLayoutResource();

    public void setToolbarTitle(@StringRes int id) {
        toolbarTitle.setText(id);
    }
}
