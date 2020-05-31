package com.nkechinnaji.foreverfitness.segments.onboarding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.nkechinnaji.foreverfitness.MainActivity;
import com.nkechinnaji.foreverfitness.R;
import com.nkechinnaji.foreverfitness.segments.profile.ProfileActivity;

import java.util.ArrayList;
import java.util.List;

public class OnBoardingActivity extends AppCompatActivity {

        private ViewPager screenPager;
        SliderAdapter sliderPagerAdapter ;
        TabLayout tabIndicator;
        Button btnNext;
        int position = 0 ;
        Button btnGetStarted;
        Animation btnAnim ;
        TextView tvSkip;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);

            if (getSharedPreferencesData()) {
                Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class );
                startActivity(mainActivity);
                finish();
            }

            setContentView(R.layout.activity_intro);

            btnNext = findViewById(R.id.btn_next);
            btnGetStarted = findViewById(R.id.btn_get_started);
            tabIndicator = findViewById(R.id.tab_indicator);
            btnAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.button_animation);
            tvSkip = findViewById(R.id.tv_skip);

            final List<Items> mList = new ArrayList<>();
            mList.add(new Items(getString(R.string.everything_you_need_to_keep_a_healthy_weight),"",R.drawable.ic_fitness_center_weights));
            mList.add(new Items(getString(R.string.view_progress),"",R.drawable.ic_graph));
            mList.add(new Items(getString(R.string.reach_goal),"",R.drawable.ic_target));

            // setup viewpager
            screenPager =findViewById(R.id.screen_viewpager);
            sliderPagerAdapter = new SliderAdapter(this, mList);
            screenPager.setAdapter(sliderPagerAdapter);

            // setup tablayout with viewpager
            tabIndicator.setupWithViewPager(screenPager);

            // next button click Listner
            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    position = screenPager.getCurrentItem();
                    if (position < mList.size()) {
                        position++;
                        screenPager.setCurrentItem(position);
                    }

                    if (position == mList.size()-1) { // when we rech to the last screen

                        loadFinalScreen();
                    }
                }
            });


            tabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {

                    if (tab.getPosition() == mList.size()-1) {

                        loadFinalScreen();
                    }
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                }
                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                }
            });

            btnGetStarted.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //open main activity
                    Intent mainActivity = new Intent(getApplicationContext(), ProfileActivity.class);
                    startActivity(mainActivity);
                    saveToSharedPreferences();
                    finish();

                }
            });

            // skip button click listener
            tvSkip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    screenPager.setCurrentItem(mList.size());
                }
            });

        }

        private boolean getSharedPreferencesData() {
            SharedPreferences pref = getApplicationContext().getSharedPreferences("FITNESS_APP",MODE_PRIVATE);
            Boolean checkActivityOpen = pref.getBoolean("introScreenOpened",false);
            return  checkActivityOpen;
        }

        private void saveToSharedPreferences() {
            SharedPreferences pref = getApplicationContext().getSharedPreferences("FITNESS_APP",MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("introScreenOpened",true);
            editor.commit();


        }

        private void loadFinalScreen() {

            btnNext.setVisibility(View.INVISIBLE);
            btnGetStarted.setVisibility(View.VISIBLE);
            tvSkip.setVisibility(View.INVISIBLE);
            tabIndicator.setVisibility(View.INVISIBLE);
            // setup animation
            btnGetStarted.setAnimation(btnAnim);

        }
    }