package com.nkechinnaji.foreverfitness.segments.home;

import android.content.Intent;
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
import com.nkechinnaji.foreverfitness.R;
import com.nkechinnaji.foreverfitness.storage.LocalStorage;
import com.nkechinnaji.foreverfitness.segments.entries.EntriesFragment;
import com.nkechinnaji.foreverfitness.segments.entries.EntriesViewModel;

import java.util.List;

public class HomeFragment extends Fragment {


    private TextView weightPosted;
    private TextView datePosted;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_home, container, false);
        final LinearLayoutCompat weightEntryFile = root.findViewById(R.id.weight_entry_tile);
        final LinearLayoutCompat pictureEntryTile = root.findViewById(R.id.picture_entry_tile);

        //Click event: Weight Entry Tile
        weightEntryFile.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( getActivity(), EntriesFragment.class));
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

      // get latest weight from local storage
        List<String> weightList = LocalStorage.getInstance(getContext()).getWeightList();
        List<String> dateList = LocalStorage.getInstance(getContext()).getDateList();
        if(weightList.size() > 0) {
            weightPosted.setText(weightList.get(0));
        }

        if(dateList.size() > 0){
            datePosted.setText(dateList.get(0));
        }


    }




}