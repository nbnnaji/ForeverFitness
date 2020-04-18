package com.nkechinnaji.foreverfitness.ui.history;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.nkechinnaji.foreverfitness.R;
import com.nkechinnaji.foreverfitness.storage.LocalStorage;
import com.nkechinnaji.foreverfitness.ui.entries.EntriesViewModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * Created by Nkechi Nnaji on 3/28/20.
 * Description:
 */
public class HistoryFragment extends Fragment {

    private HistoryViewModel mHistoryViewModel;
    private EntriesViewModel mEntriesViewModel;
    private List<String> weightList = new ArrayList<>();

    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       // mHistoryViewModel = ViewModelProviders.of(this).get(HistoryViewModel.class);
       // mEntriesViewModel = ViewModelProviders.of(requireActivity()).get(EntriesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_history, container, false);
        final TableLayout tableLayout = root.findViewById(R.id.history_table);
//        final TextView textView = root.findViewById(R.id.text_history);
//        mHistoryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

       /* mEntriesViewModel.getWeightText().observe(requireActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
              // weightList.add(s);

            }
        });*/
       // get weight list from local storage
        weightList = LocalStorage.getInstance(getContext()).getWeightList();
        for(String s : weightList) {
            View tableRow = inflater.inflate(R.layout.table_row_histroy_items, tableLayout, false);
            TextView dateText = tableRow.findViewById(R.id.tv_date);
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            Calendar cal = Calendar.getInstance();
            Date date = cal.getTime();
            String todaysDate = dateFormat.format(date);
            dateText.setText(todaysDate);
            final TextView weight = tableRow.findViewById(R.id.tv_weight);
            weight.setText(s);
            ImageView entryIcon = (ImageView) tableRow.findViewById(R.id.iv_weight_icon);
            entryIcon.setBackgroundResource(R.drawable.ic_weight_loss_24dp);
            tableLayout.addView(tableRow);
        }
        return root;
    }

}

