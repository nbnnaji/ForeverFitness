package com.nkechinnaji.foreverfitness.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.nkechinnaji.foreverfitness.Constants;
import com.nkechinnaji.foreverfitness.segments.profile.model.ProfileModel;


/**
 * Created by Nkechi Nnaji on 5/9/20.
 * Description:
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context, String name,
                          SQLiteDatabase.CursorFactory factory, int version) {
        super(context, Constants.PROFILE_TABLE, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        //Create Profile Table
        String createProfileTable = "CREATE TABLE " + "PROFILE" +
                " ( " + "ID" + "INTEGER PRIMARY KEY, " +
                "NAME text, DOB text, SEX text, CURRENT_WEIGHT text, TARGET_WEIGHT text, HEIGHT text, EMAIL_ADDRESS text);";
        db.execSQL(createProfileTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Constants.PROFILE_TABLE);
        onCreate(db);
    }

    //Method for adding data to database
    public void addData(ProfileModel item){
        SQLiteDatabase db = this .getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.COL1, item.getUsername());
        contentValues.put(Constants.COL2, item.getDateOfBirth());
        contentValues.put(Constants.COL3, item.getGender());
        contentValues.put(Constants.COL4, item.getCurrentWeight());
        contentValues.put(Constants.COL5, item.getTargetGoalWeight());
        contentValues.put(Constants.COL6, item.getHeight());
        contentValues.put(Constants.COL7, item.getEmail());

        Log.d(Constants.DATABASE_TAG, "addData: Adding " + item + "to " + Constants.PROFILE_TABLE);


        db.insert(Constants.PROFILE_TABLE, null, contentValues);
        db.close();

        //Check if data was entered properly
  //      long entryResult = db.insert(Constants.PROFILE_TABLE, null, contentValues);
//       if(entryResult == -1){
//           return false;
//       }
//       else{
//           return true;
//       }


    }

    //Method for handling query to Database
    public ProfileModel findProfile (String name) {
        String query = "Select * FROM " + Constants.PROFILE_TABLE + " WHERE " + Constants.COL2 + " =  \"" + name + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        ProfileModel profile = new ProfileModel();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            profile.setUsername(cursor.getString(1));
            profile.setDateOfBirth(cursor.getString(2));
            profile.setGender(cursor.getString(3));
            profile.setCurrentWeight(cursor.getString(4));
            profile.setTargetGoalWeight(cursor.getString(5));
            profile.setHeight(cursor.getString(6));
            profile.setEmail(cursor.getString(7));

            cursor.close();
        } else {
            profile = null;
        }
        db.close();
        return profile;
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + Constants.PROFILE_TABLE;
        Cursor data = db.rawQuery(query, null);
        return data;

    }


    //Method for deleting entries in DB

    public boolean deleteProduct(String name) {

        boolean result = false;

        String query = "Select * FROM " + Constants.PROFILE_TABLE + " WHERE " + Constants.COL2 + " =  \"" + name + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        ProfileModel profile = new ProfileModel();

        if (cursor.moveToFirst()) {
//            profile.setId(Integer.parseInt(cursor.getString(0)));
//            db.delete(Constants.PROFILE_TABLE, Constants.COL1 + " = ?",
//                    new String[] { String.valueOf(profile.getId()) });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }


}