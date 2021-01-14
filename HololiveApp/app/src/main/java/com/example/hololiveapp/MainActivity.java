package com.example.hololiveapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
    }

    public void PlayMedia(View view){
        startActivity(new Intent(MainActivity.this, MediaActivity.class));
    }

    public void goToGallery(View view){
        startActivity(new Intent(this,VtuberLibraryActivity.class));
    }

}