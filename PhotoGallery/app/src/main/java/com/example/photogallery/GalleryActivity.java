package com.example.photogallery;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity {
    @Override
    protected void onResume() {
        super.onResume();
        loadImages(); // your method that loads images into RecyclerView/GridView
    }
    private void loadImages() {

        GridView gridView = findViewById(R.id.galleryGrid);
        ArrayList<String> imagePaths = new ArrayList<>();

        File folder = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyGalleryApp");

        File[] files = folder.listFiles();

        if (files != null) {
            for (File f : files) {
                imagePaths.add(f.getAbsolutePath());
            }
        }

        ImageAdapter adapter = new ImageAdapter(this, imagePaths);
        gridView.setAdapter(adapter);
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        // Line 17 ko aise likho:
        GridView grid = findViewById(R.id.galleryGrid);
        ArrayList<String> imagePaths = new ArrayList<>();

        File folder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "MyGalleryApp");
        File[] files = folder.listFiles();

        if (files != null) {
            for (File f : files) imagePaths.add(f.getAbsolutePath());
        }

        ImageAdapter adapter = new ImageAdapter(this, imagePaths);
        grid.setAdapter(adapter);

        grid.setOnItemClickListener((parent, view, position, id) -> {
            Intent i = new Intent(this, DetailActivity.class);
            i.putExtra("path", imagePaths.get(position));
            startActivity(i);
        });
    }
}