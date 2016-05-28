package com.fmt.cheaptrip.fragments.trips;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.fmt.cheaptrip.R;
import com.fmt.cheaptrip.entities.Bagage;
import com.fmt.cheaptrip.entities.Trip;
import com.fmt.cheaptrip.entities.Vehicle;
import com.fmt.cheaptrip.managers.UserAccountManager;
import com.fmt.cheaptrip.webservices.TripWSInvoker;
import com.fmt.cheaptrip.webservices.response.WSResponseListener;
import com.fmt.cheaptrip.webservices.response.WSResponseObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TripDetailFragment extends Fragment {

    public static final String TAG = "TRIP_DETAIL_FRAGMENT_TAG";

    private final String PORTUGAL_CURRENCY = "€";
    private final String MINUTES = "min"; //AHAHAHAHHAHAH

    // Location section views
    private TextView new_trip_fragment_origin_city_tv;
    private TextView new_trip_fragment_destiny_city_tv;

    // Date section views
    private DatePicker new_trip_fragment_date_dp;

    // Price section views
    private TextView new_trip_fragment_price_tv_value;

    // Car section vies
    private Spinner new_trip_fragment_car_spinner_value;

    // Bagage
    private ImageView new_trip_fragment_bagage_icon;

    // Time tolerance views
    private TextView new_trip_fragment_tolerance_tv_value;


    public TripDetailFragment() {
        //Default constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trip_detail, container, false);

        Trip trip = getArguments().getParcelable("trip_detail");

        if (trip != null) {

        } else {
            Toast.makeText(getActivity(), "", Toast.LENGTH_LONG);
        }

       /* // Location section views
        new_trip_fragment_origin_city_tv = (TextView) view.findViewById(R.id.new_trip_fragment_origin_city_tv);
        new_trip_fragment_origin_city_tv.setText(getArguments().get("addressOrigin").toString());
        new_trip_fragment_destiny_city_tv = (TextView) view.findViewById(R.id.new_trip_fragment_destiny_city_tv);
        new_trip_fragment_destiny_city_tv.setText(getArguments().get("addressDestiny").toString());

        // Date section views
        new_trip_fragment_date_dp = (DatePicker) view.findViewById(R.id.new_trip_fragment_date_dp);

        // Price section views
        new_trip_fragment_price_tv_value = (TextView) view.findViewById(R.id.new_trip_fragment_price_tv_value);

        // Car section vies

        // Bagage
        final ArrayList<Bagage> baggageTypesMap = new ArrayList<>();
        baggageTypesMap.add(new Bagage("S", "Small", ContextCompat.getDrawable(getActivity(), R.drawable.bagage_icon_small)));
        baggageTypesMap.add(new Bagage("M", "Medium", ContextCompat.getDrawable(getActivity(), R.drawable.bagage_icon_medium)));
        baggageTypesMap.add(new Bagage("L", "Large", ContextCompat.getDrawable(getActivity(), R.drawable.bagage_icon_large)));

        new_trip_fragment_bagage_icon = (ImageView) view.findViewById(R.id.new_trip_fragment_bagage_icon);
        new_trip_fragment_bagage_icon.setImageDrawable(baggageTypesMap.get(0).getIcon());
        // Time tolerance views

        new_trip_fragment_tolerance_tv_value = (TextView) view.findViewById(R.id.new_trip_fragment_tolerance_tv_value);
        new_trip_fragment_tolerance_tv_value.setText("");

*/
        return view;
    }


}
