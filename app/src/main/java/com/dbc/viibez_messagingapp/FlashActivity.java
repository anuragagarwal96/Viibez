package com.dbc.viibez_messagingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class FlashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent nextScreen = new Intent(FlashActivity.this,WelcomeScreen.class);
                startActivity(nextScreen);
                finish();

            }
        },2500);


        



    }
}