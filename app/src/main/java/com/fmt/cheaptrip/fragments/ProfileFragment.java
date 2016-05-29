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
import android.widget.Toast;

import com.fmt.cheaptrip.R;
import com.fmt.cheaptrip.activities.LoginActivity;
import com.fmt.cheaptrip.activities.MainActivity;
import com.fmt.cheaptrip.utils.LoginUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    public static final String TAG = "PROFILE_FRAGMENT_TAG";

    private Button fragment_profile_signout_btn;
    private Button fragment_profile_hardreset_btn;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        fragment_profile_signout_btn = (Button) view.findViewById(R.id.fragment_profile_signout_btn);
        fragment_profile_signout_btn.setOnClickListener(signOutListener());

        fragment_profile_hardreset_btn = (Button) view.findViewById(R.id.fragment_profile_hardreset_btn);
        fragment_profile_hardreset_btn.setOnClickListener(hardResetListener());

        return view;
    }

    /**
     * @return
     */
    private View.OnClickListener signOutListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUtils.getInstance().removeSignedLogin(getActivity());
                LoginUtils.getInstance().revokeGplusAccount(getActivity());

                Intent intent = new Intent();
                intent.setClass(getActivity(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(intent);

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
                        .setTitle("Title")
                        .setMessage("Do you really want to whatever?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                Toast.makeText(getActivity(), "Yaay", Toast.LENGTH_SHORT).show();
                            }})
                        .setNegativeButton(android.R.string.no, null).show();



            }
        };
    }

}
