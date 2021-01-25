package com.example.hololiveapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
/**
 * Class that contains the layout and operations of the activity_main.xml
 * This is the dashboard activity that is used to navigate the different features in the app.
 */
public class MainActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_main);
    }

    /**
     *  Used to navigate to the VtuberLibraryActivity class
     * @param view This class represents the basic building block for user interface components.
     *      *             A View occupies a rectangular area on the screen and is responsible for drawing and event handling.
     */
    public void goToGallery(View view){
        startActivity(new Intent(this,VtuberLibraryActivity.class));
    }

}