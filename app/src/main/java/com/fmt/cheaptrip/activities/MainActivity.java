package com.fmt.cheaptrip.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.ImageView;

import com.fmt.cheaptrip.fragments.AboutFragment;
import com.fmt.cheaptrip.fragments.MapFragment;
import com.fmt.cheaptrip.fragments.ProfileFragment;
import com.fmt.cheaptrip.fragments.trips.GivenTripsFragment;
import com.fmt.cheaptrip.R;
import com.fmt.cheaptrip.fragments.trips.ReceivedTripsFragment;
import com.fmt.cheaptrip.utils.ActivityUtils;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private MapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setActionBarIcon();

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
                .add(R.id.main_content_container, mapFragment).commit();
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

            case R.id.menu_item_home:

               // MapFragment mapFragment = (MapFragment) getSupportFragmentManager().findFragmentByTag(MapFragment.TAG);

                //if (mapFragment == null) {
                    mapFragment = new MapFragment();
                //}

                ActivityUtils.replaceFragment(fragmentManager, mapFragment, R.id.main_content_container, MapFragment.TAG, true);

                break;

            case R.id.menu_item_received_trips:

                ReceivedTripsFragment receivedTripsFragment = new ReceivedTripsFragment();
                ActivityUtils.replaceFragment(fragmentManager, receivedTripsFragment, R.id.main_content_container, ReceivedTripsFragment.TAG, true);

                break;

            case R.id.menu_item_given_trips:

                GivenTripsFragment givenTripsFragment = new GivenTripsFragment();
                ActivityUtils.replaceFragment(fragmentManager, givenTripsFragment, R.id.main_content_container, GivenTripsFragment.TAG, true);

                break;

            case R.id.menu_item_edit_profile:


                ProfileFragment profileFragment = new ProfileFragment();
                ActivityUtils.replaceFragment(fragmentManager, profileFragment, R.id.main_content_container, ProfileFragment.TAG, true);

                break;

            case R.id.menu_item_about:

                AboutFragment aboutFragment = new AboutFragment();
                ActivityUtils.replaceFragment(fragmentManager, aboutFragment, R.id.main_content_container, AboutFragment.TAG, true);

                break;

            default:

                //Nothing to do here

                break;
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setActionBarIcon(){

      getSupportActionBar().setTitle("");
    /*    getSupportActionBar().setIcon(R.drawable.logo_simple);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
*/
        getSupportActionBar().setDisplayOptions(getSupportActionBar().getDisplayOptions()
                | ActionBar.DISPLAY_SHOW_CUSTOM);
        ImageView imageView = new ImageView(getSupportActionBar().getThemedContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        imageView.setScaleX(0.7f);
        imageView.setScaleY(0.7f);
        imageView.setImageResource(R.drawable.logo_simple);
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT, Gravity.RIGHT
                | Gravity.CENTER_VERTICAL );

        imageView.setLayoutParams(layoutParams);
        getSupportActionBar().setCustomView(imageView);

    }
}
