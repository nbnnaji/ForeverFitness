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

import com.nkechinnaji.foreverfitness.Constants;
import com.nkechinnaji.foreverfitness.R;
import com.nkechinnaji.foreverfitness.segments.pictures.TakePictureActivity;
import com.nkechinnaji.foreverfitness.storage.LocalStorage;
import com.nkechinnaji.foreverfitness.segments.entries.EntriesFragment;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment {

    private ImageView profileImage, latestPic;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_home, container, false);
        profileImage = root.findViewById(R.id.toolbar_profile_iv);
        latestPic = root.findViewById(R.id.todays_pic);
        final LinearLayoutCompat weightEntryFile = root.findViewById(R.id.weight_entry_tile);
        final LinearLayoutCompat pictureEntryTile = root.findViewById(R.id.picture_entry_tile);

        //Click event: Weight Entry Tile
        weightEntryFile.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), EntriesFragment.class));
            }
        });


        //Click event: Setup Tile
        pictureEntryTile.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TakePictureActivity.class);
                startActivityForResult(intent, Constants.RequestCodes.TAKE_PIC_REQ_CODE);
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

        //Latest Picture

        if (ContextCompat.checkSelfPermission(Objects.requireNonNull(getContext()), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Objects.requireNonNull(getActivity()), new String[]{
                    Manifest.permission.CAMERA}, Constants.RequestCodes.TAKE_PIC_REQ_CODE);


            latestPic.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, Constants.RequestCodes.TAKE_PIC_REQ_CODE);

                }
            });
        }


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView weightPosted = view.findViewById(R.id.text_current_weight);
        TextView datePosted = view.findViewById(R.id.posted_date);

        // get latest weight from local storage
        List<String> weightList = LocalStorage.getInstance(getContext()).getWeightList();
        List<String> dateList = LocalStorage.getInstance(getContext()).getDateList();
        if (weightList.size() > 0) {
            weightPosted.setText(weightList.get(0));
        }

        if (dateList.size() > 0) {
            datePosted.setText(dateList.get(0));
        }


    }

    Bitmap captureImage;

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.RequestCodes.CAMERA_REQ_CODE) {
            Bitmap captureImage = (Bitmap) data.getExtras().get("data");
            Picasso.get().load(String.valueOf(captureImage)).into(profileImage);
            profileImage.setImageBitmap(captureImage);

        }

        if (requestCode == Constants.RequestCodes.TAKE_PIC_REQ_CODE) {
            Bitmap latestPictureTaken = (Bitmap) data.getExtras().get("data");
            Picasso.get().load(String.valueOf(latestPictureTaken)).into(latestPic);
            latestPic.setImageBitmap(latestPictureTaken);
        }

    }

}