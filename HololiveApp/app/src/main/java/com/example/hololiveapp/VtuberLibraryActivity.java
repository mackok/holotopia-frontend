package com.example.hololiveapp;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class VtuberLibraryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vtuber_library);
    }


    public void guraBio(View view){

        startActivity(new Intent(this,GuraBioActivity.class));
    }
}