package com.example.hololiveapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Class that contains the layout and operations of the activity_vtuber_library.xml
 */
public class VtuberLibraryActivity extends AppCompatActivity {

    /**
     * Creates and/or sets activity parameters,functions and objects needed to display and
     * set the layout of the activity.
     * @param savedInstanceState Bundle object that contains the previous state of this activity
     *                           so that it can revert to a previous state when needed.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_vtuber_library);
    }


    /**
     *  Used to navigate to the GuraBioActivity class
     * @param view This class represents the basic building block for user interface components.
     *             A View occupies a rectangular area on the screen and is responsible for drawing and event handling.
     */
    public void goToGuraBio(View view){
        startActivity(new Intent(this,GuraBioActivity.class));
    }

    /**
     *  Used to navigate to the MoriBioActivity class
     * @param view This class represents the basic building block for user interface components.
     *             A View occupies a rectangular area on the screen and is responsible for drawing and event handling.
     */
    public void goToMoriBio(View view){
        startActivity(new Intent(this,MoriBioActivity.class));
    }

    /**
     *  Used to navigate to the NinoBioActivity class
     * @param view This class represents the basic building block for user interface components.
     *             A View occupies a rectangular area on the screen and is responsible for drawing and event handling.
     */
    public void goToNinoBio(View view){
        startActivity(new Intent(this,NinoBioActivity.class));
    }

    /**
     *  Used to navigate to the TakaBioActivity class
     * @param view This class represents the basic building block for user interface components.
     *             A View occupies a rectangular area on the screen and is responsible for drawing and event handling.
     */
    public void goToTakaBio(View view){
        startActivity(new Intent(this,TakaBioActivity.class));
    }

    /**
     *  Used to navigate to the WatsonBioActivity class
     * @param view This class represents the basic building block for user interface components.
     *             A View occupies a rectangular area on the screen and is responsible for drawing and event handling.
     */
    public void goToWatsonBio(View view){
        startActivity(new Intent(this,WatsonBioActivity.class));
    }

}