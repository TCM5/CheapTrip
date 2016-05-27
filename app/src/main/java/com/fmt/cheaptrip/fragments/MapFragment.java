package com.fmt.cheaptrip.fragments;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.fmt.cheaptrip.adapters.LocationAdapter;
import com.fmt.cheaptrip.customviews.LocationAutoCompleteTextView;
import com.fmt.cheaptrip.entities.LocationEntry;
import com.fmt.cheaptrip.R;
import com.fmt.cheaptrip.fragments.trips.GivenTripsFragment;
import com.fmt.cheaptrip.fragments.trips.NewTripFragment;
import com.fmt.cheaptrip.fragments.trips.SearchTripFragment;
import com.fmt.cheaptrip.utils.ActivityUtils;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by ASUS-TCMS on 14/05/2016.
 */
public class MapFragment extends Fragment {

    public static final String TAG = "MAP_FRAGMENT_TAG";

    private GoogleMap map;

    private static final int DEFAULT_MAP_ZOOM = 15;
    private static final double DEFAULT_MAP_LATITUDE = 0.0;
    private static final double DEFAULT_MAP_LONGITUDE = 0.0;

    private LocationAutoCompleteTextView originInput;
    private LocationAutoCompleteTextView destinyInput;

    private String originCity;
    private String destinyCity;

    private FloatingActionButton newFab;
    private FloatingActionButton newTripFab;
    private FloatingActionButton findTripFab;
    private LinearLayout newTripAction;
    private LinearLayout findTripAction;

    private Integer THRESHOLD = 2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_map, container, false);

        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_fragment_id);

        if (supportMapFragment != null) {

            supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    map = googleMap;
                }
            });

            // The next piece of code is ugly, but...
            if (map == null) {
                map = supportMapFragment.getMap();
            }

            if (map != null) {
                setMapDefaultConfigs();
            }
        }


        originInput = (LocationAutoCompleteTextView) view.findViewById(R.id.map_fragment_origin_input);

        if (originInput != null) {
            originInput.setThreshold(THRESHOLD);
            originInput.setAdapter(new LocationAdapter(getActivity()));
            originInput.setOnItemClickListener(originListener());
        }

        destinyInput = (LocationAutoCompleteTextView) view.findViewById(R.id.map_fragment_destiny_input);

        if (destinyInput != null) {
            destinyInput.setThreshold(THRESHOLD);
            destinyInput.setAdapter(new LocationAdapter(getActivity()));
            destinyInput.setOnItemClickListener(destinyListener());
        }

        newTripAction = (LinearLayout) view.findViewById(R.id.map_fragment_new_trip_ll);

        findTripAction = (LinearLayout) view.findViewById(R.id.map_fragment_find_trip_ll);

        newTripFab = (FloatingActionButton) view.findViewById(R.id.map_fragment_new_trip_fab);

        if (newTripFab != null) {
            newTripFab.setOnClickListener(newTripListener());
        }

        findTripFab = (FloatingActionButton) view.findViewById(R.id.map_fragment_find_trip_fab);

        if (findTripFab != null) {
            findTripFab.setOnClickListener(findTripListener());
        }

        newFab = (FloatingActionButton) view.findViewById(R.id.map_fragment_new_fab);

        if (newFab != null) {
            newFab.setOnClickListener(newAction());
        }

        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //TODO later
    }

    private void setMapDefaultConfigs() {

        map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        map.getUiSettings().setZoomGesturesEnabled(true);
        map.getUiSettings().setZoomControlsEnabled(true);

        LatLng defaultLatLng;
        /*    if( posicao actual existe){
                //TODO
            }
            else{
                defaultLatLng = new LatLng(DEFAULT_MAP_LATITUDE,DEFAULT_MAP_LONGITUDE);
            }
        */

        defaultLatLng = new LatLng(DEFAULT_MAP_LATITUDE, DEFAULT_MAP_LONGITUDE);

        CameraUpdate center = CameraUpdateFactory.newLatLng(defaultLatLng);

        CameraUpdate zoom = CameraUpdateFactory.zoomTo(30);

        map.animateCamera(zoom);

        map.moveCamera(center);

    }

    /**
     * @return
     */
    private AdapterView.OnItemClickListener originListener() {

        return new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                LocationEntry result = (LocationEntry) parent.getItemAtPosition(position);
                originInput.setText(result.getAddress());
                originCity = result.getCity();

                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(result.getLatLng());
                map.addMarker(markerOptions);
                CameraUpdate zoom = CameraUpdateFactory.zoomTo(20);

                map.animateCamera(zoom);
                map.moveCamera(CameraUpdateFactory.newLatLng(result.getLatLng()));

            }
        };
    }

    /**
     * @return
     */
    private AdapterView.OnItemClickListener destinyListener() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                LocationEntry result = (LocationEntry) parent.getItemAtPosition(position);
                destinyInput.setText(result.getAddress());
                destinyCity = result.getCity();

            }
        };
    }

    private View.OnClickListener newAction() {

        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Later this validation as to change, because its easly hackable
                if (originCity != null && destinyCity != null) {

                    if (LinearLayout.GONE == findTripAction.getVisibility() || LinearLayout.GONE == newTripAction.getVisibility()) {
                        findTripAction.setVisibility(LinearLayout.VISIBLE);
                        newTripAction.setVisibility(LinearLayout.VISIBLE);
                    } else {
                        findTripAction.setVisibility(LinearLayout.GONE);
                        newTripAction.setVisibility(LinearLayout.GONE);
                    }
                } else {
                    Toast.makeText(getActivity(), "You have to select a origin and destination city", Toast.LENGTH_SHORT).show();
                }
            }
        };

    }

    private View.OnClickListener newTripListener() {

        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NewTripFragment newTripFragment = new NewTripFragment();
                ActivityUtils.replaceFragment(getFragmentManager(), newTripFragment, R.id.main_content_container, NewTripFragment.TAG, true);
            }
        };
    }

    private View.OnClickListener findTripListener() {

        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("addressOrigin", originCity);
                bundle.putString("addressDestiny", destinyCity);

                SearchTripFragment searchTripFragment = new SearchTripFragment();
                searchTripFragment.setArguments(bundle);

                ActivityUtils.replaceFragment(getFragmentManager(), searchTripFragment, R.id.main_content_container, SearchTripFragment.TAG, true);

            }
        };
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
