package com.fmt.cheaptrip.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.fmt.cheaptrip.Fragments.AboutFragment;
import com.fmt.cheaptrip.Fragments.MyTripsFragment;
import com.fmt.cheaptrip.R;
import com.fmt.cheaptrip.Utils.ActivityUtils;
import com.fmt.cheaptrip.Fragments.MapFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private MapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initializeMapFragment(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    private void initializeMapFragment(Bundle savedInstanceState) {

        // if (savedInstanceState != null) {

        mapFragment = new MapFragment();


        getSupportFragmentManager().beginTransaction()
                .add(R.id.map_fragment_container, mapFragment).commit();
     /*   } else {

            mapFragment = (MapFragment) getSupportFragmentManager().findFragmentById(android.R.id.content);
        }*/
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int itemId = item.getItemId();

        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (itemId) {

            case R.id.menu_item_my_trips:

                MyTripsFragment myTripsFragment = new MyTripsFragment();
                ActivityUtils.replaceFragment(fragmentManager, myTripsFragment, R.id.mytrips_fragment_container);

                break;

            case R.id.menu_item_about:

                AboutFragment aboutFragment = new AboutFragment();
                ActivityUtils.replaceFragment(fragmentManager, aboutFragment, R.id.about_fragment_container);

                break;
            default:

                MapFragment mapFragment = new MapFragment();
                ActivityUtils.replaceFragment(fragmentManager, mapFragment, R.id.map_fragment_container);

                break;
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
