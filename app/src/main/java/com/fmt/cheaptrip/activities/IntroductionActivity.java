package com.fmt.cheaptrip.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.fmt.cheaptrip.R;
import com.fmt.cheaptrip.Fragments.IntroductionSlideFragment;

/**
 * @author TiagoCMS
 */
public class IntroductionActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.introduction_activity);

        // viewPager = findViewById(R.id.);
    }

    /**
     *
     */
    public class IntroductionSliderAdapter extends FragmentStatePagerAdapter {

        private static final int NUMBER_OF_SLIDES = 3;

        public IntroductionSliderAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);

        }

        @Override
        public Fragment getItem(int position) {

            return new IntroductionSlideFragment();
        }

        @Override
        public int getCount() {

            return NUMBER_OF_SLIDES;
        }
    }

}
