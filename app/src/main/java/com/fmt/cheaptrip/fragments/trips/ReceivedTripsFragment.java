package com.fmt.cheaptrip.fragments.trips;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fmt.cheaptrip.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReceivedTripsFragment extends Fragment {


    public ReceivedTripsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_received_trips, container, false);
    }

}
