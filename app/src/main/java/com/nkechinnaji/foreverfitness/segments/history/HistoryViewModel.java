package com.nkechinnaji.foreverfitness.segments.history;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Created by Nkechi Nnaji on 3/28/20.
 * Description:
 */
public class HistoryViewModel extends ViewModel {

        private MutableLiveData<String> mText;

    public HistoryViewModel() {
            mText = new MutableLiveData<>();
            mText.setValue("This is history fragment");
        }

        public LiveData<String> getText() {
            return mText;
        }
    }

