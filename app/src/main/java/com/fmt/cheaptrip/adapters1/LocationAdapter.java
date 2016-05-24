package com.fmt.cheaptrip.adapters1;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.fmt.cheaptrip.entities1.LocationEntry;
import com.fmt.cheaptrip.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by ASUS-TCMS on 15/05/2016.
 */
public class LocationAdapter extends BaseAdapter implements Filterable {

    private static final int MAX_LOCATIONS_RESULTS = 5;

    private Context context;

    private ArrayList<LocationEntry> resultsList = new ArrayList();

    public LocationAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return getResultsList().size();
    }

    @Override
    public LocationEntry getItem(int position) {
        return (LocationEntry) getResultsList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.map_fragment_location_result, parent, false);


        String address = getItem(position).getAddress();

        TextView tv = ((TextView) convertView.findViewById(R.id.map_fragment_location_result_entry));

        if (tv != null) {
            tv.setText(address);
        }

        return convertView;
    }

    /**
     * @param inputLocation
     * @return
     */
    private ArrayList<LocationEntry> searchLocation(String inputLocation) {

        ArrayList<LocationEntry> locationsResults = new ArrayList<>();

        Locale locale = context.getResources().getConfiguration().locale;

        Geocoder geocoder = new Geocoder(context, locale);

        try {

            List<Address> addressesResultList = geocoder.getFromLocationName(inputLocation, MAX_LOCATIONS_RESULTS);

            for (Address address : addressesResultList) {

                if (address.getMaxAddressLineIndex() >= 0) {
                    LocationEntry locationEntry = new LocationEntry(address);
                    locationsResults.add(locationEntry);
                }
            }

        } catch (IOException e) {
            Log.d("", String.valueOf(e.getCause()));
            e.printStackTrace();

        }

        return locationsResults;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();

                if (constraint != null) {
                    //Just for defensive compile-time :)
                    StringBuilder sb = new StringBuilder(constraint.length());
                    sb.append(constraint);


                    ArrayList<LocationEntry> locationsToFilterList = searchLocation(sb.toString());

                    filterResults.values = locationsToFilterList;
                    filterResults.count = locationsToFilterList.size();
                } else {
                    //    filterResults.values = Collections.emptyList();
                    //  filterResults.count = 0;
                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                if (results != null) {
                    setResultsList((ArrayList) results.values);

                    if (results.count > 0) {
                        notifyDataSetChanged();
                    } else {
                        notifyDataSetInvalidated();
                    }
                }
            }
        };
    }

    /**
     * @return
     */
    public ArrayList getResultsList() {
        return resultsList;
    }

    /**
     * @param resultsList
     */
    public void setResultsList(ArrayList resultsList) {
        this.resultsList = resultsList;
    }
}