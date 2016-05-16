package com.fmt.cheaptrip.Fragments;


import android.location.Address;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.fmt.cheaptrip.Adapters.MyTripsAdapter;
import com.fmt.cheaptrip.Entities.TripEntry;
import com.fmt.cheaptrip.R;

import java.util.ArrayList;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyTripsFragment extends Fragment {


    private MyTripsAdapter myTripsAdapter;

    public MyTripsFragment() {


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mytrips_fragment, container, false);

        ListView listView = (ListView) view.findViewById(R.id.menu_item_my_trips);

        Address add1 = new Address(Locale.ENGLISH);
        add1.setLatitude(5.0);
        add1.setLongitude(10.0);

        Address add2 = new Address(Locale.ENGLISH);
        add2.setLatitude(6.0);
        add2.setLongitude(11.0);

        TripEntry tripEntry1 = new TripEntry();
        tripEntry1.setDestinyLocation(add1);
        tripEntry1.setOriginLocation(add2);

        TripEntry tripEntry2 = new TripEntry();
        tripEntry1.setDestinyLocation(add2);
        tripEntry1.setOriginLocation(add1);


        ArrayList<TripEntry> dummy = new ArrayList<TripEntry>();
        dummy.add(tripEntry1 );
        dummy.add(tripEntry2 );



        myTripsAdapter = new MyTripsAdapter(getActivity(),dummy);




        listView.setAdapter(myTripsAdapter);

        return view;
    }

}
