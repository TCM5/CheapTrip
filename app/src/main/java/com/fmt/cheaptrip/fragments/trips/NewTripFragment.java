package com.fmt.cheaptrip.fragments.trips;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.fmt.cheaptrip.R;
import com.fmt.cheaptrip.entities.Trip;
import com.fmt.cheaptrip.webservices.TripWSInvoker;
import com.fmt.cheaptrip.webservices.response.WSResponseListener;
import com.fmt.cheaptrip.webservices.response.WSResponseObject;

import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewTripFragment extends Fragment {

    public static final String TAG = "NEW_TRIPS_FRAGMENT_TAG";

    // Location section views
    private TextView new_trip_fragment_origin_city_tv;
    private TextView new_trip_fragment_destiny_city_tv;

    // Date section views

    // Price section views
    private FloatingActionButton new_trip_fragment_price_fb_less;
    private TextView new_trip_fragment_price_tv_value;
    private FloatingActionButton new_trip_fragment_price_fb_more;

    // Car section vies
    private Spinner new_trip_fragment_car_spinner_value;

    // Bagage
    private Spinner new_trip_fragment_bagage_spinner_value;

    // Time tolerance views
    private FloatingActionButton new_trip_fragment_tolerance_fb_less;
    private TextView new_trip_fragment_tolerance_tv_value;
    private FloatingActionButton new_trip_fragment_tolerance_fb_more;

    // Obeservation vies
    private EditText new_trip_fragment_observation_et_value;

    // Rules views
    private CheckBox new_trip_fragment_rules_cb_value;

    private FloatingActionButton btnRegisterTrip;


    public NewTripFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_trip, container, false);


        // Location section views
        new_trip_fragment_origin_city_tv = (TextView) view.findViewById(R.id.new_trip_fragment_origin_city_tv);
        new_trip_fragment_origin_city_tv.setText(getArguments().get("addressOrigin").toString());
        new_trip_fragment_destiny_city_tv = (TextView) view.findViewById(R.id.new_trip_fragment_destiny_city_tv);
        new_trip_fragment_destiny_city_tv.setText(getArguments().get("addressDestiny").toString());

        // Date section views

        // Price section views
        new_trip_fragment_price_fb_less = (FloatingActionButton) view.findViewById(R.id.new_trip_fragment_price_fb_less);
        new_trip_fragment_price_tv_value = (TextView) view.findViewById(R.id.new_trip_fragment_price_tv_value);
        new_trip_fragment_price_fb_more = (FloatingActionButton) view.findViewById(R.id.new_trip_fragment_price_fb_more);

        // Car section vies
        new_trip_fragment_car_spinner_value = (Spinner) view.findViewById(R.id.new_trip_fragment_car_spinner_value);

        // Bagage
        new_trip_fragment_bagage_spinner_value = (Spinner) view.findViewById(R.id.new_trip_fragment_bagage_spinner_value);

        // Time tolerance views
        new_trip_fragment_tolerance_fb_less = (FloatingActionButton) view.findViewById(R.id.new_trip_fragment_tolerance_fb_less);
        new_trip_fragment_tolerance_tv_value = (TextView) view.findViewById(R.id.new_trip_fragment_tolerance_tv_value);
        new_trip_fragment_tolerance_fb_more = (FloatingActionButton) view.findViewById(R.id.new_trip_fragment_tolerance_fb_more);

        // Obeservation vies
        new_trip_fragment_observation_et_value = (EditText) view.findViewById(R.id.new_trip_fragment_observation_et_value);

        // Rules views
        new_trip_fragment_rules_cb_value = (CheckBox) view.findViewById(R.id.new_trip_fragment_rules_cb_value);

        btnRegisterTrip = (FloatingActionButton) view.findViewById(R.id.btnRegisterTrip);

        btnRegisterTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerTrip(view);
            }
        });


        return view;
    }

    private void registerTrip(View view) {
        final Trip trip = new Trip();
        trip.setDriverId(1);
        trip.setVehicleId(1);
        trip.setStartCity("Lisboa");
        trip.setEndCity("Beja");
        trip.setStartPoint("1");
        trip.setEndPoint("2");
        trip.setTripDate(new Date());
        trip.setPrice(20.0d);
        trip.setObservations("Obs");
        trip.setBaggageSize("1");
        trip.setDelayTolerance(15);

        if (trip.getDriverId() == null || trip.getVehicleId() == null ||
                (trip.getStartCity() == null || trip.getStartCity().isEmpty()) ||
                (trip.getEndCity() == null || trip.getEndCity().isEmpty()) ||
                (trip.getStartPoint() == null || trip.getStartPoint().isEmpty()) ||
                (trip.getEndPoint() == null || trip.getEndPoint().isEmpty()) ||
                trip.getTripDate() == null || trip.getPrice() == null ||
                trip.getBaggageSize() == null || trip.getDelayTolerance() == null) {

            Toast.makeText(getActivity(), "You have to bla bla", Toast.LENGTH_LONG);

        } else {


            TripWSInvoker.registerTrip(getActivity().getApplicationContext(), trip, new WSResponseListener() {
                @Override
                public void onResponse(WSResponseObject response) {
                    if (response.getSuccess().equalsIgnoreCase("true")) {
                        Toast.makeText(getActivity().getApplicationContext(), "Trip Registered", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onError(VolleyError error) {

                }
            });
        }
    }
}
