package com.fmt.cheaptrip.fragments;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.fmt.cheaptrip.R;
import com.fmt.cheaptrip.activities.IntroductionActivity;
import com.fmt.cheaptrip.activities.LoginActivity;
import com.fmt.cheaptrip.activities.MainActivity;
import com.fmt.cheaptrip.entities.Vehicle;
import com.fmt.cheaptrip.managers.UserAccountManager;
import com.fmt.cheaptrip.utils.LoginUtils;
import com.fmt.cheaptrip.webservices.TripWSInvoker;
import com.fmt.cheaptrip.webservices.response.WSResponseListener;
import com.fmt.cheaptrip.webservices.response.WSResponseObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    public static final String TAG = "PROFILE_FRAGMENT_TAG";

    private EditText fragment_profile_brand;
    private EditText fragment_profile_model;
    private EditText fragment_profile_year;
    private EditText fragment_profile_seats;

    private Button fragment_profile_signout_btn;
    private Button fragment_profile_hardreset_btn;
    private Button fragment_profile_addcar_btn;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        fragment_profile_brand = (EditText) view.findViewById(R.id.fragment_profile_brand);
        fragment_profile_model = (EditText) view.findViewById(R.id.fragment_profile_model);
        fragment_profile_year = (EditText) view.findViewById(R.id.fragment_profile_year);
        fragment_profile_seats = (EditText) view.findViewById(R.id.fragment_profile_seats);

        fragment_profile_signout_btn = (Button) view.findViewById(R.id.fragment_profile_signout_btn);
        fragment_profile_signout_btn.setOnClickListener(signOutListener());

        fragment_profile_hardreset_btn = (Button) view.findViewById(R.id.fragment_profile_hardreset_btn);
        fragment_profile_hardreset_btn.setOnClickListener(hardResetListener());

        fragment_profile_addcar_btn = (Button) view.findViewById(R.id.fragment_profile_addcar_btn);
        fragment_profile_addcar_btn.setOnClickListener(addCarListener());
        return view;
    }

    private View.OnClickListener addCarListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Vehicle vehicle = new Vehicle();
                vehicle.setBrand(fragment_profile_brand.getText().toString());
                vehicle.setModel(fragment_profile_model.getText().toString());
                vehicle.setYear(fragment_profile_year.getText().toString());
                vehicle.setSeatsNumber(Integer.valueOf(fragment_profile_seats.getText().toString()));

                TripWSInvoker.registerUserVehicle(getContext(), vehicle, new WSResponseListener() {
                    @Override
                    public void onResponse(WSResponseObject response) {
                        Toast.makeText(getContext(), "Vehicle Registed Successfully", Toast.LENGTH_SHORT);
                    }

                    @Override
                    public void onError(VolleyError error) {

                    }
                });
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

}
