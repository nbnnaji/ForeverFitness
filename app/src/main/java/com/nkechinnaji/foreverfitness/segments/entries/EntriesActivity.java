package com.nkechinnaji.foreverfitness.segments.entries;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import com.nkechinnaji.foreverfitness.BuildConfig;
import com.nkechinnaji.foreverfitness.R;
import com.nkechinnaji.foreverfitness.segments.UiUtils.Constants;
import com.nkechinnaji.foreverfitness.segments.model.WeightEntryModel;
import com.nkechinnaji.foreverfitness.segments.storage.DatabaseHelper;
import com.squareup.picasso.Picasso;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class EntriesActivity extends AppCompatActivity {

    Button saveBtn;
    DatePickerDialog picker;
    TextView dateEntryTxt;
    TextView weightEntryTxt;
    Button takePictureBtn;
    ImageView imageTaken;
    private String imagePath ="";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_entries);
        weightEntryTxt = findViewById(R.id.text_weight);
        dateEntryTxt = findViewById(R.id.text_date);
        saveBtn = findViewById(R.id.save_entries_btn);
        takePictureBtn = findViewById(R.id.take_picture_btn);
        imageTaken = findViewById(R.id.entry_pic_iv);

        //Check camera permissions
        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }

    }

    //Select weight entry date
    public void SelectDate(View view) {
        final Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);

        //Get date picker dialog
        picker = new DatePickerDialog(EntriesActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dateEntryTxt.setText(new StringBuilder().append(month + 1).append(getString(R.string.date_divider)).append(dayOfMonth).append(getString(R.string.date_divider)).append(year).toString());
            }
        }, year, month, day);

        //Disable past dates
        int pastDates = 1000;
        picker.getDatePicker().setMinDate(System.currentTimeMillis() - pastDates);
        picker.show();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            }
        }
    }

    //Take picture
    public void takePictures(View view) {
        try {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            File file = createPictureFile();
            Uri photoURI = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + getString(R.string.provider),file);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivityForResult(intent, Constants.RequestCodes.TAKE_PIC_REQ_CODE);
        }
        catch (Exception e) {
            e.getMessage();

        }
    }

    //Create picture directory, port pic to imageview
    private File createPictureFile() throws IOException {
        String timeStamp = new SimpleDateFormat(getString(R.string.date_format)).format(new Date());
        String pictureName = getString(R.string.jpeg_format) + timeStamp + getString(R.string.under_score);
        String pictureSuffix = getString(R.string.jpeg_extension);
        File fileStorageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image;
        image = File.createTempFile(pictureName, pictureSuffix, fileStorageDirectory);
        imagePath = image.getAbsolutePath();
        return image;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent dataFile) {
        super.onActivityResult(requestCode, resultCode, dataFile);
        if (requestCode == Constants.RequestCodes.TAKE_PIC_REQ_CODE) {
            if (resultCode == RESULT_OK) {
                File file = new File(imagePath);
                Picasso.get().load(file).into(imageTaken);

            }
        }
    }

    //Send data to database & close activity
    public void saveAllEntries(View view) {
        DatabaseHelper mDatabaseHelper = new DatabaseHelper(getApplicationContext());
        WeightEntryModel model = new WeightEntryModel(dateEntryTxt.getText().toString(), weightEntryTxt.getText().toString(), imagePath);
        mDatabaseHelper.addWeightEntry(model);
        setResult(Activity.RESULT_OK);
        finish();

    }
}

