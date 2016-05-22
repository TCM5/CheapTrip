package com.fmt.cheaptrip.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fmt.cheaptrip.R;
import com.fmt.cheaptrip.activities.LoginActivity;

/**
 * Created by ASUS-TCMS on 13/05/2016.
 */
public class IntroductionSlideFragment extends Fragment implements View.OnClickListener{

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


        switch (slideNumber){
            case 0 :
                rootView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.green4_cheaptrip));
                break;

            case 1 :
                rootView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.green3_cheaptrip));
                break;

            case 2 :
                rootView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.green2_cheaptrip));

                LinearLayout fragment_introduction_slide_finish = (LinearLayout) rootView.findViewById(R.id.fragment_introduction_slide_finish);
                fragment_introduction_slide_finish.setVisibility(View.VISIBLE);

                FloatingActionButton fragment_introduction_slide_finish_fb = (FloatingActionButton) rootView.findViewById(R.id.fragment_introduction_slide_finish_fb);
                fragment_introduction_slide_finish.setOnClickListener(this);

                break;
        }

        // Set the title view to show the page number.
        ((TextView) rootView.findViewById(R.id.text1)).setText(
                getString(R.string.title_template_step, slideNumber +  1));

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

            Intent intent = new Intent();
            intent.setClass(getActivity(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getActivity().startActivity(intent);

    }
}
