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
    private final FragmentsManager fragmentsManager = new FragmentsManager();

    private DrawerLayout drawer;
    private Toolbar toolbar;
    private FragmentManager fragmentManager;
    private Fragment currentFragment = null;
    private String defaultTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dfsensors);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getFragmentManager();

        Utils.setContext(this);

        defaultTitle = getString(R.string.app_name);
        showFragment(R.id.nav_main_page);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        boolean success = showFragment(item.getItemId());
        drawer.closeDrawer(GravityCompat.START);
        return success;
    }

    private boolean showFragment(int itemId) {
        int sensorType = Utils.getSensorTypeFromItemId(itemId);
        final Fragment targetFragment = fragmentsManager.getFragment(sensorType);

        if (targetFragment == null || targetFragment == currentFragment) {
            return false;
        }

        final String title = Utils.getSensorDescription(sensorType);
        toolbar.setTitle(title.isEmpty() ? defaultTitle : title);

        currentFragment = targetFragment;
        fragmentManager.beginTransaction().replace(R.id.content_frame, targetFragment).commit();

        return true;
    }
}
