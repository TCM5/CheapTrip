package com.fmt.cheaptrip.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;

import com.fmt.cheaptrip.Adapters.LocationAdapter;
import com.fmt.cheaptrip.CustomViews.LocationAutoCompleteTextView;
import com.fmt.cheaptrip.Entities.LocationEntry;
import com.fmt.cheaptrip.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by ASUS-TCMS on 14/05/2016.
 */
public class MapFragment extends Fragment {

    private GoogleMap map;

    private static final int DEFAULT_MAP_ZOOM = 15;
    private static final double DEFAULT_MAP_LATITUDE = 38.754422;
    private static final double DEFAULT_MAP_LONGITUDE = 0.0;

    private LocationAutoCompleteTextView originInput;
    private LocationAutoCompleteTextView destinyInput;

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

        MapFragmentListeners mapFragmentListeners = new MapFragmentListeners();

        originInput = (LocationAutoCompleteTextView) view.findViewById(R.id.map_fragment_origin_input);

        if (originInput != null) {
            originInput.setThreshold(THRESHOLD);
            originInput.setAdapter(new LocationAdapter(getActivity()));
            originInput.setOnItemClickListener(mapFragmentListeners.originListener());
        }

        //  destinyInput = (AutoCompleteTextView) view.findViewById(R.id.map_fragment_destiny_input);
        // destinyInput.setOnItemClickListener(mapFragmentListeners.destinyListener());
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

        map.moveCamera(center);
        map.animateCamera(zoom);
    }

    /**
     *
     */
    private class MapFragmentListeners {

        /**
         * @return
         */
        public AdapterView.OnItemClickListener originListener() {

            return new AdapterView.OnItemClickListener() {


                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                    LocationEntry result = (LocationEntry) parent.getItemAtPosition(position);
                    originInput.setText(result.getAddress());

                    //parent.getItemAtPosition(position);

                    //LatLng originLatLng = new LatLng(0.0,0.0);
                    //markerOptions.position(originLatLng);

                    //BitmapDescriptor originIcon = null;
                    //markerOptions.icon(originIcon);

                    //map.addMarker(markerOptions);
                }
            };
        }

        /**
         * @return
         */
        public View.OnClickListener destinyListener() {

            return new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            };
        }


    }

}
