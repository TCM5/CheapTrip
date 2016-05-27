package com.fmt.cheaptrip.fragments.trips;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.fmt.cheaptrip.R;
import com.fmt.cheaptrip.adapters.TripsAdapter;
import com.fmt.cheaptrip.customviews.GreenProgressDialog;
import com.fmt.cheaptrip.entities.Trip;
import com.fmt.cheaptrip.webservices.TripWSInvoker;
import com.fmt.cheaptrip.webservices.response.WSResponseListener;
import com.fmt.cheaptrip.webservices.response.WSResponseObject;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReceivedTripsFragment extends Fragment {

    public static final String TAG = "RECEIVED_TRIPS_FRAGMENT_TAG";

    private ListView listView;
    private TextView emptyListTextView;

    private TripsAdapter tripsAdapter;

    private GreenProgressDialog greenProgressDialog;

    public ReceivedTripsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_received_trips, container, false);

        listView = (ListView) view.findViewById(R.id.my_trips_fragment_list);
        emptyListTextView = (TextView) view.findViewById(R.id.received_trips_fragment_empty_list_tv);

        tripsAdapter = new TripsAdapter(getActivity(), R.layout.mytrip_header, R.id.mytrip_header_textview);

        listView.setAdapter(tripsAdapter);

        View header = inflater.inflate(R.layout.mytrip_header, null);
        listView.addHeaderView(header);

        callServiceReceivedTrips();

        return view;
    }

    /**
     * This method calls a webservices responsible for returning received trips for the current user.<br>
     * The result of the webservices will populate a list.
     * Otherwise, if the result is empty a given arraylist is returned<br>
     *
     * @return List of received trips
     */
    private void callServiceReceivedTrips() {

        greenProgressDialog = new GreenProgressDialog(getActivity());
        greenProgressDialog.show();

        TripWSInvoker.receivedTrips(getActivity(), new WSResponseListener() {

            @Override
            public void onResponse(WSResponseObject response) {
                if (response == null) {
                    //TODO
                } else if (response.getTrips() != null) {
                    List<Trip> receivedTrips = response.getTrips();

                    tripsAdapter.refreshTripsList(receivedTrips);

                    if (receivedTrips.size() < 1) {
                        emptyListTextView.setVisibility(View.VISIBLE);
                        listView.setVisibility(View.GONE);
                    } else {
                        emptyListTextView.setVisibility(View.GONE);
                        listView.setVisibility(View.VISIBLE);
                    }


                }
                greenProgressDialog.dismiss();
            }

            @Override
            public void onError(VolleyError error) {
                //TODO
                greenProgressDialog.dismiss();
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        //TODO
    }
}