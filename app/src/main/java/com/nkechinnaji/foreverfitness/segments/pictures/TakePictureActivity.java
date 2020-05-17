package com.nkechinnaji.foreverfitness.segments.pictures;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.nkechinnaji.foreverfitness.Constants;
import com.nkechinnaji.foreverfitness.R;
import com.nkechinnaji.foreverfitness.segments.home.HomeFragment;

public class TakePictureActivity extends AppCompatActivity {
    ImageView imageview;
    Button cameraBtn;
    Bitmap captureImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_picture);

        imageview = findViewById(R.id.camera);
        cameraBtn = findViewById(R.id.open_camera_btn);

        //Get Camera Permission
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{
                 Manifest.permission.CAMERA
            }, Constants.RequestCodes.CAMERA_REQ_CODE);
        }

        cameraBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, Constants.RequestCodes.CAMERA_REQ_CODE);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == Constants.RequestCodes.CAMERA_REQ_CODE){
            captureImage = (Bitmap) data.getExtras().get("data");
            imageview.setImageBitmap(captureImage);

        }

      }
}
