package com.fmt.cheaptrip.fragments;



    import android.content.Context;
    import android.net.Uri;
    import android.os.Bundle;
    import android.support.v4.app.Fragment;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;

    import com.fmt.cheaptrip.R;


    public class GooglePlusFragment extends Fragment {

        public GooglePlusFragment() {
            //
        }

        public static GooglePlusFragment newInstance() {

            GooglePlusFragment fragment = new GooglePlusFragment();

            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            return inflater.inflate(R.layout.activity_login, container, false);
        }

        public void onButtonPressed(Uri uri) {

        }

        @Override
        public void onAttach(Context context) {
            super.onAttach(context);

        }

        @Override
        public void onDetach() {
            super.onDetach();

        }


    }
