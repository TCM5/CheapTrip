package com.fmt.cheaptrip.Fragments;


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

import com.fmt.cheaptrip.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewTripFragment extends Fragment {

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


    public NewTripFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_trip_fragment, container, false);

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


        return view;
    }

}
