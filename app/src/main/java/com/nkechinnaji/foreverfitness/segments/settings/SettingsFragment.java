package com.nkechinnaji.foreverfitness.segments.settings;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.nkechinnaji.foreverfitness.R;
import com.nkechinnaji.foreverfitness.segments.profile.model.ProfileModel;
import com.nkechinnaji.foreverfitness.storage.DatabaseHelper;
import com.nkechinnaji.foreverfitness.storage.LocalStorage;

import java.util.ArrayList;

/**
 * Created by Nkechi Nnaji on 3/28/20.
 * Description:
 */
public class SettingsFragment extends Fragment {

    private EditText username;
    private EditText dateOfBirth;
    private EditText sex;
    private EditText currentWeight;
    private EditText targetWeight;
    private EditText height;
    private EditText email;

    DatabaseHelper mDatabaseHelper;
    private ListView mListView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        username = view.findViewById(R.id.username);
        dateOfBirth = view.findViewById(R.id.date_of_birth);
        sex = view.findViewById(R.id.sex);
        currentWeight = view.findViewById(R.id.current_weight);
        targetWeight = view.findViewById(R.id.target_weight);
        height = view.findViewById(R.id.height);
        email = view.findViewById(R.id.email);

       // Getting data from SharedPreferences
       // ProfileModel data = (ProfileModel) LocalStorage.getProfileModel(getContext(), "PROFILE_MODEL");
        
        populateSettingsScreen();

    }

    private void populateSettingsScreen() {
        mDatabaseHelper = new DatabaseHelper(getContext());
        ProfileModel data = mDatabaseHelper.getData();
        username.setText(data.getUsername());
        dateOfBirth.setText(data.getDateOfBirth());
        sex.setText(data.getGender());
        currentWeight.setText(data.getCurrentWeight());
        targetWeight.setText(data.getTargetGoalWeight());
        height.setText(data.getHeight());
        email.setText(data.getEmail());
    }
}