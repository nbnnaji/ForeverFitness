package com.nkechinnaji.foreverfitness;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nkechinnaji.foreverfitness.ui.entries.EntriesViewModel;
import com.nkechinnaji.foreverfitness.ui.history.HistoryFragment;
import com.nkechinnaji.foreverfitness.ui.home.HomeFragment;
import com.nkechinnaji.foreverfitness.ui.pictures.PicturesFragment;
import com.nkechinnaji.foreverfitness.ui.settings.SettingsFragment;

public class MainActivity extends AppCompatActivity {

    private EntriesViewModel mEntriesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEntriesViewModel = ViewModelProviders.of(this).get(EntriesViewModel.class);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment, new HomeFragment())
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
                    selectedFragment = new HomeFragment();
                    break;

                case R.id.navigation_pictures:
                    selectedFragment = new PicturesFragment();
                    break;
                case R.id.navigation_history:
                    selectedFragment = new HistoryFragment();
                    break;
                case R.id.navigation_settings:
                    selectedFragment = new SettingsFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                    selectedFragment).commit();
            return true;
        }

    };

}
