package com.example.hololiveapp;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
/**
 * Class that contains the layout and operations of the activity_mediaplayer.xml
 * This class provides the mediaplayer needed to play video or livestream files.
 */
public class MediaActivity extends AppCompatActivity {

    SimpleExoPlayer player;
    PlayerView playerView;

    /**
     * Creates and/or sets activity parameters,functions and objects needed to display and
     * set the layout of the activity.
     * @param savedInstanceState Bundle object that contains the previous state of this activity
     *                           so that it can revert to a previous state when needed.
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mediaplayer);
        configureMediaPlayer();
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
        MediaItem mediaItem = MediaItem.fromUri("http://f24hls-i.akamaihd.net/hls/live/221147/F24_EN_HI_HLS/master_2000.m3u8");
        player.setMediaItem(mediaItem);
        player.prepare();
    }

    /**
     * stops and releases the mediaplayer when going back to a previous activity.
     * Therefore making sure that memory is freed when necessary and the mediaplayer stops playing.
     */
    @Override
    public void onBackPressed()
    {
        player.stop();
        player.release();
        playerView.onPause();
        super.onBackPressed();
    }

    /**
     * Pauses the view and the player, thereby preventing overlap between videos being played in the player and in the
     * playerview when pressing pause control.
     */
    @Override
    protected void onPause() {
        super.onPause();
        player.stop();
        playerView.onPause();
    }

}
