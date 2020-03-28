package com.nkechinnaji.foreverfitness.ui.entries;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.nkechinnaji.foreverfitness.R;

public class EntriesFragment extends Fragment {

    private EntriesViewModel mEntriesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mEntriesViewModel =
                ViewModelProviders.of(this).get(EntriesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_daily_entries, container, false);
        final TextView textView = root.findViewById(R.id.text_daily_entries);
        mEntriesViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
