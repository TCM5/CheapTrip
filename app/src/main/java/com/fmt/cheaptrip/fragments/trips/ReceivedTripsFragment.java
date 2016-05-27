package com.fmt.cheaptrip.fragments.trips;


import android.location.Address;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.fmt.cheaptrip.R;
import com.fmt.cheaptrip.adapters.TripsAdapter;
import com.fmt.cheaptrip.entities.Trip;
import com.fmt.cheaptrip.entities.TripEntry;
import com.fmt.cheaptrip.entities.User;
import com.fmt.cheaptrip.utils.login.DefaultLoginUtils;
import com.fmt.cheaptrip.webservices.TripWSInvoker;
import com.fmt.cheaptrip.webservices.response.WSResponseListener;
import com.fmt.cheaptrip.webservices.response.WSResponseObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReceivedTripsFragment extends Fragment {

    public static final String TAG = "RECEIVED_TRIPS_FRAGMENT_TAG";

    private TripsAdapter tripsAdapter;

    public ReceivedTripsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_received_trips, container, false);

        ListView listView = (ListView) view.findViewById(R.id.my_trips_fragment_list);

        List<Trip> receivedTrips = receivedTripsList();

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
    private List<Trip> receivedTripsList() {

        final List<Trip> receivedTrips = new ArrayList<>();

        TripWSInvoker.receivedTrips(getActivity(), new WSResponseListener() {
            @Override
            public void onResponse(WSResponseObject response) {
                if (response == null) {
                    //TODO
                } else {
                    receivedTrips.addAll(response.getTrips());
                }
            }

            @Override
            public void onError(VolleyError error) {
                //TODO
            }
        });

        return receivedTrips;
    }


}
