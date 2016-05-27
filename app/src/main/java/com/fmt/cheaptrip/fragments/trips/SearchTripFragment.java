package com.fmt.cheaptrip.fragments.trips;


import android.location.Address;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.fmt.cheaptrip.R;
import com.fmt.cheaptrip.adapters.TripsAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchTripFragment extends Fragment {

    public static final String TAG = "SEARCH_TRIPS_FRAGMENT_TAG";

    private String addressOrigin;
    private String addressDestiny;

    private ListView listView;
    private TextView emptyListTextView;

    private TripsAdapter tripsAdapter;

    public SearchTripFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addressOrigin = getArguments().getString("addressOrigin");
        addressDestiny = getArguments().getString("addressDestiny");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_trip_fragment, container, false);

        listView = (ListView) view.findViewById(R.id.given_trips_fragment_list);
        emptyListTextView = (TextView) view.findViewById(R.id.given_trips_fragment_empty_list_tv);

        tripsAdapter = new TripsAdapter(getActivity(), R.layout.mytrip_header, R.id.mytrip_header_textview);
        listView.setAdapter(tripsAdapter);

        View header = inflater.inflate(R.layout.mytrip_header, null);
        listView.addHeaderView(header);


        return view;
    }

}
