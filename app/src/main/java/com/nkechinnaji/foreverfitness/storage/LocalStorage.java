package com.nkechinnaji.foreverfitness.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.nkechinnaji.foreverfitness.segments.profile.model.ProfileModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Nkechi Nnaji on 4/18/20.
 * Description: Storage with Shared Preferences
 */
public class LocalStorage {

    private SharedPreferences mSharedPreferences  = null;
    private  static LocalStorage sLocalStorage;

    private LocalStorage(Context context) {
        mSharedPreferences = context.getSharedPreferences("FOREEVER_FITNESS", Context.MODE_PRIVATE);
    }

    public synchronized static LocalStorage getInstance(Context context) {
        if(sLocalStorage == null) {
            sLocalStorage = new LocalStorage(context);
        }
        return sLocalStorage;
    }

    public void storeWeight(List<String> weightList) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        String weight = convertToString(weightList);
        editor.putString("WEIGHT_LIST", weight);
        editor.apply();
    }

    public List<String> getWeightList() {
        String weight = mSharedPreferences.getString("WEIGHT_LIST", null);
        return convertToArray(weight);
    }


    public void storeDate(List<String> dateList){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        String date = convertToString(dateList);
        editor.putString("DATE_LIST", date);
        editor.apply();
    }

    public List<String> getDateList() {
        String date = mSharedPreferences.getString("DATE_LIST", null);
        return convertToArray(date);
    }


    public static void storeProfileModel(Context context, String key, Object value){
        SharedPreferences sharedPreferences =  context.getSharedPreferences(
                context.getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(value);
        editor.putString("PROFILE_MODEL", json);
        editor.apply();
    }

    public static Object  getProfileModel(Context context, String key) {
        SharedPreferences sharedPreferences =  context.getSharedPreferences(
                context.getPackageName(), Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(key, "");
        ProfileModel objData = new Gson().fromJson(json, ProfileModel.class);
        return objData;
    }


    private String convertToString(List<String> list) {

        StringBuilder sb = new StringBuilder();
        String delim = "";
        for (String s : list)
        {
            sb.append(delim);
            sb.append(s);;
            delim = ",";
        }
        return sb.toString();
    }

    private ArrayList<String> convertToArray(String string) {
        if(string == null) {
            return new ArrayList<>();
        }

        ArrayList<String> list = new ArrayList<String>(Arrays.asList(string.split(",")));
        return list;
    }
}
