package com.example.hololiveapp;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import com.bumptech.glide.Glide;
import com.example.hololiveapp.models.VideoDataModel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Class that contains the layout and operations of the activity_gura_bio.xml
 */
public class GuraBioActivity extends AppCompatActivity {

    private List<VideoDataModel> videolist;
    private TextView textView;
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
        setContentView(R.layout.activity_gura_bio);
        linLay = findViewById(R.id.LinearGura);
        getApiData();
        createImageViews();

    }

    public void getApiData(){
        Thread apiThr = new Thread(() -> {
            try {
                URL url = new URL("http://10.0.2.2:8080/holotopia_backend_war/hololive-members/6/videos");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();
                ObjectMapper mapper = new ObjectMapper();
                String inputline = "";
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                while ((inputline = reader.readLine()) != null) {
                    response.append(inputline);
                }
                reader.close();
                videolist = mapper.readValue(response.toString(), new TypeReference<List<VideoDataModel>>(){});
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        apiThr.start();
    }

    public void createImageViews(){
        Thread imgThr = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            runOnUiThread(() -> {
                for (int x = 0; x < videolist.size(); x++) {
                    ImageView img = new ImageView(GuraBioActivity.this);
                    LinearLayoutCompat.LayoutParams lp = new LinearLayoutCompat.LayoutParams(500, 500);
                    img.setLayoutParams(lp);
                    linLay.addView(img);
                    Glide.with(GuraBioActivity.this)
                            .load(videolist.get(x).getThumbnail())
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

