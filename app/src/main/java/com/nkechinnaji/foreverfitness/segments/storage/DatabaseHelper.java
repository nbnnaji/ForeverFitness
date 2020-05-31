package com.nkechinnaji.foreverfitness.segments.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.nkechinnaji.foreverfitness.segments.UiUtils.Constants;
import com.nkechinnaji.foreverfitness.segments.model.ProfileModel;
import com.nkechinnaji.foreverfitness.segments.model.WeightEntryModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Nkechi Nnaji on 5/9/20.
 * Description: Storage using Database SQLite. Currently being used for all data storage in the app.
 * Replaces usage of LocalStorage class which is a SharedPreferences class.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, Constants.PROFILE_TABLE, null, 2);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        //Profile Table (Used to populate SettingsFragment screen and HomeFragment screen(date & weight))
        String createProfileTable = "CREATE TABLE " + "PROFILE" +
                " ( " + "ID" + "INTEGER PRIMARY KEY , " +
                "NAME text, DOB text, SEX text, CURRENT_WEIGHT text, TARGET_WEIGHT text, HEIGHT text, EMAIL_ADDRESS text, DATE text);";
        db.execSQL(createProfileTable);

        //Weight Entry table (Populating HomeFragment screen (weight & date) and HostoryFragment screen (date, weight & picture))
        String createEntryTable = "CREATE TABLE " + Constants.ENTRY_TABLE +
                " ( " + "ID" + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "DATE text, WEIGHT text, increase  boolean,  PICTURE text);";

        db.execSQL(createEntryTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Constants.PROFILE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.ENTRY_TABLE);
        onCreate(db);
    }

    //WeightEntry table content update
    public void addWeightEntry(WeightEntryModel model) {
        SQLiteDatabase db = this .getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.WEIGHT, model.getWeight());
        contentValues.put(Constants.DATE, model.getDate());
        contentValues.put(Constants.IMAGE, model.getImageUrl());
        db.insert(Constants.ENTRY_TABLE, null, contentValues);
        db.close();
    }

    //WeightEntry table content entry
    public List<WeightEntryModel>  getWeightEntry() {
        String query = "Select * FROM " + Constants.ENTRY_TABLE + " ORDER BY ID DESC";
        List<WeightEntryModel> weightEntries = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            String weight = cursor.getString(cursor.getColumnIndex(Constants.WEIGHT));
            String date = cursor.getString(cursor.getColumnIndex(Constants.DATE));
            String imageUrl = cursor.getString(cursor.getColumnIndex(Constants.IMAGE));
            WeightEntryModel model = new WeightEntryModel(date, weight,imageUrl);


            weightEntries.add(model);
        }
        cursor.close();
        db.close();
        return weightEntries;
    }


    //Profile table content update
    public void addData(ProfileModel item){
        SQLiteDatabase db = this .getWritableDatabase();
        db.delete(Constants.PROFILE_TABLE, null, null);
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.NAME, item.getUsername());
        contentValues.put(Constants.DOB, item.getDateOfBirth());
        contentValues.put(Constants.SEX, item.getGender());
        contentValues.put(Constants.CURRENT_WEIGHT, item.getCurrentWeight());
        contentValues.put(Constants.TARGET_WEIGHT, item.getTargetGoalWeight());
        contentValues.put(Constants.HEIGHT, item.getHeight());
        contentValues.put(Constants.EMAIL_ADDRESS, item.getEmail());
        contentValues.put(Constants.DATE, item.getEntryDate());
        Log.d(Constants.DATABASE_TAG, "addData: Adding " + item + "to " + Constants.PROFILE_TABLE);
        db.insert(Constants.PROFILE_TABLE, null, contentValues);
        db.close();

    }


    //Profile table content entry
    public ProfileModel getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + Constants.PROFILE_TABLE;
        Cursor data = db.rawQuery(query, null);
        data.moveToFirst();
        ProfileModel model = new ProfileModel();
        model.setUsername(data.getString(data.getColumnIndex(Constants.NAME)));
        model.setEmail(data.getString(data.getColumnIndex(Constants.EMAIL_ADDRESS)));
        model.setHeight(data.getString(data.getColumnIndex(Constants.HEIGHT)));
        model.setTargetGoalWeight(data.getString(data.getColumnIndex(Constants.TARGET_WEIGHT)));
        model.setCurrentWeight(data.getString(data.getColumnIndex(Constants.CURRENT_WEIGHT)));
        model.setGender(data.getString(data.getColumnIndex(Constants.SEX)));
        model.setDateOfBirth(data.getString(data.getColumnIndex(Constants.DOB)));
        model.setEntryDate(data.getString(data.getColumnIndex(Constants.DATE)));
        return model;
    }

}
