package com.example.hololiveapp;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void PlayMedia(View view){

        Intent mediaIntent = new Intent(MainActivity.this, MediaActivity.class);
        startActivity(mediaIntent);
    }

    public void goToGallery(View view){
        startActivity(new Intent(this,VtuberLibraryActivity.class));
    }

}