package com.fmt.cheaptrip.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.Menu;

import com.fmt.cheaptrip.fragments.IntroductionSlideFragment;
import com.fmt.cheaptrip.R;

/**
 * @author TiagoCMS
 */
public class IntroductionActivity extends AppCompatActivity {

    private ViewPager viewPager;

    private IntroductionSliderAdapter introductionSliderAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_introduction);

        viewPager = (ViewPager) findViewById(R.id.pager);
        introductionSliderAdapter = new IntroductionSliderAdapter(getSupportFragmentManager());
        viewPager.setAdapter(introductionSliderAdapter);
    }

        @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {

            super.onBackPressed();
        } else {

            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
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

            return IntroductionSlideFragment.create(position);
        }

        @Override
        public int getCount() {

            return NUMBER_OF_SLIDES;
        }
    }

}
