package com.fmt.cheaptrip.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
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
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
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

    // Zoom that shows Portugal
    private static final float DEFAULT_MAP_ZOOM = 6.5f;

    // Center of Portugal
    private static final double DEFAULT_MAP_LATITUDE = 40.1520666;
    private static final double DEFAULT_MAP_LONGITUDE = -7.9894828;

    private LocationAutoCompleteTextView originInput;
    private LocationAutoCompleteTextView destinyInput;

    private String originCity;
    private String destinyCity;

    private LatLng originLatLng;
    private LatLng destinyLatLng;

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

        FragmentManager fm = getActivity().getSupportFragmentManager();
        SupportMapFragment supportMapFragment = new SupportMapFragment();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.map_fragment_id, supportMapFragment);
        ft.commit();

        if (supportMapFragment != null) {

            supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    map = googleMap;
                    setMapDefaultConfigs();
                }
            });
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
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(6.5f);

        map.moveCamera(center);
        map.animateCamera(zoom);

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
                originLatLng = result.getLatLng();

                MarkerOptions markerOptions = new MarkerOptions();

                BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.markericonstart);
                markerOptions.icon(icon);

                markerOptions.position(result.getLatLng());
                map.addMarker(markerOptions);

                CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);

                map.animateCamera(zoom);
                map.moveCamera(CameraUpdateFactory.newLatLng(result.getLatLng()));

                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

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
                destinyLatLng = result.getLatLng();

                MarkerOptions markerOptions = new MarkerOptions();

                BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.markericonend);
                markerOptions.icon(icon);

                markerOptions.position(result.getLatLng());
                map.addMarker(markerOptions);
                CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);

                map.animateCamera(zoom);
                map.moveCamera(CameraUpdateFactory.newLatLng(result.getLatLng()));


                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

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

                Bundle bundle = new Bundle();
                bundle.putString("addressOrigin", originCity);
                bundle.putString("addressDestiny", destinyCity);
                bundle.putDouble("originLatitude", originLatLng.latitude);
                bundle.putDouble("originLongitude", originLatLng.longitude);
                bundle.putDouble("destinyLatitude", destinyLatLng.latitude);
                bundle.putDouble("destinyLongitude", destinyLatLng.longitude);

                NewTripFragment newTripFragment = new NewTripFragment();
                newTripFragment.setArguments(bundle);

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

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

}
