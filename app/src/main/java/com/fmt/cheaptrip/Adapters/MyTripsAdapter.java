package com.fmt.cheaptrip.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.fmt.cheaptrip.Entities.TripEntry;
import com.fmt.cheaptrip.R;

import java.util.ArrayList;

/**
 * Created by santostc on 16-05-2016.
 */
public class MyTripsAdapter extends ArrayAdapter<TripEntry> {


    public MyTripsAdapter(Context context, ArrayList<TripEntry> myTripsList) {
        super(context, 0, myTripsList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        super.getView(position, convertView, parent);

        TripEntry tripEntry = getItem(position);

        if (convertView != null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.mytrip_item, parent, false);
        }

        TextView myTripOriginTextView = (TextView) convertView.findViewById(R.id.mytrip_item_location_origin);
        TextView myTripDestinyTextView = (TextView) convertView.findViewById(R.id.mytrip_item_location_destiny);

        // Set the values
        myTripOriginTextView.setText(tripEntry.getOriginLocation().getLocality());
        myTripDestinyTextView.setText(tripEntry.getDestinyLocation().getLocality());

        return convertView;

    }
}
