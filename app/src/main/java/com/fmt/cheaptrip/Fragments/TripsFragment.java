package com.fmt.cheaptrip.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fmt.cheaptrip.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TripsFragment extends Fragment {


    public TripsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.trips_fragment, container, false);
    }

}
