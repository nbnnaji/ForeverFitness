package com.nkechinnaji.foreverfitness.segments.UiUtils;

import android.widget.EditText;

/**
 * Created by Nkechi Nnaji on 5/31/20.
 * Description:
 */
public class Utils {

    //EditText validation
    public boolean validate(EditText[] fields) {
        for (int i = 0; i < fields.length; i++) {
            EditText currentField = fields[i];
            if (currentField.getText().toString().length() <= 0) {
                return false;
            }
        }
        return true;
    }
}
