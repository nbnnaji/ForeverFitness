package com.nkechinnaji.foreverfitness.storage;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Nkechi Nnaji on 4/18/20.
 * Description:
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
