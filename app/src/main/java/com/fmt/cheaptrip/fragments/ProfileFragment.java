package com.fmt.cheaptrip.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.fmt.cheaptrip.R;
import com.fmt.cheaptrip.activities.LoginActivity;
import com.fmt.cheaptrip.utils.LoginUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private Button fragment_profile_signout_btn;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        fragment_profile_signout_btn = (Button) view.findViewById(R.id.fragment_profile_signout_btn);
        fragment_profile_signout_btn.setOnClickListener(signOutListener());


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

}
