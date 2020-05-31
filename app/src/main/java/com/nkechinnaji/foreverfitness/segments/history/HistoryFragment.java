package com.nkechinnaji.foreverfitness.segments.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.nkechinnaji.foreverfitness.R;
import com.nkechinnaji.foreverfitness.segments.model.WeightEntryModel;
import com.nkechinnaji.foreverfitness.segments.storage.DatabaseHelper;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Nkechi Nnaji on 3/28/20.
 * Description:
 */
public class HistoryFragment extends Fragment {

    View tableRow;
    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_history, container, false);
        final TableLayout tableLayout = root.findViewById(R.id.history_table);

        //populate home screen with database content
        DatabaseHelper mDatabaseHelper = new DatabaseHelper(getContext());
        List<WeightEntryModel> data = mDatabaseHelper.getWeightEntry();

        for(int i = 0; i < data.size() ; i ++) {

            tableRow = inflater.inflate(R.layout.table_row_histroy_items, tableLayout, false);

            final TextView date = tableRow.findViewById(R.id.tv_date);
            date.setText(data.get(i).getDate());

            final TextView weight = tableRow.findViewById(R.id.tv_weight);
            weight.setText(data.get(i).getWeight());

            final ImageView weightPic = tableRow.findViewById(R.id.iv_weight);

            String imageUrl = data.get(i).getImageUrl();
            if(imageUrl.isEmpty()) {
                weightPic.setVisibility(View.INVISIBLE);
            } else {
                File file = new File(imageUrl);
                Picasso.get().load(file).into(weightPic);
            }

            tableLayout.addView(tableRow);
        }

        return root;
    }

}

