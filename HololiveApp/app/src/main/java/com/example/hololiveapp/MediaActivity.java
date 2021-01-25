package com.example.hololiveapp;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.hololiveapp.Api.ApiHandler;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import lombok.SneakyThrows;

/**
 * Class that contains the layout and operations of the activity_mediaplayer.xml
 * This class provides the mediaplayer needed to play video or livestream files.
 */
public class MediaActivity extends AppCompatActivity {

    private SimpleExoPlayer player;
    private PlayerView playerView;
    public ApiHandler apiHandler = new ApiHandler();


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
        apiHandler.getVideoLocationFromApi(getIntent());
        playMedia();


    }

    /**
     * Plays the video that was set in the player
     */
    public void playMedia(){
        try {
            Thread.sleep(500);
        }catch (Exception e){
            e.printStackTrace();
        }
        MediaItem mediaItem = MediaItem.fromUri(apiHandler.getVideoLocation());
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
