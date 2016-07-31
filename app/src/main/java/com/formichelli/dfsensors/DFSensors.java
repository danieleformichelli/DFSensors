package com.formichelli.dfsensors;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.formichelli.dfsensors.fragments.FragmentsManager;

public class DFSensors extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private final FragmentsManager _fragmentsManager = new FragmentsManager();

    private DrawerLayout _drawer;
    private FragmentManager _fragmentManager;
    private Fragment currentFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dfsensors);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        _drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, _drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        _drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        _fragmentManager = getFragmentManager();

        showFragment(R.id.nav_main_page);
    }

    @Override
    public void onBackPressed() {
        if (_drawer.isDrawerOpen(GravityCompat.START)) {
            _drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        boolean success = showFragment(item.getItemId());

        _drawer.closeDrawer(GravityCompat.START);

        return success;
    }

    private boolean showFragment(int itemId) {
        final Fragment targetFragment = _fragmentsManager.getFragment(itemId);
        if (targetFragment == null || targetFragment == currentFragment) {
            return false;
        }

        currentFragment = targetFragment;
        _fragmentManager.beginTransaction().replace(R.id.content_frame, targetFragment).commit();

        return true;
    }
}
