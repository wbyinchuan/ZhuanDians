package com.baronzhang.android.library.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.ViewStub;

import com.baronzhang.android.library.R;

public class ToolBarWithDrawerBaseActivity extends ToolBarBaseActivity {

    protected DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    }

    @Override
    protected
    @LayoutRes
    int getLayoutID() {
        return R.layout.activity_base;
    }

    protected void addNavFooter(@LayoutRes int layoutId) {
        ViewStub content = (ViewStub) findViewById(R.id.nav_footer_stub);
        content.setLayoutResource(layoutId);
        content.inflate();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void closeDrawer() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    public void openDrawer() {
        if(!drawer.isDrawerOpen(GravityCompat.START)){
            drawer.openDrawer(GravityCompat.START, true);
        }
    }
}