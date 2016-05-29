package com.fmt.cheaptrip.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fmt.cheaptrip.R;
import com.fmt.cheaptrip.activities.LoginActivity;

/**
 * Created by ASUS-TCMS on 13/05/2016.
 */
public class IntroductionSlideFragment extends Fragment implements View.OnClickListener {

    /**
     * The argument key for the page number this fragment represents.
     */
    public static final String ARG_PAGE = "page";

    /**
     * The fragment's page number, which is set to the argument value for {@link #ARG_PAGE}.
     */
    private int slideNumber;


    public static IntroductionSlideFragment create(int pageNumber) {
        IntroductionSlideFragment fragment = new IntroductionSlideFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        slideNumber = getArguments().getInt(ARG_PAGE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_introducion_slide, container, false);

        TextView fragment_introduction_slide_title_tv = (TextView) rootView.findViewById(R.id.fragment_introduction_slide_title_tv);
        ImageView fragment_introduction_slide_image_iv = (ImageView) rootView.findViewById(R.id.fragment_introduction_slide_image_iv);
        TextView fragment_introduction_slide_desc_tv = (TextView) rootView.findViewById(R.id.fragment_introduction_slide_desc_tv);

        switch (slideNumber) {
            case 0:
                rootView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.white));
                fragment_introduction_slide_title_tv.setText(getString(R.string.fragment_introduction_slide_title1_text));
                fragment_introduction_slide_title_tv.setTextColor(ContextCompat.getColor(getActivity(), R.color.green4_cheaptrip));
                fragment_introduction_slide_desc_tv.setText(getString(R.string.fragment_introduction_slide_desc1_text));
                fragment_introduction_slide_desc_tv.setTextColor(ContextCompat.getColor(getActivity(), R.color.green4_cheaptrip));

                Drawable myIcon0 = ContextCompat.getDrawable(getActivity(), R.drawable.economic);
                int color0 = ContextCompat.getColor(getActivity(), R.color.green4_cheaptrip);

                ColorFilter filter0 = new LightingColorFilter(color0, color0);
                myIcon0.setColorFilter(filter0);

                fragment_introduction_slide_image_iv.setImageDrawable(myIcon0);

                break;

            case 1:
                rootView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.green4_cheaptrip));
                fragment_introduction_slide_title_tv.setText(getString(R.string.fragment_introduction_slide_title2_text));
                fragment_introduction_slide_title_tv.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
                fragment_introduction_slide_desc_tv.setText(getString(R.string.fragment_introduction_slide_desc2_text));
                fragment_introduction_slide_desc_tv.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));

                Drawable myIcon = ContextCompat.getDrawable(getActivity(), R.drawable.friends);
                int color = ContextCompat.getColor(getActivity(), R.color.white);

                ColorFilter filter = new LightingColorFilter(color, color);
                myIcon.setColorFilter(filter);

                fragment_introduction_slide_image_iv.setImageDrawable(myIcon);
                break;

            case 2:
                rootView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.white));
                fragment_introduction_slide_title_tv.setText(getString(R.string.fragment_introduction_slide_title3_text));
                fragment_introduction_slide_title_tv.setTextColor(ContextCompat.getColor(getActivity(), R.color.green4_cheaptrip));
                fragment_introduction_slide_desc_tv.setText(getString(R.string.fragment_introduction_slide_desc3_text));
                fragment_introduction_slide_desc_tv.setTextColor(ContextCompat.getColor(getActivity(), R.color.green4_cheaptrip));

                Drawable myIcon2 = ContextCompat.getDrawable(getActivity(), R.drawable.green);
                int color2 = ContextCompat.getColor(getActivity(), R.color.green4_cheaptrip);

                ColorFilter filter2 = new LightingColorFilter(color2, color2);
                myIcon2.setColorFilter(filter2);


                fragment_introduction_slide_image_iv.setImageDrawable(myIcon2);

                TextView fragment_introduction_slide_finish_tv = (TextView) rootView.findViewById(R.id.fragment_introduction_slide_finish_tv);
                fragment_introduction_slide_finish_tv.setVisibility(View.VISIBLE);
                fragment_introduction_slide_finish_tv.setOnClickListener(this);

                break;
        }

        return rootView;
    }

    /**
     * Returns the page number represented by this fragment object.
     */
    public int getPageNumber() {

        return slideNumber;
    }


    @Override
    public void onClick(View v) {

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("signed_firsttime", false);
        editor.commit();

        Intent intent = new Intent();
        intent.setClass(getActivity(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getActivity().startActivity(intent);
    }
}
