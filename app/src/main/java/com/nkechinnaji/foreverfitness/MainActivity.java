package com.nkechinnaji.foreverfitness;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nkechinnaji.foreverfitness.segments.history.HistoryFragment;
import com.nkechinnaji.foreverfitness.segments.home.HomeFragment;
import com.nkechinnaji.foreverfitness.segments.settings.SettingsFragment;

public class MainActivity extends AppCompatActivity  {

    private String tag = "";
    public static final String HOME_FRAGMENT = "home_fragment";
    private static final String HISTORY_PRAGMENT = "history_fragment";
    private static final String SETTING_PRAGMENT = "setting_fragment";

    private HomeFragment homeFragment = new HomeFragment();
    private HistoryFragment historyFragment = new HistoryFragment();
    private SettingsFragment settingsFragment = new SettingsFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment, homeFragment, HOME_FRAGMENT)
                    .commit();
        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            Fragment selectedFragment = null;

            switch (menuItem.getItemId()) {
                case R.id.navigation_home:
                    tag = HOME_FRAGMENT;
                    selectedFragment = homeFragment;
                    if (selectedFragment == null) {
                        selectedFragment = new HomeFragment();
                    }
                    break;

                case R.id.navigation_history:
                    tag = HISTORY_PRAGMENT;
                    selectedFragment = historyFragment;
                    if (selectedFragment == null) {
                        selectedFragment = new HistoryFragment();
                    }
                    break;
                case R.id.navigation_settings:
                    tag = SETTING_PRAGMENT;
                    selectedFragment = settingsFragment;
                    if (selectedFragment == null) {
                        selectedFragment = new SettingsFragment();
                    }
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, selectedFragment, tag).commit();
            return true;
        }

    };


}
