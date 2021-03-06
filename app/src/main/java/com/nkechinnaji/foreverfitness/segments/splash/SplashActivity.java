package com.nkechinnaji.foreverfitness.segments.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.nkechinnaji.foreverfitness.R;
import com.nkechinnaji.foreverfitness.segments.onboarding.OnBoardingActivity;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 7000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Splash screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(SplashActivity.this, OnBoardingActivity.class);
                startActivity(homeIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);

    }
}
