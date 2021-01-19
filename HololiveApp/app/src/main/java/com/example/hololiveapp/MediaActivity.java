package com.example.hololiveapp;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Class that contains the layout and operations of the activity_mediaplayer.xml
 * This class provides the mediaplayer needed to play video or livestream files.
 */
public class MediaActivity extends AppCompatActivity {

    SimpleExoPlayer player;
    PlayerView playerView;
    String videoLocation;

    /**
     * Creates and/or sets activity parameters,functions and objects needed to display and
     * set the layout of the activity.
     * @param savedInstanceState Bundle object that contains the previous state of this activity
     *                           so that it can revert to a previous state when needed.
     */
    @SneakyThrows
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mediaplayer);
        configureMediaPlayer();
        getVideoFromApi();
        try {
            Thread.sleep(500);
        }catch (Exception e){
            e.printStackTrace();
        }
        playMedia();


    }

    /**
     * Plays the video that was set in the player
     */
    public void playMedia(){
        MediaItem mediaItem = MediaItem.fromUri(videoLocation);
        player.setMediaItem(mediaItem);
        player.prepare();
        player.play();
    }

    /**
     * configures and prepares the mediaplayer with the necessary MediaItems and binds the Exoplayer to the PlayerView
     */
    public void configureMediaPlayer(){
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        player = new SimpleExoPlayer.Builder(this).build();
        playerView = findViewById(R.id.exoplayview);
        playerView.setPlayer(player);
    }

    /**
     * retrieves the url located at the holotopia endpoint
     */
    public void getVideoFromApi(){
        Thread getVideoThr = new Thread(() -> {
            try {
                String youtubeId = getIntent().getStringExtra("id");
                String apiEndpoint = "http://10.0.2.2:8080/holotopia_backend_war/videos/"+ youtubeId +"/url";
                URL url = new URL(apiEndpoint);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();
                String inputline = "";
                ObjectMapper mapper = new ObjectMapper();
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                while ((inputline = reader.readLine()) != null) {
                    response.append(inputline);
                }
                videoLocation = response.toString();
                reader.close();

            }catch (Exception e){
                e.printStackTrace();
            }

        });
        getVideoThr.start();

    }


    /**
     * stops and releases the mediaplayer when going back to a previous activity,
     * therefore making sure that memory is freed when necessary and the mediaplayer stops playing.
     */
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        player.stop();
        player.release();
        playerView.onPause();
    }

    /**
     * Pauses the view and the player, thereby preventing overlap between videos being played in the player and in the
     * playerview, when pressing pause control.
     */
    @Override
    protected void onPause() {
        super.onPause();
        player.stop();
        playerView.onPause();
    }

}
