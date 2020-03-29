package com.nkechinnaji.foreverfitness.ui.home;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.nkechinnaji.foreverfitness.R;
import com.nkechinnaji.foreverfitness.ui.entries.EntriesFragment;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final LinearLayoutCompat setupTile = root.findViewById(R.id.setup_tile);
        final LinearLayoutCompat weightEntryFile = root.findViewById(R.id.weight_entry_tile);
        final LinearLayoutCompat historyTile = root.findViewById(R.id.history_tile);
        final LinearLayoutCompat pictureEntryTile = root.findViewById(R.id.picture_entry_tile);

        //Click event: Setup Tile
        setupTile.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(), "goToSetup", Toast.LENGTH_LONG).show();
            }
        });


        //Click event: Weight Entry Tile
        weightEntryFile.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                EntriesFragment entriesFragment = new EntriesFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, entriesFragment, "ENTRIES_FRAGMENT")
                        //.addToBackStack(null)
                        .commit();

                Toast.makeText(getContext(), "goToWeightEntry", Toast.LENGTH_LONG).show();
            }
        });


        //Click event: History Tile
        historyTile.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(), "goToHistory", Toast.LENGTH_LONG).show();
            }
        });


        //Click event: Setup Tile
        pictureEntryTile.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(), "goToPictureEntry", Toast.LENGTH_LONG).show();
            }
        });


        //Click event: Setup Tile
        setupTile.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(), "goToSetup", Toast.LENGTH_LONG).show();
            }
        });


        return root;
    }
}
