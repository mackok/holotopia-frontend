package com.example.hololiveapp;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MediaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mediaplayer);

        VideoView vidView = (VideoView) findViewById(R.id.video_view);
        Uri uri = Uri.parse("rtsp://r7—sn-4g57kue6.googlevideo.com/Ck0LENy73wIaRAmk3cJBg-iaXhMYDSANFC0k8JRWMOCoAUIJbXYtZ29vZ2xlSARSBXdhdGNoYKK205Ti2LaNVooBC2lZYlRtN201YTlRDA==/9EFB79E36D6A4191F3BF60D01A0AE429B5C9AA32.B774C6D2C0948C3A49A088F9F5CCCFBE34864B29/yt6/1/video.3gp");
        vidView.setVideoURI(uri);

        MediaController medcontrol = new MediaController(this);
        vidView.setMediaController(medcontrol);



    }
}
