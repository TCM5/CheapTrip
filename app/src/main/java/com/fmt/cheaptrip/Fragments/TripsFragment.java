package com.fmt.cheaptrip.Fragments;


import android.location.Address;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.fmt.cheaptrip.Adapters.TripsAdapter;
import com.fmt.cheaptrip.Entities.TripEntry;
import com.fmt.cheaptrip.R;

import java.util.ArrayList;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class TripsFragment extends Fragment {


    private TripsAdapter tripsAdapter;

    public TripsFragment() {


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mytrips_fragment, container, false);

        ListView listView = (ListView) view.findViewById(R.id.my_trips_fragment_list);

        Address add1 = new Address(Locale.ENGLISH);
        add1.setLatitude(5.0);
        add1.setLongitude(10.0);

        Address add2 = new Address(Locale.ENGLISH);
        add2.setLatitude(6.0);
        add2.setLongitude(11.0);

        TripEntry tripEntry1 = new TripEntry();
        tripEntry1.setDestinyLocation(add1);
        tripEntry1.setOriginLocation(add2);




        ArrayList<TripEntry> dummy = new ArrayList<TripEntry>();
        for(int i = 0 ; i < 6; i++) {
            dummy.add(tripEntry1);
        }

        tripsAdapter = new TripsAdapter(getActivity(), R.layout.mytrip_header, R.id.mytrip_header_textview, dummy);
        listView.setAdapter(tripsAdapter);

        View header = inflater.inflate(R.layout.mytrip_header, null);
        listView.addHeaderView(header);


        return view;
    }


}
