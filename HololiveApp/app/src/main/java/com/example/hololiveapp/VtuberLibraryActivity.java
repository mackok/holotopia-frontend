package com.example.hololiveapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class VtuberLibraryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_vtuber_library);
    }


    public void goToGuraBio(View view){

        startActivity(new Intent(this,GuraBioActivity.class));
    }

    public void goToMoriBio(View view){

        startActivity(new Intent(this,MoriBioActivity.class));
    }
    public void goToNinoBio(View view){

        startActivity(new Intent(this,NinoBioActivity.class));
    }
    public void goToTakaBio(View view){

        startActivity(new Intent(this,TakaBioActivity.class));
    }
    public void goToWatsonBio(View view){

        startActivity(new Intent(this,WatsonBioActivity.class));
    }
}