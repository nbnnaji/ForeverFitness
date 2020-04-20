package com.nkechinnaji.foreverfitness.segments.entries;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.nkechinnaji.foreverfitness.R;
import com.nkechinnaji.foreverfitness.storage.LocalStorage;
import java.util.Calendar;
import java.util.List;

public class EntriesFragment extends AppCompatActivity {


    FloatingActionButton mFloatingActionButton;
    DatePickerDialog picker;

    public EntriesFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_entries);
        populateView();

    }


    public void populateView() {

        final TextInputEditText weightEditText = findViewById(R.id.text_weight);
        final TextView dateTextView = findViewById(R.id.text_date);

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


        dateTextView.addTextChangedListener(new TextWatcher() {
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


        //Get date from calendar
        dateTextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final Calendar cal = Calendar.getInstance();
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int month = cal.get(Calendar.MONTH);
                int year = cal.get(Calendar.YEAR);

                //get date picker dialog

                picker = new DatePickerDialog(EntriesFragment.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dateTextView.setText((month + 1) + "/" + dayOfMonth + "/" + year);
                    }
                }, year, month, day);

                //Disable past dates
                int pastDates = 1000;
                picker.getDatePicker().setMinDate(System.currentTimeMillis() - pastDates);
                picker.show();
            }
        });


        mFloatingActionButton = findViewById(R.id.fab_add);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Store in local storage
                List<String> weightList = LocalStorage.getInstance(EntriesFragment.this).getWeightList();
                List<String> dateList = LocalStorage.getInstance(EntriesFragment.this).getDateList();

                weightList.add(0, weightEditText.getText().toString());
                dateList.add(0, dateTextView.getText().toString());

                LocalStorage.getInstance(EntriesFragment.this).storeWeight(weightList);
               LocalStorage.getInstance(EntriesFragment.this).storeDate(dateList);

                onBackPressed();
            }
        });
    }

}
