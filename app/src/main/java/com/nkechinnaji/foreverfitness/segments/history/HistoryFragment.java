package com.nkechinnaji.foreverfitness.segments.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.nkechinnaji.foreverfitness.R;
import com.nkechinnaji.foreverfitness.storage.LocalStorage;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Nkechi Nnaji on 3/28/20.
 * Description:
 */
public class HistoryFragment extends Fragment {

    private List<String> weightList = new ArrayList<>();
    private List<String> dateList = new ArrayList<>();

    View tableRow;
    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_history, container, false);
        final TableLayout tableLayout = root.findViewById(R.id.history_table);

       // get weight list from local storage
        weightList = LocalStorage.getInstance(getContext()).getWeightList();
        dateList = LocalStorage.getInstance(getContext()).getDateList();

        for(int i = 0; i < dateList.size() ; i ++) {

            tableRow = inflater.inflate(R.layout.table_row_histroy_items, tableLayout, false);

            final TextView date = tableRow.findViewById(R.id.tv_date);
            date.setText(dateList.get(i));

            final TextView weight = tableRow.findViewById(R.id.tv_weight);
            weight.setText(weightList.get(i));

            tableLayout.addView(tableRow);
        }

        return root;
    }

}

