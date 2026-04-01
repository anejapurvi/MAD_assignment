package com.example.mediaplayer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Q2: Media Player Application
 * Developed by: Purvi Aneja
 */
public class MainActivity extends AppCompatActivity {

    private VideoView mediaDisplay;
    private Button btnOpenFile, btnOpenURL, btnPlay, btnPause, btnStop, btnRestart;

    // Direct URL for video streaming requirement
    private String streamUrl = "https://www.w3schools.com/html/mov_bbb.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaDisplay = findViewById(R.id.mediaDisplay);
        btnOpenFile = findViewById(R.id.btnOpenFile);
        btnOpenURL = findViewById(R.id.btnOpenURL);
        btnPlay = findViewById(R.id.btnPlay);
        btnPause = findViewById(R.id.btnPause);
        btnStop = findViewById(R.id.btnStop);
        btnRestart = findViewById(R.id.btnRestart);

        // a) Play from disk
        btnOpenFile.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("video/* audio/*");
            startActivityForResult(intent, 10);
        });

        // b) Stream from URL
        btnOpenURL.setOnClickListener(v -> {
            mediaDisplay.setVideoURI(Uri.parse(streamUrl));
            mediaDisplay.start();
            Toast.makeText(this, "Streaming...", Toast.LENGTH_SHORT).show();
        });

        btnPlay.setOnClickListener(v -> mediaDisplay.start());
        btnPause.setOnClickListener(v -> mediaDisplay.pause());
        btnStop.setOnClickListener(v -> {
            mediaDisplay.stopPlayback();
            mediaDisplay.resume();
        });
        btnRestart.setOnClickListener(v -> {
            mediaDisplay.seekTo(0);
            mediaDisplay.start();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 && resultCode == RESULT_OK && data != null) {
            mediaDisplay.setVideoURI(data.getData());
            mediaDisplay.start();
        }
    }
}