package com.example.mediaplayer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private VideoView videoView;
    private EditText editUrl;
    private static final int PICK_FILE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.videoView);
        editUrl = findViewById(R.id.editUrl);

        // Buttons for selecting and loading
        findViewById(R.id.btnOpenFile).setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("video/* audio/*"); // Helps filter for media
            startActivityForResult(intent, PICK_FILE_REQUEST);
        });

        findViewById(R.id.btnLoadUrl).setOnClickListener(v -> {
            String url = editUrl.getText().toString();
            if (!url.isEmpty()) {
                videoView.setVideoURI(Uri.parse(url));
                videoView.start();
            }
        });

        // Playback controls
        findViewById(R.id.btnPlay).setOnClickListener(v -> videoView.start());
        findViewById(R.id.btnPause).setOnClickListener(v -> videoView.pause());
        findViewById(R.id.btnStop).setOnClickListener(v -> videoView.stopPlayback());
        findViewById(R.id.btnRestart).setOnClickListener(v -> {
            videoView.seekTo(0);
            videoView.start();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FILE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri fileUri = data.getData();
            videoView.setVideoURI(fileUri);
            videoView.start();
            Toast.makeText(this, "Playing Selected File", Toast.LENGTH_SHORT).show();
        }
    }
}