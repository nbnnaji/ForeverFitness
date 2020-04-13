package com.nkechinnaji.foreverfitness.ui.entries;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EntriesViewModel extends ViewModel {

    private MutableLiveData<String> mWeightText = new MutableLiveData<>();
    private MutableLiveData<String> mDateText = new MutableLiveData<>();

    public void setWeight(String weight) {
        mWeightText.setValue(weight);
    }

    public void setDate(String date) {
        mDateText.setValue(date);
    }
    public LiveData<String> getWeightText() {
        return mWeightText;
    }
    public LiveData<String> getDateText() {
        return mDateText;
    }
}