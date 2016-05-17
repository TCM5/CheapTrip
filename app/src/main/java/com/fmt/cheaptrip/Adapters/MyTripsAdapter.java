package com.fmt.cheaptrip.Adapters;

import android.content.Context;
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
public class MyTripsAdapter extends ArrayAdapter<TripEntry> {

    private Context context;
    private int resourceId;
    private ArrayList<TripEntry> myTripList;

    public MyTripsAdapter(Context context, int resourceId, int resourceViewId, ArrayList<TripEntry> myTripsList) {

        super(context, resourceId, resourceViewId, myTripsList);

        this.context = context;
        this.resourceId = resourceId;
        this.myTripList = myTripsList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        super.getView(position, convertView, parent);

        MyTripsItemHolder myTripsItemHolder = null;


        // if (convertView == null) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.mytrip_item, parent, false);

        myTripsItemHolder = new MyTripsItemHolder();
        myTripsItemHolder.originLocation = (TextView) convertView.findViewById(R.id.mytrip_item_location_origin);
        myTripsItemHolder.destinyLocation = (TextView) convertView.findViewById(R.id.mytrip_item_location_destiny);
        myTripsItemHolder.date = (TextView) convertView.findViewById(R.id.mytrip_item_date);

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

        return convertView;

    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public TripEntry getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }


    public static class MyTripsItemHolder {

        TextView originLocation;
        TextView destinyLocation;
        TextView date;

    }

}
