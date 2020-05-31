package com.nkechinnaji.foreverfitness.segments.profile;

import androidx.appcompat.app.AppCompatActivity;

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
import com.nkechinnaji.foreverfitness.segments.entries.EntriesActivity;
import com.nkechinnaji.foreverfitness.segments.model.ProfileModel;
import com.nkechinnaji.foreverfitness.segments.storage.DatabaseHelper;

import java.util.Calendar;

public class ProfileActivity extends AppCompatActivity {

    Button createProfileBtn;
    ProfileModel mProfileModel = new ProfileModel();
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

    final Calendar cal = Calendar.getInstance();
    int day = cal.get(Calendar.DAY_OF_MONTH);
    int month = cal.get(Calendar.MONTH);
    int year = cal.get(Calendar.YEAR);

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
        dateEntry = findViewById(R.id.entry_date);
        mProfileModel = new ProfileModel(username, dateOfBirth, gender, currentWeight, targetWeight, height, email, entryDate);

    }

    public void chooseBirthday(View view) {
        //Get date picker dialog
        picker = new DatePickerDialog(ProfileActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dateOfBirthEntry.setText(new StringBuilder().append(month + 1).append(getString(R.string.date_divider)).append(dayOfMonth).append(getString(R.string.date_divider)).append(year).toString());
            }
        }, year, month, day);
        picker.show();
    }

    public void chooseEntryDate(View view) {

        //Get date picker dialog
        picker = new DatePickerDialog(ProfileActivity.this, new DatePickerDialog.OnDateSetListener() {
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

    public void addNewProfileContent(View view) {

        Utils utils = new Utils();
        boolean fieldIsNotEmpty = utils.validate(new EditText[] { name, dateOfBirthEntry, genderEntry, currentWeightEntry,targetWeightEntry, heightEntry, emailEntry, dateEntry });

        if(!fieldIsNotEmpty){

            Toast.makeText(this,"Please fill in the empty fields.", Toast.LENGTH_LONG).show();

        }else {
            DatabaseHelper dbHelper = new DatabaseHelper(this);

            ProfileModel profile = new ProfileModel(name.getText().toString(), dateOfBirthEntry.getText().toString(), genderEntry.getText().toString(), currentWeightEntry.getText().toString(),
                    targetWeightEntry.getText().toString(), heightEntry.getText().toString(), emailEntry.getText().toString(), dateEntry.getText().toString());

            dbHelper.addData(profile);
            name.setText("");
            dateOfBirthEntry.setText("");
            genderEntry.setText("");
            currentWeightEntry.setText("");
            targetWeightEntry.setText("");
            heightEntry.setText("");
            emailEntry.setText("");
            dateEntry.setText("");
            //open main activity
            Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

}
