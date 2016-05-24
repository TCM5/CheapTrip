package com.fmt.cheaptrip.fragments;

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

import com.fmt.cheaptrip.adapters.LocationAdapter;
import com.fmt.cheaptrip.customviews1.LocationAutoCompleteTextView;
import com.fmt.cheaptrip.entities.LocationEntry;
import com.fmt.cheaptrip.R;
import com.fmt.cheaptrip.utils.ActivityUtils;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by ASUS-TCMS on 14/05/2016.
 */
public class MapFragment extends Fragment {

    private GoogleMap map;

    private static final int DEFAULT_MAP_ZOOM = 15;
    private static final double DEFAULT_MAP_LATITUDE = 0.0;
    private static final double DEFAULT_MAP_LONGITUDE = 0.0;

    private LocationAutoCompleteTextView originInput;
    private LocationAutoCompleteTextView destinyInput;

    private FloatingActionButton newFab;
    private FloatingActionButton newTripFab;
    private FloatingActionButton findTripFab;
    private LinearLayout newTripAction;
    private LinearLayout findTripAction;

    private Integer THRESHOLD = 2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SupportMapFragment supportMapFragment = (SupportMapFragment) getFragmentManager().findFragmentById(R.id.map_fragment_id);

        if (supportMapFragment != null) {

            supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    map = googleMap;
                }
            });

            if (map != null) {
                setMapDefaultConfigs();
            }
        }


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.map_fragment, container, false);

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
            newTripFab.setOnClickListener(newTrip());
        }

        findTripFab = (FloatingActionButton) view.findViewById(R.id.map_fragment_find_trip_fab);

        if (findTripFab != null) {
            findTripFab.setOnClickListener(findTrip());
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
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(DEFAULT_MAP_ZOOM);


        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(defaultLatLng).zoom(15).build();
        map.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));

        //map.moveCamera(center);
        map.animateCamera(zoom);
    }

    /**
     * @return
     */
    private AdapterView.OnItemClickListener originListener() {

        return new AdapterView.OnItemClickListener() {

            MarkerOptions markerOptions = new MarkerOptions();

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                LocationEntry result = (LocationEntry) parent.getItemAtPosition(position);
                originInput.setText(result.getAddress());

                markerOptions.position(result.getLatLng());
                map.addMarker(markerOptions);

                CameraUpdate center = CameraUpdateFactory.newCameraPosition(new CameraPosition(result.getLatLng(), 10, 1f, 1f));
                map.animateCamera(center);
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

            }
        };
    }

    private View.OnClickListener newAction() {

        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (LinearLayout.GONE == findTripAction.getVisibility() || LinearLayout.GONE == newTripAction.getVisibility()) {
                    findTripAction.setVisibility(LinearLayout.VISIBLE);
                    newTripAction.setVisibility(LinearLayout.VISIBLE);
                } else {
                    findTripAction.setVisibility(LinearLayout.GONE);
                    newTripAction.setVisibility(LinearLayout.GONE);
                }
            }
        };

    }

    private View.OnClickListener newTrip() {

        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NewTripFragment newTripFragment = new NewTripFragment();
                ActivityUtils.replaceFragment(getFragmentManager(), newTripFragment, R.id.main_content_container, true);

            }
        };
    }

    public View.OnClickListener findTrip() {

        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TripsFragment tripsFragment = new TripsFragment();
                ActivityUtils.replaceFragment(getFragmentManager(), tripsFragment, R.id.main_content_container, true);

            }


        };
    }


}
