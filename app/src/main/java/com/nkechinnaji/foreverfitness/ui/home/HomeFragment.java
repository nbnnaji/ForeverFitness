package com.nkechinnaji.foreverfitness.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
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
import com.nkechinnaji.foreverfitness.ui.entries.EntriesViewModel;

public class HomeFragment extends Fragment {

    private EntriesViewModel mEntriesViewModel;
    private TextView weightPosted;
    private TextView datePosted;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEntriesViewModel = ViewModelProviders.of(requireActivity()).get(EntriesViewModel.class);

    }

    public View onCreateView(@NonNull LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_home, container, false);
        final LinearLayoutCompat weightEntryFile = root.findViewById(R.id.weight_entry_tile);
        final LinearLayoutCompat pictureEntryTile = root.findViewById(R.id.picture_entry_tile);

        //Click event: Weight Entry Tile
        weightEntryFile.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                EntriesFragment entriesFragment = new EntriesFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, entriesFragment); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();

            }
        });


        //Click event: Setup Tile
        pictureEntryTile.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(), "goToPictureEntry", Toast.LENGTH_LONG).show();
            }
        });


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        weightPosted = view.findViewById(R.id.text_current_weight);
        datePosted = view.findViewById(R.id.posted_date);


        mEntriesViewModel.getWeightText().observe(requireActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                weightPosted.setText(s);
            }
        });

        mEntriesViewModel.getDateText().observe(requireActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                datePosted.setText(s);
            }
        });

    }
}