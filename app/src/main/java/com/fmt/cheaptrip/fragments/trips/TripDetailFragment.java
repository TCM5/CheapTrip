package com.fmt.cheaptrip.fragments.trips;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.fmt.cheaptrip.R;
import com.fmt.cheaptrip.activities.ConfirmTripByQRCode;
import com.fmt.cheaptrip.activities.MainActivity;
import com.fmt.cheaptrip.entities.Baggage;
import com.fmt.cheaptrip.entities.SubscribeTrip;
import com.fmt.cheaptrip.entities.Trip;
import com.fmt.cheaptrip.entities.Vehicle;
import com.fmt.cheaptrip.managers.UserAccountManager;
import com.fmt.cheaptrip.webservices.TripWSInvoker;
import com.fmt.cheaptrip.webservices.response.WSResponseListener;
import com.fmt.cheaptrip.webservices.response.WSResponseObject;

import net.glxn.qrgen.android.QRCode;

import org.w3c.dom.Text;

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

    // Date section view
    private TextView new_trip_fragment_date_tv;

    // Hour section view
    private TextView new_trip_fragment_hour_tv;

    // Price section views
    private TextView new_trip_fragment_price_tv_value;

    // Car section vies
    private TextView new_trip_fragment_car_tv_value;

    // Baggage
    private ImageView new_trip_fragment_bagage_icon;
    private TextView new_trip_fragment_baggage_tv_value;

    // Time tolerance views
    private TextView new_trip_fragment_tolerance_tv_value;

    // QR code
    private ImageView qrCodeImageView;

    private TextView confirmTrip;

    private TextView joinTrip;


    public TripDetailFragment() {
        //Default constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trip_detail, container, false);

        Trip trip = getArguments().getParcelable("trip_detail");

        if (trip != null) {

            new_trip_fragment_origin_city_tv = (TextView) view.findViewById(R.id.new_trip_fragment_origin_city_tv);

            if (trip.getStartCity() != null) {
                new_trip_fragment_origin_city_tv.setText(trip.getStartCity());
            }

            new_trip_fragment_destiny_city_tv = (TextView) view.findViewById(R.id.new_trip_fragment_destiny_city_tv);

            if (trip.getEndCity() != null) {
                new_trip_fragment_destiny_city_tv.setText(trip.getEndCity());
            }

            // Date section views
            new_trip_fragment_date_tv = (TextView) view.findViewById(R.id.new_trip_fragment_date_tv);
            new_trip_fragment_date_tv.setText(DateUtils.formatDateTime(getActivity(), trip.getTripDate().getTime(), DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR));

            // Hour section views
            new_trip_fragment_date_tv = (TextView) view.findViewById(R.id.new_trip_fragment_hour_tv);
            new_trip_fragment_date_tv.setText(DateUtils.formatDateTime(getActivity(), trip.getTripDate().getTime(), DateUtils.FORMAT_SHOW_TIME));


            // Price section views
            new_trip_fragment_price_tv_value = (TextView) view.findViewById(R.id.new_trip_fragment_price_tv_value);

            if (trip.getPrice() != null) {
                new_trip_fragment_price_tv_value.setText(String.valueOf(trip.getPrice()) + " " + "€");
            }
            // Car section vies
            new_trip_fragment_car_tv_value = (TextView) view.findViewById(R.id.new_trip_fragment_car_tv_value);

            if (trip.getVehicleBrand() != null && trip.getVehicleModel() != null) {
                new_trip_fragment_car_tv_value.setText(trip.getVehicleBrand() + " " + trip.getVehicleModel());
            }

            // Baggage
            Baggage baggage = new Baggage(trip.getBaggageSize(), getActivity());

            new_trip_fragment_bagage_icon = (ImageView) view.findViewById(R.id.new_trip_fragment_bagage_icon);
            new_trip_fragment_bagage_icon.setImageDrawable(baggage.getIcon());

            new_trip_fragment_baggage_tv_value = (TextView) view.findViewById(R.id.new_trip_fragment_baggage_tv_value);
            new_trip_fragment_baggage_tv_value.setText(baggage.getDesc());

            // Time tolerance views
            new_trip_fragment_tolerance_tv_value = (TextView) view.findViewById(R.id.new_trip_fragment_tolerance_tv_value);

            if (trip.getDelayTolerance() != null) {
                new_trip_fragment_tolerance_tv_value.setText(String.valueOf(trip.getDelayTolerance()) + " " + "min");
            }

            // Confirm
            confirmTrip = (TextView) view.findViewById(R.id.confirmTrip);
            confirmTrip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    redirectToConfirmTripByQRCode();
                }
            });

            // Join
            joinTrip = (TextView) view.findViewById(R.id.joinTrip);
            joinTrip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // CALL SERVICE


                }
            });

            // QR Code
            qrCodeImageView = (ImageView) view.findViewById(R.id.qrCodeImageView);

            if (DetailType.GIVEN.equals(getDetailType())) {
                Bitmap myBitmap = QRCode.from(String.valueOf(trip.getTripId())).withSize(600, 600).bitmap();
                qrCodeImageView.setImageBitmap(myBitmap);
                qrCodeImageView.setVisibility(View.VISIBLE);
            } else if (DetailType.RECEIVED.equals(getDetailType())) {
                confirmTrip.setVisibility(View.VISIBLE);

                if (trip.getConfirmDate() != null) {
                    confirmTrip.setText("Trip Confirmed");
                    confirmTrip.setEnabled(false);
                }
            } else if (DetailType.SEARCHED.equals(getDetailType())) {
                joinTrip.setVisibility(View.VISIBLE);
            }

        } else {
            Toast.makeText(getActivity(), "", Toast.LENGTH_LONG);
        }

        return view;
    }

    public DetailType getDetailType() {
        return getArguments().getParcelable("trip_detail_type");
    }

    private void redirectToConfirmTripByQRCode() {
        Intent intent = new Intent();
        intent.setClass(getActivity().getApplicationContext(), ConfirmTripByQRCode.class);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                String tripId = data.getStringExtra("tripId");

                TripWSInvoker.confirmTrip(getActivity().getApplicationContext(), tripId, new WSResponseListener() {
                    @Override
                    public void onResponse(WSResponseObject response) {
                        Toast.makeText(getContext(), "Trip Confirmed", Toast.LENGTH_SHORT).show();
                        confirmTrip.setText("Trip Confirmed");
                        confirmTrip.setEnabled(false);
                    }

                    @Override
                    public void onError(VolleyError error) {
                    }
                });
            } else if (resultCode == Activity.RESULT_CANCELED) {
            }
        }
    }
}
