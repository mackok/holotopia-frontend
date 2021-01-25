package com.example.hololiveapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import com.bumptech.glide.Glide;
import com.example.hololiveapp.Api.ApiHandler;

/**
 * Class that contains the layout and operations of the activity_watson_bio.xml
 */
public class WatsonBioActivity extends AppCompatActivity {

    private ApiHandler apiHandler = new ApiHandler();
    private LinearLayoutCompat linLay;

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
        linLay = findViewById(R.id.linearWatson);
        apiHandler.getApiData("6717");
        createImageViews();
    }

    /**
     * Create the ImageViews used to display thumbnails of the videos and makes them clickable
     */
    public void createImageViews(){
        Thread imgThr = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            runOnUiThread(() -> {
                for (int x = 0; x < apiHandler.getVideolist().size(); x++) {
                    ImageView img = new ImageView(WatsonBioActivity.this);
                    LinearLayoutCompat.LayoutParams lp = new LinearLayoutCompat.LayoutParams(500, 500);
                    img.setLayoutParams(lp);
                    String youtubeId = apiHandler.getVideolist().get(x).getYoutube_id();
                    img.setOnClickListener(v -> {
                        Intent intent = new Intent(WatsonBioActivity.this, MediaActivity.class);
                        intent.putExtra("id",youtubeId);
                        startActivity(intent);
                    });
                    linLay.addView(img);
                    Glide.with(WatsonBioActivity.this)
                            .load(apiHandler.getVideolist().get(x).getThumbnail())
                            .override(500, 500)
                            .into(img);
                    if(img.getParent() != null) {
                        ((ViewGroup)img.getParent()).removeView(img);
                    }
                    linLay.addView(img);
                }
            });
        });
        imgThr.start();
    }
}