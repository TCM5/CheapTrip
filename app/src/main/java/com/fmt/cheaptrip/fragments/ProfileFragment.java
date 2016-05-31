package com.fmt.cheaptrip.fragments;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.fmt.cheaptrip.R;
import com.fmt.cheaptrip.activities.IntroductionActivity;
import com.fmt.cheaptrip.activities.LoginActivity;
import com.fmt.cheaptrip.activities.MainActivity;
import com.fmt.cheaptrip.entities.Baggage;
import com.fmt.cheaptrip.entities.Vehicle;
import com.fmt.cheaptrip.managers.UserAccountManager;
import com.fmt.cheaptrip.utils.LoginUtils;
import com.fmt.cheaptrip.webservices.TripWSInvoker;
import com.fmt.cheaptrip.webservices.response.WSResponseListener;
import com.fmt.cheaptrip.webservices.response.WSResponseObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    public static final String TAG = "PROFILE_FRAGMENT_TAG";

    private EditText fragment_profile_brand;
    private EditText fragment_profile_model;
    private EditText fragment_profile_year;
    private EditText fragment_profile_seats;

    private TextView fragment_profile_signout_btn;
    private TextView fragment_profile_hardreset_btn;
    private TextView fragment_profile_addcar_btn;
    private TextView fragment_profile_clear_car_form;

    private ListView fragment_profile_vehicle_list;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        fragment_profile_brand = (EditText) view.findViewById(R.id.fragment_profile_brand);
        fragment_profile_model = (EditText) view.findViewById(R.id.fragment_profile_model);
        fragment_profile_year = (EditText) view.findViewById(R.id.fragment_profile_year);
        fragment_profile_seats = (EditText) view.findViewById(R.id.fragment_profile_seats);
        fragment_profile_vehicle_list = (ListView) view.findViewById(R.id.fragment_profile_vehicle_list);

        final ArrayAdapter<Vehicle> vehicleListAdapter = new ArrayAdapter<Vehicle>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, new ArrayList<Vehicle>());
        fragment_profile_vehicle_list.setAdapter(vehicleListAdapter);
        fragment_profile_vehicle_list.setOnItemLongClickListener(itemLongClickListener(vehicleListAdapter));
        fragment_profile_vehicle_list.setOnItemClickListener(onListViewClickListener(vehicleListAdapter));
        fillVehiclesListView(vehicleListAdapter);

        fragment_profile_addcar_btn = (TextView) view.findViewById(R.id.fragment_profile_addcar_btn);
        fragment_profile_addcar_btn.setOnClickListener(addCarListener(vehicleListAdapter));

        fragment_profile_signout_btn = (TextView) view.findViewById(R.id.fragment_profile_signout_btn);
        fragment_profile_signout_btn.setOnClickListener(signOutListener());

        fragment_profile_hardreset_btn = (TextView) view.findViewById(R.id.fragment_profile_hardreset_btn);
        fragment_profile_hardreset_btn.setOnClickListener(hardResetListener());


        fragment_profile_addcar_btn = (TextView) view.findViewById(R.id.fragment_profile_addcar_btn);
        fragment_profile_addcar_btn.setOnClickListener(addCarListener(vehicleListAdapter));

        fragment_profile_clear_car_form = (TextView) view.findViewById(R.id.fragment_profile_clean_btn);
        fragment_profile_clear_car_form.setOnClickListener(clearVehicleFormListener());

        return view;
    }

    private void fillVehiclesListView(final ArrayAdapter<Vehicle> vehicleListAdapter) {
        TripWSInvoker.getUserVehicles(getContext(), new WSResponseListener() {
            @Override
            public void onResponse(WSResponseObject response) {
                if (response.getVehicles() != null && response.getVehicles().size() > 0) {
                    vehicleListAdapter.addAll(response.getVehicles());
                    vehicleListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(VolleyError error) {
            }
        });
    }

    private View.OnClickListener addCarListener(final ArrayAdapter<Vehicle> vehicleListAdaptert) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String brand = fragment_profile_brand.getText().toString();
                String model = fragment_profile_model.getText().toString();
                String year = fragment_profile_year.getText().toString();
                String seats = fragment_profile_seats.getText().toString();

                if (brand == null || brand.isEmpty() || model == null || model.isEmpty() || year == null || year.isEmpty() || seats == null || seats.isEmpty()) {

                    Toast.makeText(getActivity(), R.string.empty_fields_msg, Toast.LENGTH_SHORT).show();

                } else if (!fragment_profile_brand.isEnabled()) { //Basta validar se um esta disable, porque se um esta todos estao
                    Toast.makeText(getActivity(), R.string.cant_add_car_twice, Toast.LENGTH_SHORT).show();

                } else {

                    final Vehicle vehicle = new Vehicle();
                    vehicle.setBrand(brand);
                    vehicle.setModel(model);
                    vehicle.setYear(year);
                    vehicle.setSeatsNumber(Integer.valueOf(seats));

                    TripWSInvoker.registerUserVehicle(getContext(), vehicle, new WSResponseListener() {
                        @Override
                        public void onResponse(WSResponseObject response) {
                            if ("true".equalsIgnoreCase(response.getSuccess())) {
                                Toast.makeText(getContext(), R.string.vechicle_registered_msg, Toast.LENGTH_SHORT).show();
                                vehicle.setVehicleId(response.getVehicle().getVehicleId());
                                vehicleListAdaptert.add(vehicle);
                                vehicleListAdaptert.notifyDataSetChanged();
                                clearForm();
                            } else {
                                Toast.makeText(getActivity(), R.string.unknow_error_msg, Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(VolleyError error) {

                        }
                    });
                }
            }
        };
    }

    /**
     * @return
     */

    private View.OnClickListener signOutListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(getActivity())
                        .setTitle("Hard reset")
                        .setMessage("Do you really want to logout?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {

                                UserAccountManager.logout(getActivity());

                                Intent intent = new Intent();
                                intent.setClass(getActivity(), LoginActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                getActivity().startActivity(intent);

                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();
            }
        };
    }

    /**
     * @return
     */
    private View.OnClickListener hardResetListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(getActivity())
                        .setTitle("Hard reset")
                        .setMessage("Do you really want to make an hardreset? If yes, all yout data will be cleared")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {

                                UserAccountManager.clearAllData(getActivity());

                                Intent intent = new Intent();
                                intent.setClass(getActivity(), IntroductionActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                getActivity().startActivity(intent);

                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();
            }
        };
    }

    private AdapterView.OnItemClickListener onListViewClickListener(final ArrayAdapter<Vehicle> vehicleListAdapter) {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Vehicle vehicle = vehicleListAdapter.getItem(i);
                setFormEnabled(false);
                fragment_profile_brand.setText(vehicle.getBrand());
                fragment_profile_model.setText(vehicle.getModel());
                fragment_profile_year.setText(vehicle.getYear());
                fragment_profile_seats.setText(String.valueOf(vehicle.getSeatsNumber()));
            }
        };
    }

    private AdapterView.OnItemLongClickListener itemLongClickListener(final ArrayAdapter<Vehicle> vehicleListAdapter) {

        return new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                final Vehicle vehicle = vehicleListAdapter.getItem(i);

                new AlertDialog.Builder(getActivity())
                        .setTitle("Delete Car")
                        .setMessage("Do you really want delete " + vehicle.getBrand() + " " + vehicle.getModel() + " ?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                TripWSInvoker.deleteUserVehicle(getContext(), vehicle.getVehicleId(), new WSResponseListener() {
                                    @Override
                                    public void onResponse(WSResponseObject response) {
                                        if ("true".equalsIgnoreCase(response.getSuccess())) {
                                            vehicleListAdapter.remove(vehicle);
                                            vehicleListAdapter.notifyDataSetChanged();
                                            Toast.makeText(getContext(), vehicle.getBrand() + " " + vehicle.getModel() + " deleted", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onError(VolleyError error) {

                                    }
                                });
                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();
                return false;
            }
        };
    }

    private View.OnClickListener clearVehicleFormListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFormEnabled(true);
                clearForm();
            }
        };
    }

    private void setFormEnabled(boolean flag) {
        fragment_profile_brand.setEnabled(flag);
        fragment_profile_model.setEnabled(flag);
        fragment_profile_year.setEnabled(flag);
        fragment_profile_seats.setEnabled(flag);
    }

    private void clearForm() {
        fragment_profile_brand.setText("");
        fragment_profile_model.setText("");
        fragment_profile_year.setText("");
        fragment_profile_seats.setText("");
    }
}
