package com.nkechinnaji.foreverfitness.ui.entries;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.nkechinnaji.foreverfitness.MainActivity;
import com.nkechinnaji.foreverfitness.R;
import com.nkechinnaji.foreverfitness.storage.LocalStorage;
import com.nkechinnaji.foreverfitness.ui.home.HomeFragment;

import java.util.List;

public class EntriesFragment extends AppCompatActivity {

    private EntriesViewModel mEntriesViewModel;
    FloatingActionButton mFloatingActionButton;

    public EntriesFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_entries);
        populateView();
        //mEntriesViewModel = ViewModelProviders.of(requireActivity()).get(EntriesViewModel.class);
    }

  /*  @Override
    public View onCreateView(@NonNull LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_entries, container, false);

        return view;
    }*/


    public void populateView() {

        final TextInputEditText weightEditText = findViewById(R.id.text_weight);
        final TextInputEditText dateEditText = findViewById(R.id.text_date);
        weightEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        dateEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mEntriesViewModel.setDate(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });
        mFloatingActionButton = findViewById(R.id.fab_add);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Store in localSrorage
                List<String> weightList = LocalStorage.getInstance(EntriesFragment.this).getWeightList();
                weightList.add(0, weightEditText.getText().toString());
                LocalStorage.getInstance(EntriesFragment.this).storeWeight(weightList);
                onBackPressed();
            }
        });
    }

}
