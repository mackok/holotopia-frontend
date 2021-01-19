package com.example.hololiveapp;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.concurrent.Delayed;
/**
 * Class that contains the layout and operations of the activity_splash.xml
 */
public class SplashActivity extends AppCompatActivity {

    /**
     * Creates and/or sets activity parameters,functions and objects needed to display and
     * set the layout of the activity. Handler object is also created inorder to show the splashscreen
     * on app startup(otherwise it immediately goes to the dashboard activity).
     * @param savedInstanceState Bundle object that contains the previous state of this activity
     *                           so that it can revert to a previous state when needed
     *                           (Disabled since it is a launcher activity).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }
        }, 2000);



    }
}