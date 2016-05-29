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
import com.fmt.cheaptrip.R;
import com.fmt.cheaptrip.adapters.TripsAdapter;
import com.fmt.cheaptrip.customviews.GreenProgressDialog;
import com.fmt.cheaptrip.entities.Trip;
import com.fmt.cheaptrip.utils.ActivityUtils;
import com.fmt.cheaptrip.webservices.TripWSInvoker;
import com.fmt.cheaptrip.webservices.response.WSResponseListener;
import com.fmt.cheaptrip.webservices.response.WSResponseObject;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchTripFragment extends Fragment {

    public static final String TAG = "SEARCH_TRIPS_FRAGMENT_TAG";

    private GreenProgressDialog greenProgressDialog;

    private String addressOrigin;
    private String addressDestiny;

    private ListView listView;
    private LinearLayout emptyListLinearLayout;

    private TripsAdapter tripsAdapter;

    public SearchTripFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addressOrigin = getArguments().getString("addressOrigin");
        addressDestiny = getArguments().getString("addressDestiny");

        callServiceSearchTrips(addressOrigin, addressDestiny);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_trips, container, false);

        listView = (ListView) view.findViewById(R.id.search_trips_fragment_list);
        emptyListLinearLayout = (LinearLayout) view.findViewById(R.id.search_trips_fragment_empty_list_ll);
        listView.setOnItemClickListener(tripClickListener());

        tripsAdapter = new TripsAdapter(getActivity(), R.layout.mytrip_header, R.id.mytrip_header_textview);
        listView.setAdapter(tripsAdapter);

        View header = inflater.inflate(R.layout.mytrip_header, null);
        listView.addHeaderView(header);


        return view;
    }

    /**
     * TODO
     *
     * @return List of trips
     */
    private void callServiceSearchTrips(String addressOrigin, String addressDestiny) {
        greenProgressDialog = new GreenProgressDialog(getActivity());
        greenProgressDialog.show();

        TripWSInvoker.searchTrip(getActivity(), addressOrigin, addressDestiny, new WSResponseListener() {
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
                bundle.putParcelable("trip_detail_type", DetailType.SEARCHED);

                TripDetailFragment tripDetailFragment = new TripDetailFragment();
                tripDetailFragment.setArguments(bundle);

                ActivityUtils.replaceFragment(getFragmentManager(), tripDetailFragment, R.id.main_content_container, TripDetailFragment.TAG, true);

            }
        };
    }

}
