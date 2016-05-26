package com.fmt.cheaptrip.fragments.trips;


import android.location.Address;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.fmt.cheaptrip.R;
import com.fmt.cheaptrip.adapters.TripsAdapter;
import com.fmt.cheaptrip.entities.TripEntry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReceivedTripsFragment extends Fragment {

    private TripsAdapter tripsAdapter;

    public ReceivedTripsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_received_trips, container, false);

        ListView listView = (ListView) view.findViewById(R.id.my_trips_fragment_list);

        ArrayList<TripEntry> receivedTrips = receivedTripsList();

        tripsAdapter = new TripsAdapter(getActivity(), R.layout.mytrip_header, R.id.mytrip_header_textview, receivedTrips);
        listView.setAdapter(tripsAdapter);

        View header = inflater.inflate(R.layout.mytrip_header, null);
        listView.addHeaderView(header);

        return view;
    }

    /**
     * This method calls a webservices responsible for returning received trips for the current user.<br>
     * The result of the webservices will populate a list.
     * Otherwise, if the result is empty a given arraylist is returned<br>
     *
     * @return List of received trips
     */
    private ArrayList<TripEntry> receivedTripsList() {

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
        for (int i = 0; i < 6; i++) {
            dummy.add(tripEntry1);
        }

        if (dummy != null) {

        } else {
            return (ArrayList<TripEntry>) Collections.EMPTY_LIST;
        }
return null;
    }


}
