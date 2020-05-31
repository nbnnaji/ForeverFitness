package com.nkechinnaji.foreverfitness.segments.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.nkechinnaji.foreverfitness.MainActivity;
import com.nkechinnaji.foreverfitness.R;
import com.nkechinnaji.foreverfitness.segments.UiUtils.Utils;
import com.nkechinnaji.foreverfitness.segments.model.ProfileModel;
import com.nkechinnaji.foreverfitness.segments.profile.ProfileActivity;
import com.nkechinnaji.foreverfitness.segments.storage.DatabaseHelper;

import java.util.Calendar;

public class EditProfileActivity extends AppCompatActivity {

    Button saveProfileBtn;
    Button cancelProfileBtn;
    ProfileModel mProfileModel = new ProfileModel();

    final Calendar cal = Calendar.getInstance();
    int day = cal.get(Calendar.DAY_OF_MONTH);
    int month = cal.get(Calendar.MONTH);
    int year = cal.get(Calendar.YEAR);

    DatePickerDialog picker;

    String username;
    String dateOfBirth;
    String gender;
    String currentWeight;
    String targetWeight;
    String height;
    String email;
    String entryDate;

    EditText name;
    EditText dateOfBirthEntry;
    EditText genderEntry;
    EditText currentWeightEntry;
    EditText targetWeightEntry;
    EditText heightEntry;
    EditText emailEntry;
    EditText dateEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        saveProfileBtn = findViewById(R.id.edit_save_button);
        cancelProfileBtn = findViewById(R.id.edit_cancel_button);

        name = findViewById(R.id.edit_username);
        dateOfBirthEntry = findViewById(R.id.edit_date_of_birth);
        genderEntry = findViewById(R.id.edit_sex);
        currentWeightEntry = findViewById(R.id.edit_current_weight);
        targetWeightEntry = findViewById(R.id.edit_target_weight);
        heightEntry = findViewById(R.id.edit_height);
        emailEntry = findViewById(R.id.edit_email);
        dateEntry = findViewById(R.id.edit_entry_date);
        mProfileModel = new ProfileModel(username, dateOfBirth, gender, currentWeight, targetWeight, height, email, entryDate);

        populateSettingsScreen();
    }

    public void editBirthday(View view) {

        //Get date picker dialog
        picker = new DatePickerDialog(EditProfileActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dateOfBirthEntry.setText(new StringBuilder().append(month + 1).append(getString(R.string.date_divider)).append(dayOfMonth).append(getString(R.string.date_divider)).append(year).toString());
            }
        }, year, month, day);
        picker.show();
    }


    public void editEntryDate(View view) {

        //Get date picker dialog
        picker = new DatePickerDialog(EditProfileActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dateEntry.setText(new StringBuilder().append(month + 1).append(getString(R.string.date_divider)).append(dayOfMonth).append(getString(R.string.date_divider)).append(year).toString());
            }
        }, year, month, day);

        //Disable past dates
        int pastDates = 1000;
        picker.getDatePicker().setMinDate(System.currentTimeMillis() - pastDates);
        picker.show();
    }

    //Populate user edit screen with content in database
    private void populateSettingsScreen() {
        DatabaseHelper mDatabaseHelper = new DatabaseHelper(this);
        ProfileModel data = mDatabaseHelper.getData();
        name.setText(data.getUsername());
        dateOfBirthEntry.setText(data.getDateOfBirth());
        genderEntry.setText(data.getGender());
        currentWeightEntry.setText(data.getCurrentWeight());
        targetWeightEntry.setText(data.getTargetGoalWeight());
        heightEntry.setText(data.getHeight());
        emailEntry.setText(data.getEmail());
        dateEntry.setText(data.getEntryDate());
    }

    public void updateProfileContent(View view) {

        Utils utils = new Utils();
        boolean fieldIsNotEmpty = utils.validate(new EditText[] { name, dateOfBirthEntry, genderEntry, currentWeightEntry,targetWeightEntry, heightEntry, emailEntry, dateEntry });

        if(!fieldIsNotEmpty){

            Toast.makeText(this,"Please fill in the empty fields.", Toast.LENGTH_LONG).show();

        }else {
            DatabaseHelper dbHelper = new DatabaseHelper(this);
            ProfileModel profile = new ProfileModel(name.getText().toString(), dateOfBirthEntry.getText().toString(), genderEntry.getText().toString(), currentWeightEntry.getText().toString(),
                    targetWeightEntry.getText().toString(), heightEntry.getText().toString(), emailEntry.getText().toString(), dateEntry.getText().toString());

            dbHelper.addData(profile);
            setResult(Activity.RESULT_OK);
            finish();
        }
    }


    public void cancelProfileUpdate(View view) {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

}