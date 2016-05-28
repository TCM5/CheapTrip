package com.fmt.cheaptrip.fragments.trips;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import com.fmt.cheaptrip.entities.Baggage;
import com.fmt.cheaptrip.entities.Vehicle;

import android.widget.Toast;

import com.android.volley.VolleyError;
import com.fmt.cheaptrip.R;
import com.fmt.cheaptrip.entities.Trip;
import com.fmt.cheaptrip.managers.UserAccountManager;
import com.fmt.cheaptrip.webservices.TripWSInvoker;
import com.fmt.cheaptrip.webservices.response.WSResponseListener;
import com.fmt.cheaptrip.webservices.response.WSResponseObject;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewTripFragment extends Fragment {

    public static final String TAG = "NEW_TRIPS_FRAGMENT_TAG";

    private final String PORTUGAL_CURRENCY = "€";
    private final String MINUTES = "min"; //AHAHAHAHHAHAH

    private double tripTolerance = 15;
    private double tripPrice = 15;

    // Location section views
    private TextView new_trip_fragment_origin_city_tv;
    private TextView new_trip_fragment_destiny_city_tv;

    // Date section views
    private DatePicker new_trip_fragment_date_dp;


    // Price section views
    private FloatingActionButton new_trip_fragment_price_fb_less;
    private TextView new_trip_fragment_price_tv_value;
    private FloatingActionButton new_trip_fragment_price_fb_more;

    // Car section vies
    private Spinner new_trip_fragment_car_spinner_value;

    // Baggage
    private ImageView new_trip_fragment_bagage_icon;
    private Spinner new_trip_fragment_bagage_spinner_value;

    // Time tolerance views
    private FloatingActionButton new_trip_fragment_tolerance_fb_less;
    private TextView new_trip_fragment_tolerance_tv_value;
    private FloatingActionButton new_trip_fragment_tolerance_fb_more;

    // Obeservation vies
    private EditText new_trip_fragment_observation_et_value;

    // Rules views
    private CheckBox new_trip_fragment_rules_cb_value;

    // Register "button"
    private TextView registerTripTextView;


    public NewTripFragment() {
        //Default constructor
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
        new_trip_fragment_date_dp = (DatePicker) view.findViewById(R.id.new_trip_fragment_date_dp);

        // Price section views
        new_trip_fragment_price_fb_less = (FloatingActionButton) view.findViewById(R.id.new_trip_fragment_price_fb_less);
        new_trip_fragment_price_fb_less.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new_trip_fragment_price_tv_value.setText(String.valueOf(--tripPrice) + " " + PORTUGAL_CURRENCY);
            }
        });

        new_trip_fragment_price_tv_value = (TextView) view.findViewById(R.id.new_trip_fragment_price_tv_value);
        new_trip_fragment_price_tv_value.setText(String.valueOf(tripPrice) + " " + PORTUGAL_CURRENCY);

        new_trip_fragment_price_fb_more = (FloatingActionButton) view.findViewById(R.id.new_trip_fragment_price_fb_more);
        new_trip_fragment_price_fb_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new_trip_fragment_price_tv_value.setText(String.valueOf(++tripPrice) + " " + PORTUGAL_CURRENCY);
            }
        });

        // Car section vies
        new_trip_fragment_car_spinner_value = (Spinner) view.findViewById(R.id.new_trip_fragment_car_spinner_value);
        fillUserVehiclesSpinner();

        // Baggage
        final ArrayList<Baggage> baggageTypesMap = new ArrayList<>();
        baggageTypesMap.add(new Baggage("S", getActivity()));
        baggageTypesMap.add(new Baggage("M", getActivity()));
        baggageTypesMap.add(new Baggage("L", getActivity()));

        new_trip_fragment_bagage_icon = (ImageView) view.findViewById(R.id.new_trip_fragment_bagage_icon);
        new_trip_fragment_bagage_icon.setImageDrawable(baggageTypesMap.get(0).getIcon());
        new_trip_fragment_bagage_spinner_value = (Spinner) view.findViewById(R.id.new_trip_fragment_bagage_spinner_value);

        ArrayAdapter<Baggage> baggagerSpinnerAdapter = new ArrayAdapter<Baggage>(getActivity(), android.R.layout.simple_spinner_item, baggageTypesMap);
        baggagerSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        new_trip_fragment_bagage_spinner_value.setAdapter(baggagerSpinnerAdapter);
        new_trip_fragment_bagage_spinner_value.setSelection(0);
        new_trip_fragment_bagage_spinner_value.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                new_trip_fragment_bagage_icon.setImageDrawable(baggageTypesMap.get(position).getIcon());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Time tolerance views
        new_trip_fragment_tolerance_fb_less = (FloatingActionButton) view.findViewById(R.id.new_trip_fragment_tolerance_fb_less);
        new_trip_fragment_tolerance_fb_less.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new_trip_fragment_tolerance_tv_value.setText(String.valueOf(--tripTolerance) + " " + MINUTES);
            }
        });

        new_trip_fragment_tolerance_tv_value = (TextView) view.findViewById(R.id.new_trip_fragment_tolerance_tv_value);
        new_trip_fragment_tolerance_tv_value.setText(String.valueOf(tripTolerance) + " " + MINUTES);

        new_trip_fragment_tolerance_fb_more = (FloatingActionButton) view.findViewById(R.id.new_trip_fragment_tolerance_fb_more);
        new_trip_fragment_tolerance_fb_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new_trip_fragment_tolerance_tv_value.setText(String.valueOf(++tripTolerance) + " " + MINUTES);
            }
        });

        // Obeservation vies
        new_trip_fragment_observation_et_value = (EditText) view.findViewById(R.id.new_trip_fragment_observation_et_value);

        // Rules views
        new_trip_fragment_rules_cb_value = (CheckBox) view.findViewById(R.id.new_trip_fragment_rules_cb_value);

        registerTripTextView = (TextView) view.findViewById(R.id.registerTripTextView);

        registerTripTextView.setOnClickListener(new View.OnClickListener() {
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
        trip.setStartCity(new_trip_fragment_origin_city_tv.getText().toString());
        trip.setEndCity(new_trip_fragment_destiny_city_tv.getText().toString());

        String startPoint = null;
        String endPoint = null;
        {
            Double originLatitude = getArguments().getDouble("originLatitude");
            Double originLongitude = getArguments().getDouble("originLongitude");
            if (originLatitude != null && originLongitude != null) {
                startPoint = String.valueOf(originLatitude) + ":" + String.valueOf(originLongitude);
            }

            Double destinyLatitude = getArguments().getDouble("destinyLatitude");
            Double destinyLongitude = getArguments().getDouble("destinyLongitude");
            if (destinyLatitude != null && destinyLongitude != null) {
                endPoint = String.valueOf(destinyLatitude) + ":" + String.valueOf(destinyLongitude);
            }

        }

        trip.setStartPoint(startPoint);
        trip.setEndPoint(endPoint);

        Calendar calendar;
        {
            int day = new_trip_fragment_date_dp.getDayOfMonth();
            int month = new_trip_fragment_date_dp.getMonth();
            int year = new_trip_fragment_date_dp.getYear();
            calendar = Calendar.getInstance();
            calendar.set(year, month, day);
        }

        trip.setTripDate(calendar.getTime());
        trip.setPrice(tripPrice);
        trip.setObservations(new_trip_fragment_observation_et_value.getText().toString());
        trip.setBaggageSize(((Baggage) new_trip_fragment_bagage_spinner_value.getSelectedItem()).getKey());
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

            if (new_trip_fragment_rules_cb_value.isChecked()) {

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
            } else {

                Toast.makeText(getActivity(), "You have to agree with the rules", Toast.LENGTH_LONG);
            }
        }
    }

    private void fillUserVehiclesSpinner() {

        Integer currentUserId = UserAccountManager.getCurrentUserId(getActivity().getApplicationContext());
        currentUserId = 1;

        TripWSInvoker.getUserVehicles(getActivity().getApplicationContext(), currentUserId, new WSResponseListener() {
            @Override
            public void onResponse(WSResponseObject response) {
                List<Vehicle> myVehicles = response.getVehicles();

                ArrayAdapter<Vehicle> vehiclesSpinnerAdapter = new ArrayAdapter<Vehicle>(getActivity(), android.R.layout.simple_spinner_item, myVehicles);
                vehiclesSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                new_trip_fragment_car_spinner_value.setAdapter(vehiclesSpinnerAdapter);
            }

            @Override
            public void onError(VolleyError error) {

            }
        });
    }

}
