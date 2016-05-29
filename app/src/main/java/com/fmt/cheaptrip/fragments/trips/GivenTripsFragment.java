package com.fmt.cheaptrip.fragments.trips;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.fmt.cheaptrip.adapters.TripsAdapter;
import com.fmt.cheaptrip.customviews.GreenProgressDialog;
import com.fmt.cheaptrip.entities.Trip;
import com.fmt.cheaptrip.R;
import com.fmt.cheaptrip.utils.ActivityUtils;
import com.fmt.cheaptrip.webservices.TripWSInvoker;
import com.fmt.cheaptrip.webservices.response.WSResponseListener;
import com.fmt.cheaptrip.webservices.response.WSResponseObject;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GivenTripsFragment extends Fragment {


    public static final String TAG = "GIVEN_TRIPS_FRAGMENT_TAG";

    private ListView listView;
    private LinearLayout emptyListLinearLayout;

    private TripsAdapter tripsAdapter;

    private GreenProgressDialog greenProgressDialog;

    public GivenTripsFragment() {
        // Default constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Call the service
        callServiceGivenTrips();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_given_trips, container, false);

        listView = (ListView) view.findViewById(R.id.given_trips_fragment_list);
        listView.setOnItemClickListener(tripClickListener());
        emptyListLinearLayout = (LinearLayout) view.findViewById(R.id.given_trips_fragment_empty_list_ll);

        tripsAdapter = new TripsAdapter(getActivity(), R.layout.mytrip_header, R.id.mytrip_header_textview);
        listView.setAdapter(tripsAdapter);

        View header = inflater.inflate(R.layout.mytrip_header, null);
        listView.addHeaderView(header);

        return view;
    }

    /**
     * This method calls a webservices responsible for returning given trips for the current user.<br>
     * The result of the webservices will populate a list.
     * Otherwise, if the result is empty a given arraylist is returned<br>
     *
     * @return List of received trips
     */
    private void callServiceGivenTrips() {
        greenProgressDialog = new GreenProgressDialog(getActivity());
        greenProgressDialog.show();

        TripWSInvoker.givenTrips(getActivity(), new WSResponseListener() {
            @Override
            public void onResponse(WSResponseObject response) {
                if (response == null) {
                    //TODO
                } else if (response.getTrips() != null) {
                    List<Trip> givenTrips = response.getTrips();

                    tripsAdapter.refreshTripsList(response.getTrips());
                    greenProgressDialog.dismiss();

                    if (givenTrips.size() < 1) {
                        emptyListLinearLayout.setVisibility(View.VISIBLE);
                        listView.setVisibility(View.GONE);
                    } else {
                        emptyListLinearLayout.setVisibility(View.GONE);
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

    private AdapterView.OnItemClickListener tripClickListener() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Trip trip = (Trip) parent.getItemAtPosition(position);

                Bundle bundle = new Bundle();
                bundle.putParcelable("trip_detail", trip);
                bundle.putParcelable("trip_detail_type", DetailType.GIVEN);
                TripDetailFragment tripDetailFragment = new TripDetailFragment();
                tripDetailFragment.setArguments(bundle);

                ActivityUtils.replaceFragment(getFragmentManager(), tripDetailFragment, R.id.main_content_container, TripDetailFragment.TAG, true);

            }
        };
    }

}
