package com.nkechinnaji.foreverfitness.segments.pictures;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.nkechinnaji.foreverfitness.Constants;
import com.nkechinnaji.foreverfitness.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;

public class PicturesFragment extends Fragment {

    Button cameraBtn;
    ImageView image;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_pictures, container, false);
        cameraBtn = root.findViewById(R.id.open_camera_btn);
        image = root.findViewById(R.id.captured_image);

        if (ContextCompat.checkSelfPermission(Objects.requireNonNull(getContext()), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Objects.requireNonNull(getActivity()), new String[]{
                    Manifest.permission.CAMERA}, Constants.RequestCodes.CAMERA_REQ_CODE);


            cameraBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, Constants.RequestCodes.CAMERA_REQ_CODE);

                }
            });
        }


        return root;
    }

    Bitmap captureImage;

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.RequestCodes.CAMERA_REQ_CODE) {
            captureImage = (Bitmap) data.getExtras().get("data");
            image.setImageBitmap(captureImage);
        }
    }
}



