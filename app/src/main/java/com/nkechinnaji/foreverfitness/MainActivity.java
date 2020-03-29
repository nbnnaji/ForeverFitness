package com.nkechinnaji.foreverfitness;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nkechinnaji.foreverfitness.ui.entries.EntriesFragment;
import com.nkechinnaji.foreverfitness.ui.history.HistoryFragment;
import com.nkechinnaji.foreverfitness.ui.home.HomeFragment;
import com.nkechinnaji.foreverfitness.ui.pictures.PicturesFragment;
import com.nkechinnaji.foreverfitness.ui.settings.SettingsFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        HomeFragment homeFragment = new HomeFragment();
        mFragmentTransaction.replace(R.id.nav_host_fragment, homeFragment);
        mFragmentTransaction.commit();

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.navigation_home:
                    switchToHomeFragment();
                    break;
                case R.id.navigation_daily_entries:
                    switchToDailyEntriesFragment();
                    break;
                case R.id.navigation_pictures:
                    switchToPicturesFragment();
                    break;
                case R.id.navigation_history:
                    switchToHistoryFragment();
                    break;
                case R.id.navigation_settings:
                    switchToSettingsFragment();
                    break;
            }
            return true;
        }

    };

    public void switchToHomeFragment() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.nav_host_fragment, new HomeFragment()).commit();
    }

    public void switchToDailyEntriesFragment() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.nav_host_fragment, new EntriesFragment()).commit();
    }

    public void switchToPicturesFragment() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.nav_host_fragment, new PicturesFragment()).commit();
    }

    public void switchToHistoryFragment() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.nav_host_fragment, new HistoryFragment()).commit();
    }

    public void switchToSettingsFragment() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.nav_host_fragment, new SettingsFragment()).commit();
    }

}
