package com.fmt.cheaptrip.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.fmt.cheaptrip.entities.Trip;
import com.fmt.cheaptrip.entities.TripEntry;
import com.fmt.cheaptrip.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by santostc on 16-05-2016.
 */
public class TripsAdapter extends ArrayAdapter<Trip> {

    private Context context;
    private int resourceId;
    private List<Trip> tripsList = new ArrayList<>();

    private MyTripsItemHolder myTripsItemHolder;

    public TripsAdapter(Context context, int resourceId, int resourceViewId, List<Trip> tripsList) {

        super(context, resourceId, resourceViewId, tripsList);

        this.context = context;
        this.resourceId = resourceId;
        this.tripsList = tripsList;
    }

    public TripsAdapter(Context context, int resourceId, int resourceViewId) {
        super(context, resourceId, resourceViewId);

        this.context = context;
        this.resourceId = resourceId;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // if (convertView == null) {
        convertView = layoutInflater.inflate(R.layout.mytrip_item, parent, false);


        myTripsItemHolder = new MyTripsItemHolder();
        myTripsItemHolder.originLocation = (TextView) convertView.findViewById(R.id.mytrip_item_location_origin);
        myTripsItemHolder.destinyLocation = (TextView) convertView.findViewById(R.id.mytrip_item_location_destiny);
        myTripsItemHolder.date = (TextView) convertView.findViewById(R.id.mytrip_item_date);
        myTripsItemHolder.status = (TextView) convertView.findViewById(R.id.mytrip_item_status);

        convertView.setTag(myTripsItemHolder);
        // }

       /* else {
            myTripsItemHolder = (MyTripsItemHolder) convertView.getTag();

        }*/

        Trip trip = getItem(position);

        // Set the values
        myTripsItemHolder.originLocation.setText(trip.getStartCity());
        myTripsItemHolder.destinyLocation.setText(trip.getEndCity());
        myTripsItemHolder.date.setText(trip.getDate().toString());
        myTripsItemHolder.status.setText("0");

        changeStatusUI();

        return convertView;
    }

    @Override
    public int getCount() {
        return tripsList.size();
    }

    @Override
    public Trip getItem(int position) {
        return tripsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    /**
     * This method is responsible to refresh the list used in this adatpers.
     * This is specially util because this adapter is called before a response of an webservice.
     * So after the response, the list has to be updated
     *
     * @param newlist
     */
    public void refreshTripsList(List<Trip> newlist) {
        tripsList.clear();
        tripsList.addAll(newlist);
        this.notifyDataSetChanged();
    }

    /*
    *
     */
    private void changeStatusUI() {

        int color = ContextCompat.getColor(context, R.color.colorPrimaryDark); //temporary color

        String status = myTripsItemHolder.status.getText().toString();

        switch (status) {

            // Open trip
            case "0":
                color = ContextCompat.getColor(context, R.color.green4_cheaptrip);
                break;

            // Closed trip
            case "1":
                color = ContextCompat.getColor(context, R.color.red);
                break;

            default:
                //TODO
                break;
        }

        myTripsItemHolder.status.setTextColor(color);
    }

    /**
     * This class is just a holder that holds the trip item components.<br>
     */
    public static class MyTripsItemHolder {

        TextView originLocation;
        TextView destinyLocation;
        TextView date;
        TextView status;
    }

}
