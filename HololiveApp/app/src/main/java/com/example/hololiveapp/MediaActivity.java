package com.example.hololiveapp;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

public class MediaActivity extends AppCompatActivity {

    SimpleExoPlayer player;
    PlayerView playerView;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mediaplayer);
        player = new SimpleExoPlayer.Builder(this).build();
        playerView = findViewById(R.id.exoplayview);
        playerView.setPlayer(player);
        //video
        //MediaItem mediaItem = MediaItem.fromUri("https://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4");
        //livestream
        MediaItem mediaItem = MediaItem.fromUri("http://f24hls-i.akamaihd.net/hls/live/221147/F24_EN_HI_HLS/master_2000.m3u8");
        player.setMediaItem(mediaItem);
        player.prepare();
        player.play();
    }
}
