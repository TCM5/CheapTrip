package com.fmt.cheaptrip.Adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.fmt.cheaptrip.Entities.TripEntry;
import com.fmt.cheaptrip.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by santostc on 16-05-2016.
 */
public class TripsAdapter extends ArrayAdapter<TripEntry> {

    private Context context;
    private int resourceId;
    private ArrayList<TripEntry> myTripList;

    MyTripsItemHolder myTripsItemHolder;

    public TripsAdapter(Context context, int resourceId, int resourceViewId, ArrayList<TripEntry> myTripsList) {

        super(context, resourceId, resourceViewId, myTripsList);

        this.context = context;
        this.resourceId = resourceId;
        this.myTripList = myTripsList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // if (convertView == null) {
        convertView = layoutInflater.inflate(R.layout.mytrip_item, parent, false);


        //LayoutInflater.inflate(R.layout.mytrip_item, parent, false);

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

        TripEntry tripEntry = getItem(position);

        // Set the values
        myTripsItemHolder.originLocation.setText("TESTE");
        myTripsItemHolder.destinyLocation.setText("TESTE2");
        myTripsItemHolder.date.setText("21/05/1990 08:00");
        myTripsItemHolder.status.setText("0");

        changeStatusUI();

        return convertView;
    }

    @Override
    public int getCount() {
        return myTripList.size();
    }

    @Override
    public TripEntry getItem(int position) {
        return myTripList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
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
