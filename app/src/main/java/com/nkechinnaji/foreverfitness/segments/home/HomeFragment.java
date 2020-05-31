package com.nkechinnaji.foreverfitness.segments.home;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.nkechinnaji.foreverfitness.segments.UiUtils.Constants;
import com.nkechinnaji.foreverfitness.R;
import com.nkechinnaji.foreverfitness.segments.entries.EntriesActivity;
import com.nkechinnaji.foreverfitness.segments.model.ProfileModel;
import com.nkechinnaji.foreverfitness.segments.model.WeightEntryModel;
import com.nkechinnaji.foreverfitness.segments.storage.DatabaseHelper;
import com.squareup.picasso.Picasso;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment {

    private ImageView profileImage;

    TextView weightPosted, datePosted;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_home, container, false);
        profileImage = root.findViewById(R.id.toolbar_profile_iv);
        final LinearLayoutCompat weightEntryFile = root.findViewById(R.id.weight_entry_tile);


        //Click event: Weight Entry Tile
        weightEntryFile.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EntriesActivity.class);
                startActivityForResult(intent, Constants.RequestCodes.WEIGHT_ENTRY_REQ_CODE);
            }
        });


        //Profile Picture
        if (ContextCompat.checkSelfPermission(Objects.requireNonNull(getContext()), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Objects.requireNonNull(getActivity()), new String[]{
                    Manifest.permission.CAMERA}, Constants.RequestCodes.CAMERA_REQ_CODE);


            profileImage.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, Constants.RequestCodes.CAMERA_REQ_CODE);
                }
            });
        }

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        weightPosted = view.findViewById(R.id.text_current_weight);
        datePosted = view.findViewById(R.id.posted_date);
    }

    @Override
    public void onResume() {
        super.onResume();
        DatabaseHelper mDatabaseHelper = new DatabaseHelper(getContext());
        List<WeightEntryModel> profile = mDatabaseHelper.getWeightEntry();
        if(profile.size() > 0) {
            weightPosted.setText(profile.get(0).getWeight());
            datePosted.setText(profile.get(0).getDate());

        } else {
            ProfileModel model = mDatabaseHelper.getData();
            weightPosted.setText(model.getCurrentWeight());
            datePosted.setText(model.getEntryDate());
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.RequestCodes.CAMERA_REQ_CODE) {
            Bitmap captureImage = (Bitmap) data.getExtras().get("data");
            Picasso.get().load(String.valueOf(captureImage)).into(profileImage);
            profileImage.setImageBitmap(captureImage);

        }

        if (requestCode == Constants.RequestCodes.WEIGHT_ENTRY_REQ_CODE) {

            //populate home screen with database content
            DatabaseHelper mDatabaseHelper = new DatabaseHelper(getContext());
            ProfileModel profile = mDatabaseHelper.getData();
            weightPosted.setText(profile.getCurrentWeight());
            datePosted.setText(profile.getEntryDate());

        }

    }


}