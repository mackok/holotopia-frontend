package com.example.hololiveapp;

import android.content.pm.ActivityInfo;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
/**
 * Class that contains the layout and operations of the activity_watson_bio.xml
 */
public class WatsonBioActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_watson_bio);
    }
}