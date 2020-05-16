package com.nkechinnaji.foreverfitness.segments.profile.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.snackbar.Snackbar;
import com.nkechinnaji.foreverfitness.MainActivity;
import com.nkechinnaji.foreverfitness.R;
import com.nkechinnaji.foreverfitness.segments.profile.model.ProfileModel;
import com.nkechinnaji.foreverfitness.storage.DatabaseHelper;
import com.nkechinnaji.foreverfitness.storage.LocalStorage;

public class ProfileActivity extends AppCompatActivity {

    Button createProfileBtn;
    ProfileModel mProfileModel = new ProfileModel();


    String username;
    String dateOfBirth;
    String gender;
    String currentWeight;
    String targetWeight;
    String height;
    String email;

    EditText name;
    EditText dateOfBirthEntry;
    EditText genderEntry;
    EditText currentWeightEntry;
    EditText targetWeightEntry;
    EditText heightEntry;
    EditText emailEntry;

    DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        createProfileBtn = findViewById(R.id.create_profile_button);
        name = findViewById(R.id.username);
        dateOfBirthEntry = findViewById(R.id.date_of_birth);
        genderEntry = findViewById(R.id.sex);
        currentWeightEntry = findViewById(R.id.current_weight);
        targetWeightEntry = findViewById(R.id.target_weight);
        heightEntry = findViewById(R.id.height);
        emailEntry = findViewById(R.id.email);

        mProfileModel = new ProfileModel(username, dateOfBirth, gender, currentWeight, targetWeight, height, email);


//        createProfileBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                //capture text input
//                mProfileModel.setUsername(name.getText().toString());
//                mProfileModel.setDateOfBirth(dateOfBirthEntry.getText().toString());
//                mProfileModel.setGender(genderEntry.getText().toString());
//                mProfileModel.setCurrentWeight(currentWeightEntry.getText().toString());
//                mProfileModel.setTargetGoalWeight(targetWeightEntry.getText().toString());
//                mProfileModel.setHeight(heightEntry.getText().toString());
//                mProfileModel.setEmail(emailEntry.getText().toString());
//
//                //Storage using shared preferences
//                LocalStorage.storeProfileModel(ProfileActivity.this, "PROFILE_MODEL", mProfileModel);
//
//                //open main activity
//                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
//                startActivity(intent);
//
//                finish();
//
//
//            }
//        });


//        createProfileBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                //capture text input
//                mProfileModel.setUsername(name.getText().toString());
//                mProfileModel.setDateOfBirth(dateOfBirthEntry.getText().toString());
//                mProfileModel.setGender(genderEntry.getText().toString());
//                mProfileModel.setCurrentWeight(currentWeightEntry.getText().toString());
//                mProfileModel.setTargetGoalWeight(targetWeightEntry.getText().toString());
//                mProfileModel.setHeight(heightEntry.getText().toString());
//                mProfileModel.setEmail(emailEntry.getText().toString());
//
//                //Storage using SQLite Database
//                mDatabaseHelper.addData(mProfileModel);
//                //open main activity
//                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
//                startActivity(intent);
//
//                finish();
//
//
//            }
//        });
    }

    public void addNewProfileContent(View view) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);

        ProfileModel profile = new ProfileModel(name.getText().toString(), dateOfBirthEntry.getText().toString(), genderEntry.getText().toString(), currentWeightEntry.getText().toString(),
                targetWeightEntry.getText().toString(), heightEntry.getText().toString(), emailEntry.getText().toString());

        dbHelper.addData(profile);
        name.setText("");
        dateOfBirthEntry.setText("");
        genderEntry.setText("");
        currentWeightEntry.setText("");
        targetWeightEntry.setText("");
        heightEntry.setText("");
        emailEntry.setText("");

        //open main activity
        Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
