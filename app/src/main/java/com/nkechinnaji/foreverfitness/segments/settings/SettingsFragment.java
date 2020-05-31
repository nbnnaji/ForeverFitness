package com.nkechinnaji.foreverfitness.segments.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.nkechinnaji.foreverfitness.R;
import com.nkechinnaji.foreverfitness.segments.model.ProfileModel;
import com.nkechinnaji.foreverfitness.segments.storage.DatabaseHelper;

/**
 * Created by Nkechi Nnaji on 3/28/20.
 * Description:
 */
public class SettingsFragment extends Fragment {

TextView username, dateOfBirth, sex, currentWeight,targetWeight, height, email , enteredDate;
    AppCompatButton createProfileBtn;
    DatabaseHelper mDatabaseHelper;

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
        enteredDate = view.findViewById(R.id.entered_date);
        createProfileBtn = view. findViewById(R.id.create_profile_button);

        populateSettingsScreen();

        createProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                startActivityForResult(intent, 10);
            }
        });


    }

    //Populate user profile from content in database
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
        enteredDate.setText(data.getEntryDate());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10) {

            //populate settings screen with database content
            DatabaseHelper mDatabaseHelper = new DatabaseHelper(getContext());
            ProfileModel profile =  mDatabaseHelper.getData();

            username.setText(profile.getUsername());
            dateOfBirth.setText(profile.getDateOfBirth());
            sex.setText(profile.getGender());
            currentWeight.setText(profile.getCurrentWeight());
            targetWeight.setText(profile.getTargetGoalWeight());
            height.setText(profile.getHeight());
            email.setText(profile.getEmail());
            enteredDate.setText(profile.getEntryDate());
        }
    }
}

